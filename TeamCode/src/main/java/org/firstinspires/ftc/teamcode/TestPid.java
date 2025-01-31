package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp(name = "testPide")
public class TestPid extends OpMode {
    DcMotorEx testMotor;

    @Override
    public void init() {
        testMotor = hardwareMap.get(DcMotorEx.class, "motor_botLeft");
//
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        testMotor.setPower(1);

        //testMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(0.2, 0, 0, 0));


    }

    @Override
    public void loop() {
        //testMotor.setTargetPosition(500);
        //testMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        telemetry.addData("ticks", testMotor.getCurrentPosition());
//        telemetry.update();
    }
}
