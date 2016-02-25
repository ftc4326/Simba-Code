package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by basementlions on 2/20/16.
 */
public class TeleOpModeStates extends OpMode {


    DcMotor DLeft;
    DcMotor DRight;
    DcMotor ArmRotate;
    DcMotor ArmExtend1;
    DcMotor ArmExtend2;
    Servo Wing1;
    Servo Wing2;
    Servo AllClear;
    Servo Climbers;


    @Override
    public void start() {
DLeft=hardwareMap.dcMotor.get("DLeft");
        DRight=hardwareMap.dcMotor.get("DRight");
        ArmRotate=hardwareMap.dcMotor.get("ArmRotate");
        ArmExtend1=hardwareMap.dcMotor.get("ArmExtend1");
        ArmExtend2=hardwareMap.dcMotor.get("ArmExtend2");

        DLeft.setDirection(DcMotor.Direction.REVERSE);
        DRight.setDirection(DcMotor.Direction.FORWARD);



    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
