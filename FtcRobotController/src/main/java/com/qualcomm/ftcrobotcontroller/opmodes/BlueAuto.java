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
        telemetry.addData("Drivetrain Init Complete", "");
        intake.init(hardwareMap);
        telemetry.addData("Intake Init Complete", "");
        beaconScorer.init(hardwareMap);
        telemetry.addData("Beacon Scorer Init Complete", "");
        climberScorer.init(hardwareMap);
        telemetry.addData("Climber Servo Init Complete", "");
        dumper.init(hardwareMap);
        telemetry.addData("Dumper Init Complete", "");

        waitForStart();

        sleep(1000);
        drivetrain.getHeading();

        telemetry.addData("Start Autonomous", ".");

        intake.outward();
        drivetrain.moveDistance(1400, .5);
        telemetry.addData("Step 1 Complete", ".");

        sleep(500);
        //drivetrain.turnAngle(-45, 0.5);

        do{
            drivetrain.arcadeDrive(0, 0.5);
        }while(drivetrain.getHeading() < 53);

       telemetry.addData("Step 2 Complete", ".");
        sleep(500);

        drivetrain.resetEncoders();
        sleep(500);

        drivetrain.moveDistance(6800, 0.5);
        telemetry.addData("Step 3 Complete", ".");

        drivetrain.turnAngle(-45, 0.5);

      telemetry.addData("Step 4 Complete", ".");

        intake.stop();
        sleep(500);

        /*
        telemetry.addData("Color: ", beaconScorer.getBeaconColor());

        while(beaconScorer.getBeaconColor() == "Null"){}
            drivetrain.arcadeDrive(0.3, 0);

        drivetrain.brake(0, 0);
        telemetry.addData("In position to measure", ".");//telemetry.addData("Step 5 Complete", ".");

        */

        drivetrain.moveDistance(1550, 0.5);

        climberScorer.score();
        sleep(1000);
        climberScorer.reset();

        /*
        beaconColor = beaconScorer.getBeaconColor();
        telemetry.addData("Beacon color is: ", beaconColor);

        drivetrain.moveDistance(800, -0.5);
        telemetry.addData("Step 7 Complete", ".");
        sleep(500);

        if(beaconColor == "Red") {
            beaconScorer.pressRightButton();
        }
        else if(beaconColor == "Blue") {
            beaconScorer.pressLeftButton();
        }
        else {
            telemetry.addData("ERROR!", "Could not read beacon color!");
        }
        telemetry.addData("Step 8 Complete", ".");

        while(beaconScorer.getBeaconColor() == "Null"){}
            drivetrain.arcadeDrive(0.3, 0);
        telemetry.addData("Pressed Button", ".");
        sleep(500);

        drivetrain.moveDistance(750, -0.5);
        telemetry.addData("Step 10 Complete", ".");
        sleep(500);

        beaconScorer.resetButtonPressers();
        telemetry.addData("Step 11 Complete", ".");
        */
    }
}


