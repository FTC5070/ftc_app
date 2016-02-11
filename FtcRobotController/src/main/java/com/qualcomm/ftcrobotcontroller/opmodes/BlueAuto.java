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

        sleep(1000);
        drivetrain.getHeading();

        telemetry.addData("Start Autonomous", ".");

        intake.outward();

        drivetrain.moveDistance(2800, .25);
        sleep(1000);
        drivetrain.turnAngleRight(45, 0.25);
        sleep(1000);
        drivetrain.moveDistance(4200, .25);
        sleep(1000);
        drivetrain.turnAngleRight(45, 0.25);

        while(drivetrain.getHeading() > 90)
            drivetrain.tankDrive(0, 0.2);
        drivetrain.brake();

        intake.stop();

        sleep(1000);
        drivetrain.moveDistance(1000, .25);
        telemetry.addData("Heading: " , drivetrain.getHeading());
    }
}


