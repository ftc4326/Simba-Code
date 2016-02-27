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
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;


public class FTCLionsTeleOp extends OpMode {
    final boolean DEBUG = true;

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor armX1;
    DcMotor armX2;
    DcMotor armTheta;

    Servo wing1;
    Servo wing2;
    Servo climbers;
    Servo allClear;

    boolean started = false;

    public FTCLionsTeleOp() {

    }

    @Override
    public void start() {
        started = true;

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");

        armTheta = hardwareMap.dcMotor.get("armTheta");
        armX1 = hardwareMap.dcMotor.get("armX1");
        armX2 = hardwareMap.dcMotor.get("armX2");

        wing1 = hardwareMap.servo.get("wing1");
        wing2 = hardwareMap.servo.get("wing2");
        wing1.scaleRange(0, 1);
        wing2.scaleRange(0, 1);
        wing2.setDirection(Servo.Direction.REVERSE);

        allClear = hardwareMap.servo.get("allClear");
        allClear.scaleRange(0, 1);

        climbers = hardwareMap.servo.get("climber");
        climbers.scaleRange(0, 1);

        leftDrive.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightDrive.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        armTheta.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        armX1.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        armX2.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }

    @Override
    public void init() {

    }


    @Override
    public void loop() {
        if (DEBUG) {
            // TELEMETRY FOR JOYSTICK DEBUGGING
            telemetry.addData("Text", "***Robot Movement Data***");
            telemetry.addData("right gamepad stick y ", gamepad1.right_stick_y);
            telemetry.addData("left gamepad stick y ", gamepad1.left_stick_y);

            //getResources().getConfiguration().orientation;
        }


        if (started) {
            ////////////////////////////////
            //     GAMEPAD 1 CONTROLS     //
            ////////////////////////////////

            // TANK DRIVE
            leftDrive.setPower(gamepad1.right_stick_y);
            rightDrive.setPower(-gamepad1.left_stick_y);


            // WINGS
            wing1.setPosition(Range.clip(wing1.getPosition() + (gamepad1.right_stick_x), 0, 1));
            wing2.setPosition(Range.clip(wing2.getPosition() + (gamepad1.left_stick_x), 0, 1));


            ////////////////////////////////
            //     GAMEPAD 2 CONTROLS     //
            ////////////////////////////////

            // ARM
            armX1.setPower(-gamepad2.left_stick_y);
            armX2.setPower(gamepad2.right_stick_y);
            armTheta.setPower(gamepad2.right_stick_x);

            // ALL CLEAR
            if (gamepad2.right_trigger < 1) {
                allClear.setPosition(Range.clip(1 - gamepad2.right_trigger, 0, 1));
            } else {
                allClear.setPosition(1);
            }

            // CLIMBER MECHANISM
            if (gamepad2.left_trigger < 1) {
                climbers.setPosition(Range.clip(1 - gamepad2.left_trigger, 0, 1));
            } else {
                climbers.setPosition(1);
            }

            // E-STOP
            if (gamepad1.left_bumper && gamepad1.right_bumper && gamepad2.left_bumper && gamepad2.right_bumper) {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }
        }
    }

    @Override
    public void stop() {

    }
}
