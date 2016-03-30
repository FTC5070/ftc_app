package com.qualcomm.ftcrobotcontroller.opmodes.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Carlos on 3/10/2016.
 */
public class Guards {
    Servo backLeft;
    Servo backRight;

    double backLeftUpSpeed = 0;
    double backLeftDownSpeed = 1;
    double backLeftStopSpeed = 0.5;
    
    double backRightUpSpeed = 1;
    double backRightDownSpeed = 0;
    double backRightStopSpeed = 0.5;

    long swingTime;

    public Guards(){}

    public void init(HardwareMap hardwareMap){
        backLeft = hardwareMap.servo.get("backLeftGuard");
        backRight = hardwareMap.servo.get("backRightGuard");
        backLeft.setPosition(backLeftStopSpeed);
        backRight.setPosition(backRightStopSpeed);
    }

    public void raiseBackGuard(){
        backLeft.setPosition(backLeftUpSpeed);
        backRight.setPosition(backRightUpSpeed);
        //wait(swingTime);
        //backLeft.setPosition(backLeftStopSpeed);
        //backRight.setPosition(backRightStopSpeed);
    }

    public void lowerBackGuard() {
        backLeft.setPosition(backLeftDownSpeed);
        backRight.setPosition(backRightDownSpeed);
        //wait(swingTime);
        //backLeft.setPosition(backLeftStopSpeed);
        //backRight.setPosition(backRightStopSpeed);
    }

    public void stopGuard(){
        backLeft.setPosition(backLeftStopSpeed);
        backRight.setPosition(backRightStopSpeed);}


}
