package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake_settings {
    public Servo clawServ;
    public Servo clawRotateR;
    public Servo clawRotateL;
    public Servo intakePuller_L;
    public Servo intakeAssembPuller;
    public Servo sampleClaw;
    public DcMotorEx surgSpinner;
    boolean isMoving = false;
    private boolean isIntakeBack = false; // Track state
    public double startingServoPos;
    private ElapsedTime intakeTimer = new ElapsedTime(); // Make the timer persistent
    private boolean isIntakeOut = false;
    public boolean isTargetPos = false;
    private Telemetry telemetry; // Instance-specific telemetry

    public void init(String clawRotL, String clawRotR, String clawGrad, String servoName2, String servoPulBack, String claw2, HardwareMap hwMap, double startingPos) {
        intakePuller_L = hwMap.get(Servo.class, servoName2);
        sampleClaw = hwMap.get(Servo.class, claw2);
        intakeAssembPuller = hwMap.get(Servo.class, servoPulBack);
        clawServ = hwMap.get(Servo.class, clawGrad);
        clawRotateL = hwMap.get(Servo.class, clawRotL);
        clawRotateR = hwMap.get(Servo.class, clawRotR);

        startingServoPos = startingPos;

    }

    public void rotateIntakeUp(double pos, double pos2){
        intakePuller_L.setPosition(pos);
        intakeAssembPuller.setPosition(pos2);
    }

    public void rotateIntakeDown(double pos, double pos2){
        intakeAssembPuller.setPosition(pos);
        intakePuller_L.setPosition(pos2);
    }

    public void grabSample(double pos){
        sampleClaw.setPosition(pos);
    }

    public void releaseSample(double pos){
        sampleClaw.setPosition(pos);
    }

    public void clawRotateUp(){
        clawRotateR.setPosition(0.63);
        clawRotateL.setPosition(0.17);
    }

    public void clawRotateUpSpecimen(){
        clawRotateR.setPosition(0.75);
        clawRotateL.setPosition(0.25);
    }

    public void clawRotateDown(){
        clawRotateR.setPosition(0.5);
        clawRotateL.setPosition(0.5);
    }

    public void clawgrab(boolean dpad) {
        if (dpad) {
            clawServ.setPosition(0.4);
        }
    }
    public void autoclawgrab() {
            clawServ.setPosition(0.4);
    }
    public void clawrelease(boolean dpad){
        if(dpad){
            clawServ.setPosition(0);
        }
    }
    public void autoclawrelease(){

            clawServ.setPosition(0);
    }
}