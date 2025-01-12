package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.MAX_RPM;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.TRACK_WIDTH;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.encoderTicksToInches;
import static org.firstinspires.ftc.teamcode.drive.DriveConstants.rpmToVelocity;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleMecanumDrive extends MecanumDrive {
    private IMU imu;
    private DcMotorEx leftFront, leftRear, rightFront, rightRear;

    public SampleMecanumDrive(HardwareMap hardwareMap) {
        super(rpmToVelocity(MAX_RPM), 0.0, 0.0, TRACK_WIDTH);

        // Define Rev Hub Orientation
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // Initialize IMU
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(orientationOnRobot);

        // Initialize the IMU with the specified parameters
        imu.initialize(parameters);

        // Initialize motors
        leftFront = hardwareMap.get(DcMotorEx.class, "motor_topLeft");
        leftRear = hardwareMap.get(DcMotorEx.class, "motor_botLeft");
        rightFront = hardwareMap.get(DcMotorEx.class, "motor_topRight");
        rightRear = hardwareMap.get(DcMotorEx.class, "motor_botRight");

        // Set motor directions
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.FORWARD);

        // Set motors to use encoders
        leftRear.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        // Set zero power behavior
        leftRear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public double getRawExternalHeading() {
        // Use the IMU's orientation to get the robot's heading
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    @Override
    public Double getExternalHeadingVelocity() {
        // Use the IMU's angular velocity to get heading velocity
        return (double) imu.getRobotAngularVelocity(AngleUnit.RADIANS).zRotationRate;
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        // Get the encoder positions of each wheel
        double leftFrontPosition = encoderTicksToInches(leftFront.getCurrentPosition());
        double leftRearPosition = encoderTicksToInches(leftRear.getCurrentPosition());
        double rightFrontPosition = encoderTicksToInches(rightFront.getCurrentPosition());
        double rightRearPosition = encoderTicksToInches(rightRear.getCurrentPosition());

        // Return the wheel positions as a list
        return Arrays.asList(leftFrontPosition, leftRearPosition, rightFrontPosition, rightRearPosition);
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        // Set motor powers for mecanum drive
        leftFront.setPower(v);
        leftRear.setPower(v1);
        rightFront.setPower(v2);
        rightRear.setPower(v3);
    }

    // Additional methods for motor power, encoder ticks, etc.
}