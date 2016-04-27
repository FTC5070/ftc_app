package com.qualcomm.ftcrobotcontroller.opmodes.robot;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Carlos on 11/13/2015.
 */
public class Dumper {

    Servo leftFlap;
    double leftFlapOpenPosition;
    double leftFlapClosedPosition;

    Servo rightFlap;
    double rightFlapOpenPosition;
    double rightFlapClosedPosition;

    DigitalChannel leftLimitSwitch;
    DigitalChannel rightLimitSwitch;

    DcMotor motor;
    int motorLeftPostion;
    int motorCenterPosition;
    int motorRightPosition;

    int targetPosition = 0;
    int error = 0;
    double KP = 0.05;
    int ERROR_TOLERANCE = 5;

    public Dumper() {
    }

    public void init(HardwareMap hardwareMap) {

        motor = hardwareMap.dcMotor.get("dumperServo");
        leftFlap = hardwareMap.servo.get("leftFlapServo");
        rightFlap = hardwareMap.servo.get("rightFlapServo");
        leftLimitSwitch = hardwareMap.digitalChannel.get("leftLimitSwitch");
        rightLimitSwitch = hardwareMap.digitalChannel.get("rightLimitSwitch");

        leftFlap.setPosition(leftFlapClosedPosition);
        rightFlap.setPosition(rightFlapClosedPosition);
    }

    public void initializeEncoderValue() {
        while (leftLimitSwitch.getState() == false) {
            motor.setPower(-0.5);
        }
        motor.setPower(0);

        motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        motorLeftPostion = motor.getCurrentPosition();

        while (rightLimitSwitch.getState() == false) {
            motor.setPower(0.5);
        }
        motor.setPower(0);

        motorRightPosition = motor.getCurrentPosition();

        motorCenterPosition = (motorLeftPostion + motorRightPosition) / 2;

        setPosition(motorCenterPosition);
    }

    public void setPosition(int position) {

        targetPosition = position;

        do {
            if (Math.abs(error) > ERROR_TOLERANCE)
                error = targetPosition - motor.getCurrentPosition();
            else
                error = 0;

            if (leftLimitSwitch.getState() || rightLimitSwitch.getState())
                error = 0;

            motor.setPower(error * KP);
        }
        while (Math.abs(error) > ERROR_TOLERANCE);
    }

    public void setToLeftPosition() {
        while (leftLimitSwitch.getState() == false)
            motor.setPower(-1);

        motor.setPower(0);
    }

    public void setToRightPosition() {
        while (rightLimitSwitch.getState() == false)
            motor.setPower(1);

        motor.setPower(0);
    }

    public void moveLeft(){
        motor.setPower(1);
    }

    public void moveRight(){
        motor.setPower(-1);
    }

    public void stop(){
        motor.setPower(0);
    }

    public void setToCenterPosition() {
        setPosition(motorCenterPosition);
    }


    public void openLeftFlap(){
        leftFlap.setPosition(leftFlapOpenPosition);
    }
    public void closeLeftFlap(){
        leftFlap.setPosition(leftFlapClosedPosition);
    }
    public void openRightFlap(){
        leftFlap.setPosition(rightFlapOpenPosition);
    }
    public void closeRightFlap(){
        leftFlap.setPosition(rightFlapClosedPosition);
    }
}
