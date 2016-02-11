package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Carlos on 2/6/2016.
 */
public class MotorTest extends LinearOpMode {
    public DcMotor motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.dcMotor.get("motor");

        waitForStart();

        while(opModeIsActive()){
            motor.setPower(gamepad1.left_stick_y);

            if(gamepad1.a)
                telemetry.addData("Button A", " is pressed");
            else
                telemetry.addData("Button A", "is not pressed");
        }
    }
}
