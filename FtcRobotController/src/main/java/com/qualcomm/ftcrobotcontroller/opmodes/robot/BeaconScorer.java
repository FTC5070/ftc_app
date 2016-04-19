package com.qualcomm.ftcrobotcontroller.opmodes.robot;

import com.qualcomm.robotcore.hardware.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nikhil on 11/13/2015.
 */
public class BeaconScorer {
    Servo leftButtonServo;
    double leftButtonInitPosition = 0.65;
    double leftButtonPressedPosition = 0;

    Servo rightButtonServo;
    double rightButtonInitPosition = 0.35;
    double rightButtonPressedPosition = 0;

    public ColorSensor leftColorSensor;
    public ColorSensor rightColorSensor;

    long pressDelay = 50;

    public BeaconScorer(){
    }

    public void init(HardwareMap hardwareMap){
        leftButtonServo = hardwareMap.servo.get("leftButtonServo");
        rightButtonServo = hardwareMap.servo.get("rightButtonServo");
        leftColorSensor = hardwareMap.colorSensor.get("leftSideBeaconColorSensor");
        rightColorSensor = hardwareMap.colorSensor.get("rightSideBeaconColorSensor");

        leftButtonServo.setPosition(leftButtonInitPosition);
        rightButtonServo.setPosition(rightButtonInitPosition);
    }

    public void pressLeftSideButton() throws InterruptedException {
        leftButtonServo.setPosition(leftButtonPressedPosition);
        sleep(pressDelay);
        leftButtonServo.setPosition(leftButtonInitPosition);
    }

    public void pressRightSideButton() throws InterruptedException {
        rightButtonServo.setPosition(rightButtonPressedPosition);
        sleep(pressDelay);
        rightButtonServo.setPosition(rightButtonInitPosition);
    }


    public String getLeftSideBeaconColor() {

        leftColorSensor.enableLed(false);

        if(leftColorSensor.blue() == 0 && leftColorSensor.red() == 0)
            return "Null";

        leftColorSensor.enableLed(true);

        if(leftColorSensor.blue() > leftColorSensor.red())
            return "Blue";
        else if(leftColorSensor.red() > leftColorSensor.blue())
            return "Red";
        else
            return "Null";
    }

    public String getRightSideBeaconColor() {

        rightColorSensor.enableLed(false);

        if(rightColorSensor.blue() == 0 && rightColorSensor.red() == 0)
            return "Null";

        rightColorSensor.enableLed(true);

        if(rightColorSensor.blue() > rightColorSensor.red())
            return "Blue";
        else if(rightColorSensor.red() > rightColorSensor.blue())
            return "Red";
        else
            return "Null";
    }

    public void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}




