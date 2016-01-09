package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Carlos on 11/24/2015.
 */
public class ColorTest extends LinearOpMode {

    ColorSensor sensorRGB;

    @Override
    public void runOpMode() throws InterruptedException {

        sensorRGB = hardwareMap.colorSensor.get("sensorRGB");

        waitForStart();

        /*sensorRGB.enableLed(false);
        waitOneFullHardwareCycle();
        sleep(1000);
        sensorRGB.enableLed(true);
        waitOneFullHardwareCycle();*/

        while(opModeIsActive()){
            sensorRGB.enableLed(gamepad1.x);

            if(sensorRGB.blue() > sensorRGB.red())
                telemetry.addData("Color: ", "Blue");
            else if(sensorRGB.red() > sensorRGB.blue())
                telemetry.addData("Color: ", "Red");
            else
                telemetry.addData("Color: ", "Null");
        }

    }

    public void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}
