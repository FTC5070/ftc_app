package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.ftcrobotcontroller.opmodes.robot.BeaconScorer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;

import java.sql.Time;
import java.util.concurrent.TimeUnit;


/**
 * Created by Nikhil on 11/11/2015.
 */
public class BeaconTest extends LinearOpMode {
    BeaconScorer beaconScorer = new BeaconScorer();

    String beaconColor;

    public void runOpMode() throws InterruptedException {

        beaconScorer.init(hardwareMap);

        waitOneFullHardwareCycle();

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Mode: ", "Ready to run. Press A.");
            while(!gamepad1.a)
            {}

            telemetry.addData("Mode: ", "Running");

            beaconColor = beaconScorer.getBeaconColor();

            if(beaconColor == "Red") {
                beaconScorer.pressRightButton();
            }
            else if(beaconColor == "Blue") {
                beaconScorer.pressLeftButton();
            }
            else {
                telemetry.addData("ERROR!", "Could not read beacon color!");
            }

            sleep(1000);

            beaconScorer.resetButtonPressers();
        }
    }




}
