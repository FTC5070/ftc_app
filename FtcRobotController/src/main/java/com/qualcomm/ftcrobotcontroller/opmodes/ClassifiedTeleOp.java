package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.ftcrobotcontroller.opmodes.robot.*;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Carlos on 11/11/2015.
 */
public class ClassifiedTeleOp extends OpMode{

    //Lift lift = new Lift();
    Drivetrain drivetrain = new Drivetrain();
    Intake intake = new Intake();
    //Dumper dumper = new Dumper();
    ZiplineScorer ziplineScorer = new ZiplineScorer();
    //ClimberScorer climberScorer = new ClimberScorer();
    BeaconScorer beaconScorer = new BeaconScorer();

    @Override
    public void init() {
        //lift.init(hardwareMap);
        try {
            drivetrain.init(hardwareMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        intake.init(hardwareMap);
        //dumper.init(hardwareMap);
        ziplineScorer.init(hardwareMap);
        //climberScorer.init(hardwareMap);
        beaconScorer.init(hardwareMap);

    }

    @Override
    public void loop() {

        /*
        //Left and right sticks are switched for driver preference
        drivetrain.tankDrive(-gamepad1.right_stick_y, -gamepad1.left_stick_y);
        lift.setSpeed(-gamepad2.left_stick_y);
        */

        //if(Math.abs(gamepad1.right_trigger) > .15)
         //   drivetrain.tankDrive(-gamepad1.left_stick_y / 3.0, -gamepad1.right_stick_y / 3.0);//drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);
        //else
        drivetrain.tankDrive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);//drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);

        if(gamepad1.right_bumper)
            drivetrain.raiseMountainBrake();

        if(Math.abs(gamepad1.right_trigger) > .15)
            drivetrain.lowerMountainBrake();
        /*
        if(Math.abs(gamepad2.right_trigger) > .15)
            lift.setSpeed(-gamepad2.right_stick_y / 3.0);
        else
            lift.setSpeed(-gamepad2.right_stick_y);
        */

        if(gamepad1.left_bumper)
            intake.inward();

        if(Math.abs(gamepad1.left_trigger) > .15)
            intake.outward();

        if(!(gamepad1.left_bumper || Math.abs(gamepad1.left_trigger) > .15))
            intake.stop();


        if(gamepad2.right_bumper)
            ziplineScorer.setLeftOut();
        else
            ziplineScorer.setLeftIn();

        if(gamepad2.left_bumper)
            ziplineScorer.setRightOut();
        else
            ziplineScorer.setRightIn();


        if(Math.abs(gamepad2.left_trigger) > .15)
            beaconScorer.leftButtonServo.setPosition(beaconScorer.leftButtonPressedPosition);
        else
            beaconScorer.leftButtonServo.setPosition(beaconScorer.leftButtonInitPosition);

        if(Math.abs(gamepad2.right_trigger) > .15)
            beaconScorer.rightButtonServo.setPosition(beaconScorer.rightButtonPressedPosition);
        else
            beaconScorer.rightButtonServo.setPosition(beaconScorer.rightButtonInitPosition);

        /*
        if(gamepad2.a)
            climberScorer.score();

        if(gamepad2.b)
            climberScorer.reset();

        if(gamepad2.y)
            climberScorer.raise();


        if(gamepad2.dpad_left)
            dumper.sweepLeft();

        if(gamepad2.dpad_right)
            dumper.sweepRight();

        if(gamepad2.dpad_up)
            dumper.reset();
            */
        telemetry.addData("Left Beacon Color Red Value", beaconScorer.leftColorSensor.red());
        telemetry.addData("Left Beacon Color Blue Value", beaconScorer.leftColorSensor.blue());
        telemetry.addData("Right Beacon Color Red Value", beaconScorer.leftColorSensor.red());
        telemetry.addData("Right Beacon Color Blue Value", beaconScorer.leftColorSensor.blue());
    }
}

