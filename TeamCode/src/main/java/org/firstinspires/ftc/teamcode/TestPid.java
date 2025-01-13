package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class TestPid extends OpMode {
    DcMotorEx testMotor;
    HardwareMap hardwareMap;

    @Override
    public void init() {
        testMotor = hardwareMap.get(DcMotorEx.class, "motor_botLeft");

        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        testMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(0.1, 0, 0, 0));


    }

    @Override
    public void loop() {
        testMotor.setTargetPosition(500);
    }
}
