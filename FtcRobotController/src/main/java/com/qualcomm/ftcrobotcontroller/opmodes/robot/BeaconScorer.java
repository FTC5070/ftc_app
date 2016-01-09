package com.qualcomm.ftcrobotcontroller.opmodes.robot;

import com.qualcomm.robotcore.hardware.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nikhil on 11/13/2015.
 */
public class BeaconScorer {


    public Servo rightButtonServo;
    public Servo leftButtonServo;
    public ColorSensor sensorRGB;

    double rightButtonServoPressed = 0.45;
    double leftButtonServoPressed = 0.57;
    double leftButtonServoReset = 0.43;
    double rightButtonServoReset = 0.55;
    
    boolean rightIsPressed = false;
    boolean leftIsPressed = false;

    double servoStop = 0.5;

    long turnTime = 1575;

    public BeaconScorer(){
    }

    public void init(HardwareMap hardwareMap){

        sensorRGB = hardwareMap.colorSensor.get("sensorRGB");
        rightButtonServo = hardwareMap.servo.get("rightButtonServo");
        leftButtonServo = hardwareMap.servo.get("leftButtonServo");

        rightButtonServo.setPosition(servoStop);
        leftButtonServo.setPosition(servoStop);

    }

    public void pressRightButton() throws InterruptedException {

        if(!rightIsPressed) {
            rightButtonServo.setPosition(rightButtonServoPressed);
            TimeUnit.MILLISECONDS.sleep(turnTime);
            rightButtonServo.setPosition(servoStop);
            rightIsPressed = true;
        }
    }

    public void pressLeftButton() throws InterruptedException {

        if(!leftIsPressed) {
            leftButtonServo.setPosition(leftButtonServoPressed);
            TimeUnit.MILLISECONDS.sleep(turnTime);
            leftButtonServo.setPosition(servoStop);
            leftIsPressed = true;
        }

    }

    public void resetButtonPressers() throws InterruptedException {

        if(leftIsPressed)
            leftButtonServo.setPosition(leftButtonServoReset);
        
        if(rightIsPressed)
            rightButtonServo.setPosition(rightButtonServoReset);
        
        TimeUnit.MILLISECONDS.sleep(turnTime);
        leftButtonServo.setPosition(servoStop);
        rightButtonServo.setPosition(servoStop);
    }

    public String getBeaconColor() {

        sensorRGB.enableLed(true);

        if(sensorRGB.blue() == 0 && sensorRGB.red() == 0)
            return "Null";

        sensorRGB.enableLed(false);

        if(sensorRGB.blue() > sensorRGB.red())
            return "Blue";
        else if(sensorRGB.red() > sensorRGB.blue())
            return "Red";
        else
            return "Null";
    }
}




