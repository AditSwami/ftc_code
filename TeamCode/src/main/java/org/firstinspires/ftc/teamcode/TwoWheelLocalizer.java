package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.localization.TwoTrackingWheelLocalizer;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.util.Encoder;

import java.util.List;

public class TwoWheelLocalizer extends TwoTrackingWheelLocalizer {

    public static double TICKS_PER_REV = 537.7; // encoder ticks per revolution
    public static double WHEEL_RADIUS = 1.181; // odometry wheel radius
    public static double GEAR_RATIO = 1; // gear ratio for the odometry encoders

    public static double LATERAL_DISTANCE = 0.1; // distance between the tracking wheels TODO
    public static double FORWARD_OFFSET = 0.1; // offset of the forward tracking wheel TODO

    private Encoder leftEncoder, frontEncoder;

    public TwoWheelLocalizer(HardwareMap hardwareMap) {
        super(List.of(
                new Pose2d(0, LATERAL_DISTANCE / 2, 0), // left wheel position
                new Pose2d(FORWARD_OFFSET, 0, Math.toRadians(90)) // front wheel position
        ));

        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftEncoder"));
        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "frontEncoder"));

        leftEncoder.setDirection(Encoder.Direction.REVERSE);
    }

    @Override
    public List<Double> getWheelPositions() {
        return List.of(
                encoderTicksToInches(leftEncoder.getCurrentPosition()),
                encoderTicksToInches(frontEncoder.getCurrentPosition())
        );
    }

    private double encoderTicksToInches(int ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @Override
    public double getHeading() {
        return 0;
    }
}