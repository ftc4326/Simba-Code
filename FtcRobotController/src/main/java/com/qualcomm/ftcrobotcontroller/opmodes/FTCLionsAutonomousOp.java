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
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;


public class FTCLionsAutonomousOp extends OpMode {
    DcMotor leftDrive;
    DcMotor rightDrive;
    //UltrasonicSensor sonar;
    //TouchSensor touch;


    Servo wing1;
    Servo wing2;
    Servo flap;

    public FTCLionsAutonomousOp() {

    }

    @Override
    public void loop() {
        // TELEMETRY
        telemetry.addData("Text", "***Robot Data***");
        telemetry.addData("Time", time);

        // TIME SAFETY
        if (time < 1) return;

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
        wait(2);
        drivePower(0.9);

        driveForward(50);

        stopRobot();
    }

    double startPos=0;
    @Override
    public void init() {
        leftDrive=hardwareMap.dcMotor.get("leftM");
        rightDrive=hardwareMap.dcMotor.get("rightM");

        //sonar = hardwareMap.ultrasonicSensor.get("sonar");
        //touch = hardwareMap.touchSensor.get("touch");


        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        wing1=hardwareMap.servo.get("wing1");
        wing2=hardwareMap.servo.get("wing2");
        wing1.scaleRange(0, 1);
        wing2.scaleRange(0, 1);

        flap=hardwareMap.servo.get("flap");

        flap.scaleRange(0, 1);
        leftDrive.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightDrive.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    @Override
    public void start() {

    }

    // DRIVE METHODS
    public void driveForward (int position) {
        leftDrive.setTargetPosition(position);
        rightDrive.setTargetPosition(position);
    }

    public void turnLeft (int position) {
        rightDrive.setTargetPosition(position);
    }
    public void turnRight (int position) {
        leftDrive.setTargetPosition(position);
    }

    public void drivePower (double power) {
        leftDrive.setPower(power);
        rightDrive.setPower(power);
    }

    public void wingMove (double position) {
        wing1.setPosition(position);
        wing2.setPosition(1 - position);
    }

    public void stopRobot() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        wingMove(1);
        wait(1);
    }
    public void climbers(int flapPos) {
        wait(1);
        flap.setPosition(flapPos / 2);
        flap.setPosition(Range.clip(gamepad2.left_trigger, 0, 1));
    }

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