package com.qualcomm.ftcrobotcontroller.opmodes.robot;
import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Carlos on 12/18/2015.
 */
public class ClimberScorer {
    Servo climberServo;

    double scoringPosition = 0.0;
    double upwardPosition = 0.5;
    double initialPosition = 1.0;

    public ClimberScorer(){}

    public void init(HardwareMap hardwareMap){
        climberServo = hardwareMap.servo.get("climberServo");
        climberServo.setPosition(initialPosition);
    }

    public void score(){
        climberServo.setPosition(scoringPosition);
    }

    public void reset(){
        climberServo.setPosition(initialPosition);
    }

    public void raise(){
        climberServo.setPosition(upwardPosition);
    }



}
