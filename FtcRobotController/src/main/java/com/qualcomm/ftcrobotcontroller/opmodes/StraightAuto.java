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
public class StraightAuto extends LinearOpMode{

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

        telemetry.addData("Start Autonomous", ".");

        intake.outward();
        drivetrain.moveDistance(3500, .5);
        intake.stop();
        telemetry.addData("Step 1 Complete", ".");




    }


}


