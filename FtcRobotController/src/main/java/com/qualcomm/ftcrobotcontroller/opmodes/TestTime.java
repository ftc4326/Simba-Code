package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by basementlions on 1/30/16.
 */
public class TestTime extends OpMode {


    @Override
    public void init() {

    }

    @Override
    public void loop() {
        telemetry.addData("Time", time);
    }

    @Override
    public void start() {
        super.start();
    }
}
