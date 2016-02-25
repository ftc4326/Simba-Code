/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import android.bluetooth.BluetoothClass;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


public class FTCLionsTeleOp extends OpMode {
    /*
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    */
    DcMotor leftM;
    DcMotor rightM;
    DcMotor arm;
    DcMotor armY;
    DcMotor xArm;
    //DcMotor Helix;
   // DcMotor LinearSlide1;
  //  DcMotor LinearSlide2;
    //DcMotor MidLeft;
    //DcMotor MidRight;
    //Servo arm1;
    //Servo arm2;
    Servo wing1;
    Servo wing2;
    Servo flap;
    Servo allClear;
    //Servo wrist;
    //DcMotor sweep;

    //SERVO TRY
    final static double ARM_MIN_RANGE = 0.05;
    final static double ARM_MAX_RANGE = 0.90;
    //final static double CLAW_MIN_RANGE = 0.20;
    //final static double CLAW_MAX_RANGE = 0.7;
    double armPosition = 0.1;
    double armDelta = 0.1;
    //double clawPosition;
    //double clawDelta = 0.1;

    boolean started=false;
    public FTCLionsTeleOp() {
    }

    @Override
    public void start() {
        started=true;

        leftM=hardwareMap.dcMotor.get("backLeft");
        rightM=hardwareMap.dcMotor.get("backRight");

        arm=hardwareMap.dcMotor.get("arm");
        armY=hardwareMap.dcMotor.get("armY");
        xArm=hardwareMap.dcMotor.get("xArm");

        wing1=hardwareMap.servo.get("wing1");
        wing2=hardwareMap.servo.get("wing2");
        wing1.scaleRange(0, 1);
        wing2.scaleRange(0, 1);
        allClear=hardwareMap.servo.get("allClear");
        allClear.scaleRange(0, 1);

        flap=hardwareMap.servo.get("flap");

        flap.scaleRange(0, 1);

        leftM.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightM.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        arm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        armY.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        xArm.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        //super.start();
    }
    double startPos=0;
    @Override
    public void init() {

    }


    int count=0;
    int startn=0;
    boolean ran=false;
    boolean ran2=false;

    @Override
    public void loop() {
    if(started) {
    count++;

    //GAMEPAD 1 CONTROLS-------------------------------------------------------------------------------------------------------------------------

    leftM.setPower(gamepad2.right_stick_y);
    rightM.setPower(-gamepad2.left_stick_y);
    //telementry for autonimous purposes
    //telemetry.addData("Text", "***Robot Movement Data***");
    //telemetry.addData("right gamepad stick y ", gamepad1.right_stick_y);
    //telemetry.addData("-right gamepad stick y ", -gamepad1.right_stick_y);
    //telemetry.addData("-left gamepad stick y ", -gamepad1.left_stick_y);
    //telemetry.addData("left gamepad stick y ", gamepad1.left_stick_y);

    //wings
        //disable wings
            // if (Math.abs(gamepad1.right_stick_x) > 0) {
                 //wing1.setPosition(Range.clip(wing1.getPosition() + (gamepad2.right_stick_x), 0, 1));
            // //}
            // if (Math.abs(gamepad1.left_stick_x) > 0) {
                 //wing2.setPosition(Range.clip(wing2.getPosition() + (gamepad2.left_stick_x), 0, 1));
            // }



    //GAMEPAD 2 CONTROLS-------------------------------------------------------------------------------------------------------------------------

//ARM-ARM EXTENSION- ARM UP/DOWN
    arm.setPower(-gamepad1.left_stick_y);
    xArm.setPower(gamepad1.right_stick_y);
    armY.setPower(gamepad2.right_stick_x);

    //all clear
    if (gamepad1.right_trigger < 1) {
        allClear.setPosition(Range.clip(1-gamepad1.right_trigger, 0, 1));
    } else {
        allClear.setPosition(1);
    }

    //wing2.setPosition(Math.abs(gamepad2.left_stick_x));

    //flap
  /*  if (gamepad1.left_trigger < 1) {
        flap.setPosition(Range.clip(gamepad1.left_trigger, 0, 1));
    } else {
        flap.setPosition(1);
    }*/




    //COMBO CONTROLS----------------------------------------------------------------------------------------------------------------------------

    //E stop!!!!!!!!!
    if (gamepad1.left_bumper&&gamepad1.right_bumper&&gamepad2.left_bumper&&gamepad2.right_bumper) {
        leftM.setPower(0);
        rightM.setPower(0);
    }



/*
    //wrist??
    if (gamepad2.a) {
        wrist.setPosition(Range.clip(wrist.getPosition() + 0.01, 0, 1));
    } else if (gamepad2.b) {
        wrist.setPosition(Range.clip(wrist.getPosition() - 0.01, 0, 1));
    }

    //arm 1-2 i guess
    if (Math.abs(gamepad2.right_stick_y) > 0) {
        arm1.setPosition(Range.clip(arm1.getPosition() + ((-gamepad2.right_stick_y) / 100), 0, 1));
        arm2.setPosition(Range.clip(arm2.getPosition() + ((-gamepad2.right_stick_y) / 100), 0, 1));
    }*/

   /* //linear slide
    if (gamepad2.dpad_up) {
        LinearSlide1.setPower(1);
        //LinearSlide2.setPower(-1);
    } else if (gamepad2.dpad_down) {
        LinearSlide1.setPower(-1);
        //LinearSlide2.setPower(1);
    } else {
        LinearSlide1.setPower(0);
        //LinearSlide2.setPower(0);
    }*/

    //helical screw
   /* if (gamepad2.x == true) {
        Helix.setDirection(DcMotor.Direction.REVERSE);
        Helix.setPower(1);
    } else if (gamepad2.y == true) {
        Helix.setDirection(DcMotor.Direction.FORWARD);
        Helix.setPower(1);
    } else {
        Helix.setPower(0);
    }

   */// try {
        //RUN CODE FOR SERVOS?...


  //  } catch (Exception e) {

   // }

    //frontLeft.setPower(0);
    //frontRight.setPower(0);
    //armPosition = 0.0;
    /*
    float multiplier = 0;
    if (gamepad2.left_stick_y > 0) {
        multiplier = gamepad2.left_stick_y;

    } else if (gamepad2.left_stick_y < 0) {
        multiplier = gamepad2.left_stick_y;
    } else {
        multiplier = 0;
    }
    if (multiplier > 0) {
        //stick movement (wheel)
        backLeft.setPower(-gamepad1.left_stick_y * (1 - multiplier));
        backRight.setPower(-gamepad1.right_stick_y * (1 - multiplier));
        frontLeft.setPower(-gamepad1.left_stick_y);
        frontRight.setPower(-gamepad1.right_stick_y);
    } else if (multiplier < 0) {
        backLeft.setPower(-gamepad1.left_stick_y);
        backRight.setPower(-gamepad1.right_stick_y);
        frontLeft.setPower(-gamepad1.left_stick_y * (1 + multiplier));
        frontRight.setPower(-gamepad1.right_stick_y * (1 + multiplier));

    } else {
        backLeft.setPower(-gamepad1.left_stick_y);
        backRight.setPower(-gamepad1.right_stick_y);
        frontLeft.setPower(-gamepad1.left_stick_y);
        frontRight.setPower(-gamepad1.right_stick_y);

    }*/
        // MidLeft.setPower(-gamepad1.left_stick_y);
        //MidRight.setPower(gamepad1.right_stick_y);

    }
    }

    @Override
    public void stop() {

    }
}
