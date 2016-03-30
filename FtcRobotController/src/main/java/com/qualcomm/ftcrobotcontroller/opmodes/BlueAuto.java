package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.ftcrobotcontroller.opmodes.robot.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Carlos on 12/19/2015.
 */
public class BlueAuto extends LinearOpMode{

    Drivetrain drivetrain = new Drivetrain();
    Intake intake = new Intake();
    BeaconScorer beaconScorer = new BeaconScorer();
    ClimberScorer climberScorer = new ClimberScorer();
    Dumper dumper = new Dumper();

    String beaconColor;


    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain.init(hardwareMap);
        intake.init(hardwareMap);
        beaconScorer.init(hardwareMap);
        climberScorer.init(hardwareMap);
        dumper.init(hardwareMap);
        telemetry.addData("Initialization Complete", "");

        waitForStart();

        drivetrain.getHeading();

        telemetry.addData("Start Autonomous", ".");

        intake.outward();

        drivetrain.moveDistance(2800, .3);
        sleep(500);

        drivetrain.turnAngleRight(45, 0.3);
        sleep(500);

        drivetrain.moveDistance(4550, .3);
        sleep(500);

        drivetrain.turnAngleRight(45, 0.3);
        intake.stop();
        sleep(500);

        while(drivetrain.getHeading() > 90)
            drivetrain.tankDrive(0, 0.2);

        drivetrain.brake();

        drivetrain.moveDistance(1300, .25);

        climberScorer.score();
        sleep(1000);
        climberScorer.reset();

        drivetrain.moveDistance(500, -.25);
        sleep(500);

        /*
        drivetrain.turnAngle(-90, -0.3);
        sleep(500);

        telemetry.addData("Heading: ", drivetrain.getHeading());
        */
    }
}
