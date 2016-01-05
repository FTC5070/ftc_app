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
    Autonomous autonomous = new Autonomous();
    ClimberScorer climberScorer = new ClimberScorer();
    Dumper dumper = new Dumper();



    double rightButtonServoPressed = 0.45;
    double leftButtonServoPressed = 0.57;

    boolean isRed = false;


    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain.init(hardwareMap);
        telemetry.addData("Drivetrain Init Complete", "");
        intake.init(hardwareMap);
        telemetry.addData("Intake Init Complete", "");
        autonomous.init(hardwareMap);
        telemetry.addData("Autonomous Init Complete", "");
        autonomous.rightButtonServo.setPosition(0.5);
        telemetry.addData("Right Button Servo Init Complete", "");
        autonomous.leftButtonServo.setPosition(0.5);
        telemetry.addData("Left Button Servo Init Complete", "");
        climberScorer.init(hardwareMap);
        telemetry.addData("Climber Servo Init Complete", "");
        dumper.init(hardwareMap);


        waitForStart();

        sleep(1000);
        drivetrain.getHeading();

        telemetry.addData("Start Autonomous", ".");

        intake.outward();
        drivetrain.moveDistance(1200, .5);
        telemetry.addData("Step 1 Complete", ".");

        sleep(500);
        while(drivetrain.getHeading() > 315 || drivetrain.getHeading() < 305) {
            drivetrain.arcadeDrive(0, 0.5);
            telemetry.addData("Heading ", String.format("%03d", drivetrain.getHeading()));
        }

        drivetrain.arcadeDrive(0, 0);
        telemetry.addData("Heading ", String.format("%03d", drivetrain.getHeading()));
        telemetry.addData("Step 2 Complete", ".");
        sleep(500);

        drivetrain.resetEncoders();
        sleep(500);

        drivetrain.moveDistance(3800, 0.5);
        telemetry.addData("Step 3 Complete", ".");

        while(drivetrain.getHeading() > 270 || drivetrain.getHeading() < 260) {
            drivetrain.arcadeDrive(0, -0.5);
            telemetry.addData("Heading ", String.format("%03d", drivetrain.getHeading()));
        }

        drivetrain.arcadeDrive(0, 0);
        telemetry.addData("Heading ", String.format("%03d", drivetrain.getHeading()));
        telemetry.addData("Step 4 Complete", ".");

        intake.stop();
        sleep(500);

        drivetrain.moveDistance(250, 0.5);
        telemetry.addData("Step 5 Complete", ".");
        sleep(500);

        climberScorer.score();
        sleep(1000);
        climberScorer.reset();

        //isRed = (autonomous.sensorRGB.red() > 0.5);
        //telemetry.addData("Step 6 Complete", ".");

        drivetrain.moveDistance(500, -0.5);
        telemetry.addData("Step 7 Complete", ".");
        sleep(500);

        /*if (isRed) {
            autonomous.PressLeftButton();
        }
        else {
            autonomous.PressRightButton();
        }
        telemetry.addData("Step 8 Complete", ".");

        drivetrain.moveDistance(750, 0.5);
        telemetry.addData("Step 9 Complete", ".");
        sleep(500);*/



    }


}


