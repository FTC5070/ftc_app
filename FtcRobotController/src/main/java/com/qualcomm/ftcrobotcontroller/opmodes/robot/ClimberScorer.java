package com.qualcomm.ftcrobotcontroller.opmodes.robot;
import com.qualcomm.robotcore.hardware.*;

/**
 * Created by Carlos on 12/18/2015.
 */
public class ClimberScorer {
    Servo leftClimberServo;
    double leftScoringPosition = 0.0;
    double leftUpwardPosition = 0.5;
    double leftInitialPosition = 1.0;

    Servo rightClimberServo;
    double rightScoringPosition = 0.0;
    double rightUpwardPosition = 0.5;
    double rightInitialPosition = 1.0;

    public ClimberScorer(){}

    public void init(HardwareMap hardwareMap){
        leftClimberServo = hardwareMap.servo.get("leftClimberServo");
        leftClimberServo.setPosition(leftInitialPosition);

        rightClimberServo = hardwareMap.servo.get("rightClimberServo");
        rightClimberServo.setPosition(rightInitialPosition);
    }

    public void scoreLeftSide(){
        leftClimberServo.setPosition(leftScoringPosition);
    }

    public void resetLeftSide(){
        leftClimberServo.setPosition(leftInitialPosition);
    }

    public void raiseLeftSide(){
        leftClimberServo.setPosition(leftUpwardPosition);
    }

    public void rightSide(){
        rightClimberServo.setPosition(rightScoringPosition);
    }

    public void resetRightSide(){
        rightClimberServo.setPosition(rightInitialPosition);
    }

    public void raiseRightSide(){
        rightClimberServo.setPosition(rightUpwardPosition);
    }



}
