package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp (name = "test")
public class test extends OpMode {
    public Telemetry Telemetry;
    Driver_setting driver = new Driver_setting();
    SlideControl slides = new SlideControl(3800,10,600, 0, 0.01,0.1,0.0,0.0);
    Intake_settings intake = new Intake_settings();
    boolean tf = true;

    @Override
    public void init() {
        driver.init(hardwareMap);
        slides.init(hardwareMap, telemetry);
        intake.init(":LefttServo",
                "rightServo",
                "clawServo",
                "armServo",
                "intakeBack",
                "claw2", hardwareMap,
                8);
    }

    @Override
    public void loop() {
        driver.moveRobot(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        slides.moveVSlide(gamepad1.dpad_up, gamepad1.dpad_down,1,telemetry);
        slides.moveHSlides(gamepad1.dpad_right, gamepad1.dpad_left, 1, telemetry);
        if( gamepad1.b){
            if (tf){
                intake.releaseSample(0.01);
                tf = false;
            }
        } else tf = true;

        if( gamepad1.x){
            if (tf){
                intake.grabSample(0.3);
                tf = false;
            }
        } else tf = true;

        if( gamepad1.y){
            if (tf){
                intake.rotateIntakeUp(0.8,0.2);
                tf = false;
            }
        } else tf = true;
        if( gamepad1.right_bumper){
            if (tf){
                intake.rotateIntakeDown(1, 0.0);
                tf = false;
            }
        } else tf = true;

        if( gamepad1.dpad_up){
            if (tf){
                intake.clawRotateDown();
                tf = false;
            }
        } else tf = true;
        if( gamepad1.dpad_down){
            if (tf){
                intake.clawRotateUp();
                tf = false;
            }
        } else tf = true;
        if( gamepad1.left_bumper){
            if (tf){
                intake.clawgrab(gamepad1.left_bumper);
                tf = false;
            }
        } else tf = true;
    }
}