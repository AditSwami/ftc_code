package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous (name = "myAutoParkRight")
public class auto_Park_right extends LinearOpMode {
    Driver_setting drive = new Driver_setting();
    Auto_Slides slides = new Auto_Slides(5, 0.1, 0.1, 5);
    Intake_settings intake = new Intake_settings();
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() {
        drive.init(hardwareMap);
        slides.init(hardwareMap,telemetry);
        intake.init(":LefttServo",
                "rightServo",
                "clawServo",
                "armServo",
                "intakeBack",
                "claw2", hardwareMap,
                8);

        waitForStart();
        timer.reset();
        while(timer.milliseconds()<100){
            drive.power(1);
        }
        timer.reset();
        while(timer.milliseconds()<845){
            drive.turnRight(1);
        }
        timer.reset();
        while(timer.seconds()<1){
            drive.power(1);
        }
        drive.stopMotors();
    }
}