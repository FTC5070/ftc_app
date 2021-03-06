package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.ftcrobotcontroller.opmodes.robot.*;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.TimeUnit;

/**
 * Created by Carlos on 11/23/2015.
 */
public class RedAuto extends LinearOpMode{

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

        sleep(500);
        drivetrain.getHeading();

        telemetry.addData("Start Autonomous", ".");

        intake.outward();

        drivetrain.moveDistance(2650, 0.3);//2800, .3, RedAuto.this);
        sleep(500);

        /*
        drivetrain.turnAngleLeft(-45, 0.3);
        sleep(500);

        //drivetrain.moveDistance(4250, 0.3);//4550, .3, RedAuto.this);
        //sleep(500);

        drivetrain.moveDistance(2000, 0.2);
        sleep(500);

        drivetrain.turnAngleLeft(-110, -0.3);
        sleep(500);

        drivetrain.turnAngleLeft(110, 0.3);
        sleep(500);

        drivetrain.moveDistance(2250, 0.2);
        sleep(500);


        drivetrain.turnAngleLeft(-45, 0.3);
        intake.stop();
        sleep(500);

        drivetrain.brake();


        while(drivetrain.getHeading() < 270)
            drivetrain.tankDrive(0.2, 0);

        sleep(50);

        telemetry.addData("Before Final Move","");
        drivetrain.moveDistance(2100, 0.20);//1300, .25);
        sleep(500);

        telemetry.addData("After Final Move:", "");
        
        //climberScorer.score();
        sleep(1000);
        //climberScorer.reset();

        drivetrain.moveDistance(500, -.25);
        sleep(500);
        */
    }
}


