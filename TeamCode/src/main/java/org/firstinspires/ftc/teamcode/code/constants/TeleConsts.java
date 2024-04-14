package org.firstinspires.ftc.teamcode.code.constants;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.code.teleop.MalciousTeleOP;

public class TeleConsts {
    private static Consts consts;

    public TeleConsts(HardwareMap _hardwareMap) {
        consts = new Consts(_hardwareMap);
    }

    // really doesnt belong here, but fuck it we ball
    public final void fieldCentricDrive(Gamepad gamepad, double motorPower, MultipleTelemetry telem) {
        telem.addLine("Field Centric");
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = gamepad.right_stick_x;

        double botHeading = consts.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
        rotX = rotX * 1.1;
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        consts.frontLeft.setPower(frontLeftPower * motorPower);
        consts.backLeft.setPower(backLeftPower * motorPower);
        consts.frontRight.setPower(frontRightPower * motorPower);
        consts.backRight.setPower(backRightPower * motorPower);
    }

    public final void robotCentricDrive(Gamepad gamepad, double motorPower, MultipleTelemetry telem) {
        telem.addLine("Robot Centric");
        double y = -gamepad.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        consts.frontLeft.setPower(frontLeftPower * motorPower);
        consts.backLeft.setPower(backLeftPower * motorPower);
        consts.frontRight.setPower(frontRightPower * motorPower);
        consts.backRight.setPower(backRightPower * motorPower);
    }
}
