package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Carlos on 11/24/2015.
 */
public class ColorTest extends LinearOpMode {

    ColorSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = hardwareMap.colorSensor.get("colorSensor");

        waitForStart();

        /*colorSensor.enableLed(false);
        waitOneFullHardwareCycle();
        sleep(1000);
        colorSensor.enableLed(true);
        waitOneFullHardwareCycle();*/

        while(opModeIsActive()){
            colorSensor.enableLed(gamepad1.x);

            if(colorSensor.blue() > colorSensor.red())
                telemetry.addData("Color: ", "Blue");
            else if(colorSensor.red() > colorSensor.blue())
                telemetry.addData("Color: ", "Red");
            else
                telemetry.addData("Color: ", "Null");
        }

    }

    public void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}
