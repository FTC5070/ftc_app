package com.qualcomm.ftcrobotcontroller.opmodes.robot;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Carlos on 11/13/2015.
 */
public class Dumper {
    public Servo servo;


    double initPosition = 0.50;
    double rightPosition = 0.14;
    double leftPosition = 0.86;
    double maxIncrement = 1;//0.05;

    public double currentPosition = initPosition;

    public Dumper(){
    }

    public void init(HardwareMap hardwareMap){

        servo = hardwareMap.servo.get("dumperServo");
        servo.setPosition(initPosition);

    }
    public void move(double increment){
        if(Math.abs(increment) > maxIncrement)
            increment = Math.signum(increment)*maxIncrement;

        currentPosition = Range.clip(currentPosition + increment, -1, 1);
        servo.setPosition(currentPosition);
    }

    public void reset(){
        currentPosition = initPosition;
        servo.setPosition(currentPosition);
    }

    public void sweepLeft(){
        currentPosition = leftPosition;
        servo.setPosition(currentPosition);
    }

    public void sweepRight(){
        currentPosition = rightPosition;
        servo.setPosition(currentPosition);
    }

}
