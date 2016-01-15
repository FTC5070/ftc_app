package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.ftcrobotcontroller.opmodes.robot.*;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Carlos on 11/11/2015.
 */
public class ClassifiedTeleOp extends OpMode{


    Lift lift = new Lift();
    Drivetrain drivetrain = new Drivetrain();
    Intake intake = new Intake();
    Dumper dumper = new Dumper();
    ZiplineScorer ziplineScorer = new ZiplineScorer();
    ClimberScorer climberScorer = new ClimberScorer();
    BeaconScorer beaconScorer = new BeaconScorer();


    @Override
    public void init() {
        lift.init(hardwareMap);
        try {
            drivetrain.init(hardwareMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        intake.init(hardwareMap);
        dumper.init(hardwareMap);
        ziplineScorer.init(hardwareMap);
        climberScorer.init(hardwareMap);
        beaconScorer.init(hardwareMap);
    }

    @Override
    public void loop() {

        /*
        //Left and right sticks are switched for driver preference
        drivetrain.tankDrive(-gamepad1.right_stick_y, -gamepad1.left_stick_y);
        lift.setSpeed(-gamepad2.left_stick_y);
        */

        //if (lift.isShiftedHigh) {
        drivetrain.tankDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);//drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);
        lift.setSpeed(-gamepad2.left_stick_y);
        /*} else {
            lift.setSpeed(gamepad2.left_stick_y * 3.0 / 4.0);
            drivetrain.arcadeDrive(gamepad2.left_stick_y, 0);
        }
        */


        /*
        if(gamepad2.dpad_up){
            lift.isShiftedHigh = true;

            drivetrain.arcadeDrive(0,0);
            lift.setSpeed(0);

            lift.setGear("High");
        }

        if(gamepad2.dpad_down){
            lift.isShiftedHigh = false;

            drivetrain.arcadeDrive(0, 0);
            lift.setSpeed(0);

            lift.setGear("Low");
        }
        */


        if(gamepad1.left_bumper)
            intake.inward();

        if(gamepad1.right_bumper)
            intake.outward();

        if(!(gamepad1.right_bumper || gamepad1.left_bumper))
            intake.stop();



        if(gamepad2.left_bumper)
            ziplineScorer.setLeftOut();
        else
            ziplineScorer.setLeftIn();

        if(gamepad2.right_bumper)
            ziplineScorer.setRightOut();
        else
            ziplineScorer.setRightIn();


        if(gamepad2.a)
            climberScorer.score();

        if(gamepad2.b)
            climberScorer.reset();

        if(gamepad2.y)
            climberScorer.raise();;


        if(gamepad2.dpad_left)
            dumper.setLeft();

        if(gamepad2.dpad_right)
            dumper.setRight();

        if(!(gamepad2.dpad_left || gamepad2.dpad_right))
            dumper.setNeutral();

    }
}
