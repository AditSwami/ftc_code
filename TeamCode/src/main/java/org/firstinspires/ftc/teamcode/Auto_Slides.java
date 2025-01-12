package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Auto_Slides {
    public final double COUNTS_PER_INCH = 537.6;
    public static double kP = 0.01;
    public static double kI = 0;
    public static double kD = 0;
    public static double kF = 0;
    public DcMotorEx leftVSlideMotor;
    public DcMotorEx rightVSlideMotor;
    public DcMotorEx hMotor;
    public int minPos = 0;
    public int maxPos = 0;
    public int maxposH;
    public int minposH;
    double safety_distance = 5;

    public Auto_Slides(double kP, double kI, double kD, double kF) {
        this.minPos = minPos;
        this.maxPos = maxPos;
        this.maxposH = maxposH;
        this.minposH = minposH;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }

    public void init(HardwareMap hwMap, Telemetry telemetry) {// Initialize telemetry

        try {
            leftVSlideMotor = hwMap.get(DcMotorEx.class, "leftVSlide");
            rightVSlideMotor = hwMap.get(DcMotorEx.class, "rightVSlide");
            hMotor = hwMap.get(DcMotorEx.class, "hMotor");
            telemetry.addData("Motor Initialization", "Success");
        } catch (Exception e) {
            telemetry.addData("Motor Initialization Error", e.getMessage());
        }

        try {

            hMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftVSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightVSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftVSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightVSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            hMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightVSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftVSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            hMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(kP, kI, kD, kF));
            rightVSlideMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(kP, kI, kD, kF));
            leftVSlideMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(kP, kI, kD, kF));
        } catch (Exception e) {
            telemetry.addData("Motor Configuration Error", e.getMessage());
        }
        telemetry.update();
    }

    public void moveHSlidesAuto(int targetPos, Telemetry telemetry) {
        hMotor.setTargetPosition(targetPos);
        hMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hMotor.setPower(0.8);
        telemetry.addData("H Slide Target Position", targetPos);
        while (hMotor.isBusy()) {
            telemetry.addData("H Slide Current Position", hMotor.getCurrentPosition());
            telemetry.update();
        }
        hMotor.setPower(0);
        hMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveVSlideAuto(int targetPos, Telemetry telemetry) {
        rightVSlideMotor.setTargetPosition(-targetPos);
        leftVSlideMotor.setTargetPosition(targetPos);
        rightVSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftVSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightVSlideMotor.setPower(0.8);
        leftVSlideMotor.setPower(0.8);

        telemetry.addData("Vertical Slide Target Position", targetPos);
        while (rightVSlideMotor.isBusy() && leftVSlideMotor.isBusy()) {
            telemetry.addData("Right V Slide Current Position", rightVSlideMotor.getCurrentPosition());
            telemetry.addData("Left V Slide Current Position", leftVSlideMotor.getCurrentPosition());
            telemetry.update();
        }

        rightVSlideMotor.setPower(0);
        leftVSlideMotor.setPower(0);
        rightVSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftVSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}