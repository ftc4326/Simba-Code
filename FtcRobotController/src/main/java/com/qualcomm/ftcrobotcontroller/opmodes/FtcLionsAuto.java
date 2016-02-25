package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.*;


public class FtcLionsAuto extends OpMode{

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor Helix;
    DcMotor LinearSlide1;
    DcMotor LinearSlide2;
    DcMotor MidLeft;
    DcMotor MidRight;
    Servo arm1;
    Servo arm2;
    Servo wing1;
    Servo wing2;
    Servo flap;
    Servo wrist;



    @Override
    public void init() {

        frontLeft=hardwareMap.dcMotor.get("frontLeft");
        frontRight=hardwareMap.dcMotor.get("frontRight");
        backLeft=hardwareMap.dcMotor.get("backLeft");
        backRight=hardwareMap.dcMotor.get("backRight");
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        MidLeft=hardwareMap.dcMotor.get("midLeft");
        MidRight=hardwareMap.dcMotor.get("midRight");


        Helix=hardwareMap.dcMotor.get("Helix");
        Helix.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        LinearSlide2=hardwareMap.dcMotor.get("slideLeft");
        LinearSlide1=hardwareMap.dcMotor.get("slideRight");
        LinearSlide2.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        LinearSlide1.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        wing1=hardwareMap.servo.get("wing1");
        wing2=hardwareMap.servo.get("wing2");
        wing1.scaleRange(0, 1);
        wing2.scaleRange(0, 1);

        //sweep.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        arm1=hardwareMap.servo.get("armHold1");
        arm2=hardwareMap.servo.get("armHold2");

        flap=hardwareMap.servo.get("flap");

        wrist=hardwareMap.servo.get("wrist");


        arm1.scaleRange(0, 1);
        arm2.scaleRange(0, 1);

        arm1.setPosition(0);
        arm2.setPosition(0);
        flap.scaleRange(0, 1);
        wrist.scaleRange(0, 1);
        wrist.setPosition(0);
        flap.setPosition(0);
        wing1.setPosition(0);
        wing2.setPosition(0);

        arm1.setDirection(Servo.Direction.REVERSE);
        arm2.setDirection(Servo.Direction.FORWARD);
        frontLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        frontRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        MidLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        MidRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);





        frontLeft.setTargetPosition(0);
        frontRight.setTargetPosition(0);
        backLeft.setTargetPosition(0);
        backRight.setTargetPosition(0);

        MidLeft.setTargetPosition(0);
        MidRight.setTargetPosition(0);
//        MidLeft.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
//        MidRight.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
//        backLeft.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
//        backRight.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
//        frontLeft.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
//        frontRight.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);

        MidLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        MidRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        frontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        frontRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        backLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        backRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);


    }

    public void WaitForDriveMotor(int to){
        telemetry.addData("LOOP", "GOING");



       // while(Math.abs((float)MidLeft.getCurrentPosition()-to)>1&&Math.abs((float)MidRight.getCurrentPosition()-to)>1&&Math.abs((float)frontLeft.getCurrentPosition()-(float)frontLeft.getTargetPosition())>1&&Math.abs((float)backLeft.getCurrentPosition()-(float)backLeft.getTargetPosition())>1&&Math.abs((float)frontRight.getCurrentPosition()-(float)frontRight.getTargetPosition())>1&&Math.abs((float)backRight.getCurrentPosition()-(float)backRight.getTargetPosition())>1) {
    while(true){
    }
//        telemetry.addData("LOOP", "Done");

    }


    @Override
    public void start() {
        super.start();




    //MOVE 10 inches forward
    MidLeft.setPower(1);
    MidRight.setPower(1);
    backLeft.setPower(1);
    backRight.setPower(1);
    frontLeft.setPower(1);
    frontRight.setPower(1);
    MidLeft.setTargetPosition(inchToPos(100));
    MidRight.setTargetPosition(inchToPos(100));
    backLeft.setTargetPosition(inchToPos(100));
    backRight.setTargetPosition(inchToPos(100));
    frontLeft.setTargetPosition(inchToPos(100));
    frontRight.setTargetPosition(inchToPos(100));

    wait(10);
    //WaitForDriveMotor(100);

    MidLeft.setTargetPosition(inchToPos(0));
    MidRight.setTargetPosition(inchToPos(0));
    backLeft.setTargetPosition(inchToPos(0));
    backRight.setTargetPosition(inchToPos(0));
    frontLeft.setTargetPosition(inchToPos(10));
    frontRight.setTargetPosition(inchToPos(0));


    WaitForDriveMotor(0);
    MidLeft.setPower(0);
    MidRight.setPower(0);
    backLeft.setPower(0);
    backRight.setPower(0);
    frontLeft.setPower(0);
    frontRight.setPower(0);
}
    


    public void wait( int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    public int inchToPos(int i){
        return i*360*13;
    }


    @Override
    public void loop() {

    }
}
