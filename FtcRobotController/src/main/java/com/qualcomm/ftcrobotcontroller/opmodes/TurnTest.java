package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.ftcrobotcontroller.opmodes.robot.*;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Nikhil on 12/12/2015.
 */
public class TurnTest extends LinearOpMode {

    Drivetrain drivetrain = new Drivetrain();
    int currentHeading;
    int goalHeading;
    int targetAngle;
    double speed = -0.75;
    int rotationalVelocity;
    long dt = 50;

    int xVal, yVal, zVal = 0;
    int heading = 0;
    int error = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);
        telemetry.addData("Drivetrain Init Complete", "");

        waitForStart();


        int currentHeading = drivetrain.getHeading();
        telemetry.addData("Initial Heading: ", currentHeading);

        turnAngle(45, 0.5);
        telemetry.addData("Turn Complete 1", "");


        turnAngle(-45, -0.5);
        telemetry.addData("Turn Complete 2", "");


        while (opModeIsActive()) {

        }
    }

    void turnAngle(int targetAngle, double speed){
        goalHeading = (currentHeading + targetAngle) % 360;

        if(goalHeading < 0)
            goalHeading += 360;

        while (Math.abs(goalHeading - drivetrain.getHeading()) > drivetrain.headingTolerance) {
            drivetrain.arcadeDrive(0, speed);
        }

        drivetrain.brake();
    }
}
