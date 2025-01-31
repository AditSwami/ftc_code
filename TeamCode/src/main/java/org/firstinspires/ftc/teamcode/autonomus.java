package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.PedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.PedroPathing.constants.LConstants;


@Autonomous (name = "myAuto", group = "Actual Auto")
public class autonomus extends OpMode {
    private Follower follower;
    private int pathState;
    private final Pose startPose = new Pose(95,24,Math.toRadians(270));
    private final Pose scorePose = new Pose(14,27,Math.toRadians(270));
    private final Pose rallyPose = new Pose(8,15,Math.toRadians(270));
    private final Pose pushPose1 = new Pose (20,4, Math.toRadians(270));
    private final Pose endPushPose1 = new Pose(10,15, Math.toRadians(270));
    private final Pose pushPose2 = new Pose (10,15,Math.toRadians(270));
    private final Pose endPushPose2 = new Pose(10,15, Math.toRadians(270));
    private final Pose pushPose3 = new Pose(10,20,Math.toRadians(270));
    private final Pose scorePose1 = new Pose(10,20,Math.toRadians(270));
    private final Pose scorePose2 = new Pose(10,20,Math.toRadians(270));
    private final Pose scorePose3 = new Pose(10,20,Math.toRadians(270));
    private final Pose scorePose4 = new Pose(10,20,Math.toRadians(270));

    private Path scorePreload;
    private PathChain rally, push1, endPush1, push2, endPush2, push3,score1,score2,score3,score4;

    public void buildPaths(){
        scorePreload = new Path(new BezierLine(new Point(startPose),new Point(scorePose)));
        scorePreload.setLinearHeadingInterpolation(startPose.getHeading(),scorePose.getHeading());

        rally = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(scorePose), new Point(rallyPose)))
                .build();

        push1 = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(rallyPose), new Point(pushPose1)))
                .build();

        endPush1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(pushPose1), new Point(endPushPose1)))
                .build();

        push2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(endPushPose1), new Point(pushPose2)))
                .build();

        endPush2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(pushPose2), new Point(endPushPose2)))
                .build();


    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}