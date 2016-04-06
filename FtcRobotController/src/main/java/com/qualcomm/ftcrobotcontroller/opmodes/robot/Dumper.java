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

    DcMotor motor;
    int motorLeftPostion;
    int motorInitPosition;
    int motorRightPosition;

    int targetPosition = 0;
    int error = 0;
    double KP = 0.05;
    int ERROR_TOLERANCE = 5;

    public Dumper(){
    }

    public void init(HardwareMap hardwareMap){

        motor = hardwareMap.dcMotor.get("dumperServo");
        leftFlap = hardwareMap.servo.get("leftFlapServo");
        rightFlap = hardwareMap.servo.get("rightFlapServo");

        leftFlap.setPosition(leftFlapClosedPosition);
        rightFlap.setPosition(rightFlapClosedPosition);
    }

    public void setPosition(int position){

        targetPosition = position;

        do {
            if (Math.abs(error) > ERROR_TOLERANCE)
                error = targetPosition - motor.getCurrentPosition();
            else
                error = 0;

            motor.setPower(error * KP);
        }
        while(Math.abs(error) > ERROR_TOLERANCE);
    }



}
