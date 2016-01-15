package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.robot.Drivetrain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Carlos on 1/12/2016.
 */
public class DriveTest  extends LinearOpMode {

    Drivetrain drivetrain = new Drivetrain();
    boolean isArcadeDrive = true;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain.init(hardwareMap);
        telemetry.addData("Calibration:", "Complete");

        waitForStart();

        while (opModeIsActive()) {

            if(gamepad1.a) {
                isArcadeDrive = true;
                telemetry.addData("Drive Mode: ", "Arcade");
            }

            if(gamepad1.b) {
                isArcadeDrive = false;
                telemetry.addData("Drive Mode: ", "Tank");
            }

            if(isArcadeDrive)
                drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);
            else
                drivetrain.tankDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);


            telemetry.addData("4. h", String.format("%03d", drivetrain.getHeading()));
        }
    }
}
