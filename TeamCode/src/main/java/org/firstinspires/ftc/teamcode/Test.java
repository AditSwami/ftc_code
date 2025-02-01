package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp (name = "specimen")
public class Test extends OpMode {
    public Telemetry Telemetry;
    Driver_setting driver = new Driver_setting();
    SlideControl slides = new SlideControl(3000,0,600, 0, 0.01,0.1,0.0,0.0);
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
        slides.moveVSlide(gamepad2.dpad_up, gamepad2.dpad_down,1,telemetry);
        slides.moveHSlides(gamepad2.dpad_right, gamepad2.dpad_left, 1, telemetry);

        //release sample
        if( gamepad2.x){
            if (tf){
                telemetry.addData("bumper pressed", gamepad2.right_bumper);
                intake.clawRotateUpSpecimen();
                tf = false;
            }
        } else tf = true;

        //rotate vertcial claw up
        if( gamepad2.y){
            if (tf){
                telemetry.addData("bumper pressed", gamepad2.left_bumper);
                intake.clawRotateDown();
                tf = false;
            }
        } else tf = true;
    }
}