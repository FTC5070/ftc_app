package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.robot.Drivetrain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Carlos on 11/30/2015.
 */
public class EncoderTest extends LinearOpMode {

    Drivetrain drivetrain = new Drivetrain();
    int frontLeftValue, frontRightValue, backLeftValue, backRightValue, averageValue = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain.init(hardwareMap);
        telemetry.addData("Inititalization:", "Complete");


        waitForStart();

        while (opModeIsActive())  {

            drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);

            frontLeftValue = drivetrain.frontLeft.getCurrentPosition();
            frontRightValue = drivetrain.frontRight.getCurrentPosition();
            backLeftValue = drivetrain.backLeft.getCurrentPosition();
            backRightValue = drivetrain.backRight.getCurrentPosition();
            averageValue = (frontLeftValue + frontRightValue + backLeftValue +  backRightValue) / 4;

            if(gamepad1.a)
                drivetrain.resetEncoders();

            telemetry.addData("Front Left:", frontLeftValue);
            telemetry.addData("Front Right:", frontRightValue);
            telemetry.addData("Back Left:", backLeftValue);
            telemetry.addData("Back Right:", backRightValue);
            telemetry.addData("Average Value:", averageValue);

        }


    }
}