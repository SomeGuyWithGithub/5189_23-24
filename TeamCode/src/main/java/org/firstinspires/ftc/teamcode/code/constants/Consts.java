package org.firstinspires.ftc.teamcode.code.constants;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class Consts {
    private final HardwareMap hardwareMap;
    public IMU imu;

    public DcMotor frontLeft, frontRight, backLeft, backRight, slideL, slideR;
    public Servo popper, arm, joint, claw;
    public RevBlinkinLedDriver lights;

    public Consts(HardwareMap _hardwareMap) {
        hardwareMap = _hardwareMap;

        setMotors();

        setServos();

        setLights();

        setImu();
    }

    private void setMotors() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");

        slideR = hardwareMap.dcMotor.get("slideRight");
        slideL = hardwareMap.dcMotor.get("slideLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        slideR.setDirection(DcMotorSimple.Direction.FORWARD);
        slideL.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void setServos() {
        popper = hardwareMap.get(Servo.class, "pickol");
        arm = hardwareMap.get(Servo.class, "idk1");
        joint = hardwareMap.get(Servo.class, "idk2");
        claw = hardwareMap.get(Servo.class, "idk3");

        popper.setDirection(Servo.Direction.FORWARD);
        arm.setDirection(Servo.Direction.REVERSE);
        joint.setDirection(Servo.Direction.FORWARD);
        claw.setDirection(Servo.Direction.FORWARD);
    }

    private void setLights() {
        lights = hardwareMap.get(RevBlinkinLedDriver.class, "light");
    }

    private void setImu() {
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(parameters);
        imu.resetYaw();
    }

    public void setInit() {
        arm.setPosition(0);
        joint.setPosition(0.1);
        claw.setPosition(0);
        popper.setPosition(0);
    }
}