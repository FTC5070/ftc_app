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

    GyroSensor gyro;

    int heading = 0;
    public int headingTolerance = 2;

    double wheelCircumference = 6 * Math.PI;
    double ticksPerRotation = 1049;

    public Drivetrain(){
    }

    public void init(HardwareMap hardwareMap) throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("leftMotor1");
        backLeft = hardwareMap.dcMotor.get("leftMotor2");
        frontRight = hardwareMap.dcMotor.get("rightMotor1");
        backRight = hardwareMap.dcMotor.get("rightMotor2");

        gyro = hardwareMap.gyroSensor.get("gyro");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();

        gyro.calibrate();

        while (gyro.isCalibrating() || gyro.getHeading() != 0) {}

    }

    public void tankDrive(double leftSpeed,double rightSpeed) {
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


    public void moveDistance(int targetEncoderValue, double speed, LinearOpMode auto) throws InterruptedException {
        resetEncoders();

        while(Math.abs(getAverageEncoderValue("All")) < Math.abs(targetEncoderValue) && auto.opModeIsActive())
        {
            tankDrive(speed, speed);
        }

        if(!auto.opModeIsActive())
            return;

        brake();
    }


    public void turnAngle(int targetAngle, double speed, LinearOpMode auto) {

        int currentHeading = gyro.getHeading();
        int goalHeading = (currentHeading + targetAngle) % 360;

        if(goalHeading < 0)
            goalHeading += 360;

        //telemetry.addData("Goal Heading: ", goalHeading);

        while (Math.abs(goalHeading - gyro.getHeading()) > headingTolerance && auto.opModeIsActive()) {
            arcadeDrive(0, speed);
            //telemetry.addData("Current Heading: ", gyro.getHeading());
        }

        if(!auto.opModeIsActive())
            return;

        //telemetry.addData(" ", "Turn Complete");

        brake();
    }

    public void turnAngleRight(int targetAngle, double speed, LinearOpMode auto) {

        int currentHeading = gyro.getHeading();
        int goalHeading = (currentHeading + targetAngle) % 360;

        if(goalHeading < 0)
            goalHeading += 360;

        while (Math.abs(goalHeading - gyro.getHeading()) > headingTolerance && auto.opModeIsActive()) {
            tankDrive(speed, 0);
        }

        brake();
    }

    public void turnAngleLeft(int targetAngle, double speed, LinearOpMode auto) {

        int currentHeading = gyro.getHeading();
        int goalHeading = (currentHeading + targetAngle) % 360;

        if(goalHeading < 0)
            goalHeading += 360;

        while (Math.abs(goalHeading - gyro.getHeading()) > headingTolerance && auto.opModeIsActive()) {
            tankDrive(0, speed);
        }

        brake();
    }

    public void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}
