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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


public class FTCLionsAutonomousOp extends OpMode {
/*
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
*/
    DcMotor leftM;
    DcMotor rightM;
   // DcMotor Helix;
   // DcMotor LinearSlide1;
   // DcMotor LinearSlide2;
   // DcMotor MidLeft;
   // DcMotor MidRight;
   // Servo arm1;
    //Servo arm2;
    Servo wing1;
    Servo wing2;
    Servo flap;
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


    public FTCLionsAutonomousOp() {
    }

    @Override
    public void loop() {
        //super.start();

        //stick movement (wheel)

        //telementry for autonimous purposes
        telemetry.addData("Text", "***Robot Data***");
        telemetry.addData("Time", time);

        if (time > 1) {
            //wings
            wingMove(1);

            wait(1);
            drivePower(1);
            driveForward(45);
            wait(1);
            driveForward(45);

            wait(1);
            drivePower(0.5);
            turnLeft(25);

            wait(1);
            //helical(1);
            climbers(1);

            wait(1);
            drivePower(0.3);
            driveForward(-20);

            wait(1);
            drivePower(0.6);
            turnLeft(25);

            wait(1);
            drivePower(20);
           /* wait(2);
            drivePower(0.9);
            // MidLeft.setPower(1);
            //MidRight.setPower(1);
            driveForward(50);*/
            //MidLeft.setTargetPosition(inchToPos(50));
            //MidRight.setTargetPosition(inchToPos(50));
            stopRobot();
        }

//This code does not work at all//

        //flap
            //flap.setPosition(Range.clip(gamepad2.left_trigger,0,1));
            flap.setPosition(0);

        //wrist??
           /* wrist.setPosition(0);

        //arm 1-2 i guess
            arm1.setPosition(0);
            arm2.setPosition(0);

        //linear slide
            LinearSlide1.setTargetPosition(0);
            LinearSlide2.setTargetPosition(0);

        //helical screw
            Helix.setTargetPosition(-5);
*/
        //E stop!!!!!!!!!
     /*   if (gamepad1.left_trigger > 0 && gamepad1.right_trigger > 0 && gamepad2.right_stick_button && gamepad2.left_stick_button) {
            backLeft.setTargetPosition(0);
            backRight.setTargetPosition(0);
            frontLeft.setTargetPosition(0);
            frontRight.setTargetPosition(0);
            //armPosition = 0.0;

        }
        */
    }

    double startPos=0;
    @Override
    public void init() {
        leftM=hardwareMap.dcMotor.get("leftM");
        rightM=hardwareMap.dcMotor.get("rightM");
        //backLeft=hardwareMap.dcMotor.get("backLeft");
        //backRight=hardwareMap.dcMotor.get("backRight");
//Fix wheels
        rightM.setDirection(DcMotor.Direction.REVERSE);
        //frontRight.setDirection(DcMotor.Direction.REVERSE);

        //MidLeft=hardwareMap.dcMotor.get("midLeft");
        //MidRight=hardwareMap.dcMotor.get("midRight");

        //Helix=hardwareMap.dcMotor.get("Helix");
        //Helix.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        //LinearSlide2=hardwareMap.dcMotor.get("slideLeft");
        //LinearSlide1=hardwareMap.dcMotor.get("slideRight");
        //LinearSlide2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //LinearSlide1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        wing1=hardwareMap.servo.get("wing1");
        wing2=hardwareMap.servo.get("wing2");
        wing1.scaleRange(0, 1);
        wing2.scaleRange(0, 1);

        //sweep.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

       // arm1=hardwareMap.servo.get("armHold1");
        //arm2=hardwareMap.servo.get("armHold2");

        flap=hardwareMap.servo.get("flap");
        /*wrist=hardwareMap.servo.get("wrist");


        arm1.scaleRange(0, 1);
        arm2.scaleRange(0, 1);
        arm1.setPosition(0);
        arm2.setPosition(0);*/

        flap.scaleRange(0, 1);
        //wrist.scaleRange(0, 1);

       // arm1.setDirection(Servo.Direction.REVERSE);
        //arm2.setDirection(Servo.Direction.FORWARD);

        leftM.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightM.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
       // backLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
       // backRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //MidLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        //MidRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }


    int count=0;
    int startn=0;
    boolean ran=false;
    boolean ran2=false;

    @Override
    public void start() {

    }
// drive methods
    public void driveForward (int position) {
        leftM.setTargetPosition(position);
        rightM.setTargetPosition(position);
    }

    public void turnLeft (int position) {
        rightM.setTargetPosition(position);
    }
    public void turnRight (int position) {
        leftM.setTargetPosition(position);
    }

    public void drivePower (double power) {
        leftM.setPower(power);
        rightM.setPower(power);
    }

    public void wingMove (double position) {
        wing1.setPosition(position);
        wing2.setPosition(1 - position);
    }

    public void stopRobot() {
        leftM.setPower(0);
        rightM.setPower(0);
        wingMove(1);
        wait(1);
    }
    public void climbers(int flapPos) {
        wait(1);
        flap.setPosition(flapPos / 2);
        flap.setPosition(Range.clip(gamepad2.left_trigger, 0, 1));
    }
/*
    public void helical(int position) {
        if (position == 1) {
            Helix.setDirection(DcMotor.Direction.FORWARD);
            Helix.setPower(position);
        }
        if (position == 0)
            Helix.setDirection(DcMotor.Direction.REVERSE);

        Helix.setTargetPosition(position);
    }*/

    public void wait(int time) {
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {

    }
}
