package com.qualcomm.ftcrobotcontroller.opmodes.robot;

import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Carlos on 11/14/2015.
 */
public class ZiplineScorer {

    Servo leftServo;

    double leftOut = 0.25;
    double leftIn = 0.85;




    Servo rightServo;

    double rightOut = 0.75;
    double rightIn = 0.15;

    public ZiplineScorer(){

    }

    public void init(HardwareMap hardwareMap){
        leftServo = hardwareMap.servo.get("leftZiplineServo");
        rightServo = hardwareMap.servo.get("rightZiplineServo");

        leftServo.setPosition(leftIn);
        rightServo.setPosition(rightIn);
    }

    public void setLeftIn(){
        leftServo.setPosition(leftIn);
    }

    public void setRightIn(){
        rightServo.setPosition(rightIn);
    }

    public void setLeftOut(){
        leftServo.setPosition(leftOut);
    }

    public void setRightOut(){
        rightServo.setPosition(rightOut);
    }
}
