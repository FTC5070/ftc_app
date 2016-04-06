package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.ftcrobotcontroller.opmodes.robot.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Carlos on 12/19/2015.
 */
public class BlueAuto extends LinearOpMode{

    Drivetrain drivetrain = new Drivetrain();
    //Intake intake = new Intake();
    //BeaconScorer beaconScorer = new BeaconScorer();
    //ClimberScorer climberScorer = new ClimberScorer();
    //Dumper dumper = new Dumper();

    String beaconColor;


    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain.init(hardwareMap);
        //intake.init(hardwareMap);
        //beaconScorer.init(hardwareMap);
        //climberScorer.init(hardwareMap);
        //dumper.init(hardwareMap);
        telemetry.addData("Initialization Complete", "");

        waitForStart();

        drivetrain.getHeading();

        telemetry.addData("Start Autonomous", ".");

        //intake.outward();

        //drivetrain.moveDistance(1430, .4);
        //sleep(500);

        telemetry.addData("Initial Heading: ", drivetrain.getHeading());
        drivetrain.turnAngleLeftSide(45, 0.2);
        sleep(500);
        telemetry.addData("Final Heading: ", drivetrain.getHeading());

        //drivetrain.moveDistance(2500 ,0.4);


    }
}
