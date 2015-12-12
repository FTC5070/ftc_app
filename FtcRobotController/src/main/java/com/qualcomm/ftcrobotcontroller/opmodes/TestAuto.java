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
public class TestAuto extends LinearOpMode{

    Drivetrain drivetrain = new Drivetrain();
    Intake intake = new Intake();
    Autonomous autonomous = new Autonomous();


    double rightButtonServoPressed = 0.45;
    double leftButtonServoPressed = 0.57;

    boolean isBlue = false;


    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain.init(hardwareMap);
        intake.init(hardwareMap);
        autonomous.init(hardwareMap);
        telemetry.addData("Initialization Complete", "");


        waitForStart();

        telemetry.addData("Start Autonomous", ".");

        drivetrain.moveDistance(1500, 0.5);
        telemetry.addData("Step 1 Complete", ".");

        drivetrain.turnDistance(674, -0.5);
        telemetry.addData("Step 2 Complete", ".");

        intake.outward();

        drivetrain.moveDistance(4250, 0.5);
        telemetry.addData("Step 3 Complete", ".");

        intake.stop();

        drivetrain.turnDistance(625, -0.5);
        telemetry.addData("Step 4 Complete", ".");

        drivetrain.moveDistance(1000, 0.5);
        telemetry.addData("Step 5 Complete", ".");

        isBlue = (autonomous.sensorRGB.blue() > 0.5);
        telemetry.addData("Step 6 Complete", ".");

        drivetrain.moveDistance(500, -0.5);
        telemetry.addData("Step 7 Complete", ".");

        if (autonomous.sensorRGB.blue() == 1) {
            autonomous.leftButtonServo.setPosition(leftButtonServoPressed);
            TimeUnit.MILLISECONDS.sleep(2100);
            autonomous.leftButtonServo.setPosition(0.5);
        }
        else {
            autonomous.rightButtonServo.setPosition(rightButtonServoPressed);
            TimeUnit.MILLISECONDS.sleep(2100);
            autonomous.rightButtonServo.setPosition(0.5);
        }
        telemetry.addData("Step 8 Complete", ".");

        drivetrain.moveDistance(750, 0.5);
        telemetry.addData("Step 9 Complete", ".");


    }


}


