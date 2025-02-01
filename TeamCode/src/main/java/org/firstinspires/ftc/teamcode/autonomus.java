package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Point;

import org.firstinspires.ftc.teamcode.PedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.PedroPathing.constants.LConstants;

@Autonomous (name = "myAuto", group = "Actual Auto")
public class autonomus extends OpMode {
    Auto_Slides slides = new Auto_Slides(0,1,2,3);
    Intake_settings intake = new Intake_settings();
    private Follower follower;
    private int pathState;

    private final Pose startingPose = new Pose(9.228, 60.949, Math.toRadians(0));

    private final Pose startPoint = new Pose(9.228, 60.949, Math.toRadians(0));
    private final Pose scorePoint = new Pose(41.656, 66.190, Math.toRadians(0));

    private final Pose controlPoint1 = new Pose(27.911, 50.241, Math.toRadians(0));
    private final Pose controlPoint2 = new Pose(60.835, 34.405, Math.toRadians(0));
    private final Pose getToPushPoint1 = new Pose(61.177, 22.557, Math.toRadians(0));

    private final Pose pushPoint1 = new Pose(17.316, 23.468, Math.toRadians(0));

    private final Pose controlPushPoint2 = new Pose(57.190, 26.316, Math.toRadians(0));
    private final Pose controlPushPoint3 = new Pose(64.367, 22.215, Math.toRadians(0));
    private final Pose getPushPoint2 = new Pose(57.304, 12.759, Math.toRadians(0));

    private final Pose pushPoint2 = new Pose(16.747, 12.076, Math.toRadians(0));

    private final Pose getPickPos = new Pose(8.316, 38.278, Math.toRadians(0));

    private final Pose scorePos1 = new Pose(41.810, 66.304, Math.toRadians(0));

    private Path scorePreload;
    private PathChain getToPush, push1, getToPus2, push2, getToScore, score1, getScore2, score2, getScore3, score3;

    public void buildPaths() {
                scorePreload = new Path(new BezierLine(new Point(startPoint), new Point(scorePoint)));
        scorePreload.setConstantHeadingInterpolation(Math.toRadians(0));

        getToPush = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(scorePoint), new Point(controlPoint1), new Point(controlPoint2), new Point(getToPushPoint1)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        push1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(getToPushPoint1), new Point(pushPoint1)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        getToPus2 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(pushPoint1), new Point(controlPushPoint2), new Point(controlPushPoint3), new Point(getPushPoint2)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        push2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(getPushPoint2), new Point(pushPoint2)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        getToScore = follower.pathBuilder()
                .addPath(new BezierLine(new Point(pushPoint2), new Point(getPickPos)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        score1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(getPickPos), new Point(scorePos1)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        getScore2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(scorePos1), new Point(getPickPos)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        score2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(getPickPos), new Point(scorePos1)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        getScore3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(scorePos1), new Point(getPickPos)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();

        score3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(getPickPos), new Point(scorePos1)))
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
    }

    public void autonomousPathUpdate(){
        switch (pathState) {
            case 0:
                follower.followPath(scorePreload);
                setPathState(1);
                break;
            case 1:

                /* You could check for
                - Follower State: "if(!follower.isBusy() {}"
                - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
                - Robot Position: "if(follower.getPose().getX() > 36) {}"
                */

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Preload */
                    intake.clawRotateDown();
                    slides.moveVSlideAuto(1000, telemetry);
                    intake.autoclawrelease();
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(getToPush,true);
                    setPathState(2);
                }
                break;
            case 2:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */
                    slides.moveVSlideAuto(0,telemetry);
                    intake.clawRotateUpSpecimen();
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(push1,true);
                    setPathState(3);
                }
                break;
            case 3:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(getToPus2,true);
                    setPathState(4);
                }
                break;
            case 4:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup2Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(push2,true);
                    setPathState(5);
                }
                break;
            case 5:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    slides.moveVSlideAuto(0,telemetry);
                    intake.autoClawGrab();
                    intake.clawRotateDown();
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(getToScore,true);
                    setPathState(6);
                }
                break;
            case 6:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup3Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */
                    slides.moveVSlideAuto(1000, telemetry);
                    intake.autoclawrelease();
                    intake.clawRotateUpSpecimen();
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(score1, true);
                    setPathState(7);
                }
                break;
            case 7:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */
                    slides.moveVSlideAuto(0,telemetry);
                    intake.autoClawGrab();
                    intake.clawRotateDown();

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are parked */
                    follower.followPath(getScore2,true);
                    setPathState(8);
                }
                break;
            case 8:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    slides.moveVSlideAuto(1000, telemetry);
                    intake.autoclawrelease();
                    intake.clawRotateUpSpecimen();
                    follower.followPath(score2, true);
                    setPathState(9);
                }
                break;
            case 9:
                if(!follower.isBusy()) {
                    slides.moveVSlideAuto(0,telemetry);
                    intake.autoClawGrab();
                    intake.clawRotateDown();
                    follower.followPath(getScore3, true);
                    setPathState(10);
                }
                break;

            case 10:
                if(!follower.isBusy()) {
                    slides.moveVSlideAuto(1000, telemetry);
                    intake.autoclawrelease();
                    intake.clawRotateUpSpecimen();
                    follower.followPath(score3, true);
                    setPathState(11);
                }
                break;

            case 11:
                if(!follower.isBusy()) {
                    setPathState(-1);
                }
                break;
        }
    }
    public void setPathState(int pState) {
        pathState = pState;
    }

    @Override
    public void init() {
        slides.init(hardwareMap, telemetry);
        intake.init(":LefttServo",
                "rightServo",
                "clawServo",
                "armServo",
                "intakeBack",
                "claw2", hardwareMap,
                8);
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startingPose);
        buildPaths();
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();

        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }
}
