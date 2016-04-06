package com.qualcomm.ftcrobotcontroller.opmodes.robot;

import com.qualcomm.robotcore.hardware.*;
/**
 * Created by Carlos on 11/12/2015.
 */
public class Lift {

    DcMotor liftMotor;

    int targetPosition = 0;
    int error = 0;
    double KP = 0.05;
    int ERROR_TOLERANCE = 5;

    public Lift(){    }

    public void init(HardwareMap hardwareMap) {
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
     }

    public void setSpeed(double speed){
        liftMotor.setPower(speed);
    }

    public void updatePosition(){

        if(Math.abs(error) > ERROR_TOLERANCE)
            error = targetPosition - liftMotor.getCurrentPosition();
        else
            error = 0;

        liftMotor.setPower(error * KP);
    }
}

