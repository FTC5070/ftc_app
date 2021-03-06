package com.qualcomm.ftcrobotcontroller.opmodes.robot;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.robocol.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/**
 * Created by Carlos on 11/12/2015.
 */
public class Drivetrain {

    Telemetry telemetry = new Telemetry();
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    public Servo leftMountainBrake;
    double leftMountainBrakeDown = 1.0;
    double leftMountainBrakeUp = 0.4;
    public Servo rightMountainBrake;
    double rightMountainBrakeDown = 0.15;
    double rightMountainBrakeUp = 0.8;

    GyroSensor gyro;
    //ColorSensor leftColorSensor;
    //ColorSensor rightColorSensor;

    int heading = 0;
    public int headingTolerance = 2;

    double wheelCircumference = 6 * Math.PI;
    double ticksPerRotation = 1049;
    double drivingKp = 2.0;

    public Drivetrain(){
    }

    public void init(HardwareMap hardwareMap) throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");

        leftMountainBrake = hardwareMap.servo.get("leftMountainBrakeServo");
        rightMountainBrake = hardwareMap.servo.get("rightMountainBrakeServo");

        gyro = hardwareMap.gyroSensor.get("gyro");
        //leftColorSensor = hardwareMap.colorSensor.get("leftSideDrivetrainColorSensor");
        //rightColorSensor = hardwareMap.colorSensor.get("rightSideDrivetrainColorSensor");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();

        gyro.calibrate();

        while (gyro.isCalibrating() || gyro.getHeading() != 0) {}

    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        frontLeft.setPower(leftSpeed);
        backLeft.setPower(leftSpeed);

        frontRight.setPower(rightSpeed);
        backRight.setPower(rightSpeed);
    }

    public void arcadeDrive(double throttle, double turn){
        double leftPower = Range.clip(throttle + turn, -1, 1);
        double rightPower = Range.clip(throttle - turn, -1, 1);

        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);

        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
    }

    public void brake(){
        while(frontLeft.getPower() != 0 || frontRight.getPower() != 0 || backLeft.getPower() != 0 || backRight.getPower() != 0) {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
    }

    public void lowerMountainBrake(){
        leftMountainBrake.setPosition(leftMountainBrakeDown);
        rightMountainBrake.setPosition(rightMountainBrakeDown);
    }

    public void raiseMountainBrake(){
        leftMountainBrake.setPosition(leftMountainBrakeUp);
        rightMountainBrake.setPosition(rightMountainBrakeUp);
    }

    public void resetEncoders() throws InterruptedException {
        brake();

        frontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        frontRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        backLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        backRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        sleep(50);

        frontLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        frontRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        backLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        backRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        sleep(50);
    }

    public int getAverageEncoderValue(String side) {
        if (side == "All") {
            return (frontLeft.getCurrentPosition() + frontRight.getCurrentPosition() + backLeft.getCurrentPosition() + backRight.getCurrentPosition()) / 4;
        } else if (side == "Left"){
            return (frontLeft.getCurrentPosition() + backLeft.getCurrentPosition()) / 2;
        } else if(side == "Right"){
            return (frontRight.getCurrentPosition() + backRight.getCurrentPosition()) / 2;
        } else{
            return 0;
        }
    }

    public int getHeading() {
        return gyro.getHeading();
    }

    public void drive(double inches, double speed) throws InterruptedException {
        resetEncoders();

        double targetDistance = ticksPerRotation * inches/wheelCircumference;

        while(Math.abs(this.getAverageEncoderValue("All")) < Math.abs(targetDistance))
        {
            this.arcadeDrive(speed, 0);
        }

        this.brake();
    }


    public void moveDistance(int targetEncoderValue, double speed) throws InterruptedException {
        resetEncoders();
        int goalHeading = gyro.getHeading();
        int currentHeading = 0;
        int error = 0;

        while(Math.abs(getAverageEncoderValue("All")) < Math.abs(targetEncoderValue))
        {
            currentHeading = gyro.getHeading();

            error = modularDistance(goalHeading, currentHeading, 360);

            tankDrive(speed + error * drivingKp, speed - error * drivingKp);
        }

        brake();
    }


    public void turnAngle(int targetAngle, double speed) {

        int currentHeading = gyro.getHeading();
        int goalHeading = (currentHeading + targetAngle) % 360;

        if(goalHeading < 0)
            goalHeading += 360;

        while (Math.abs(goalHeading - gyro.getHeading()) > headingTolerance) {
            arcadeDrive(0, speed);
        }
        brake();
    }

    public void turnAngleRightSide(int targetAngle, double speed) {

        int currentHeading = gyro.getHeading();
        int goalHeading = (currentHeading + targetAngle) % 360;

        if(goalHeading < 0)
            goalHeading += 360;

        while (Math.abs(goalHeading - gyro.getHeading()) > headingTolerance) {
            tankDrive(0, speed);
        }

        brake();
    }

    public void turnAngleLeftSide(int targetAngle, double speed) {

        int currentHeading = gyro.getHeading();
        int goalHeading = (currentHeading + targetAngle) % 360;

        if(goalHeading < 0)
            goalHeading += 360;

        while (Math.abs(goalHeading - gyro.getHeading()) > headingTolerance) {
            tankDrive(speed, 0);
        }

        brake();

        while (Math.abs(goalHeading - gyro.getHeading()) > headingTolerance) {
            tankDrive(-speed, 0);
        }

        brake();
    }

    public void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    private int modularDistance(int a, int b, int m){
        return m/2 - ((3*m/2 + a - b) % m);
    }
}
