package org.firstinspires.ftc.teamcode.code.constants;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TeleConsts {
    private static HardwareMap hardwareMap;
    private static Consts consts;

    public TeleConsts(HardwareMap _hardwareMap) {
        hardwareMap = _hardwareMap;
        consts = new Consts(hardwareMap);
    }

    public static class Arm {
        public final double setGrab() {
            consts.arm.setPosition(0);
            consts.joint.setPosition(0.61);
            return 1;
        }

        public final double setRest() {
            consts.arm.setPosition(0);
            consts.joint.setPosition(0.1);
            return 1;
        }

        public final double setScore() {
            consts.arm.setPosition(1);
            consts.joint.setPosition(0.31);
            return 0.55;
        }
    }

    public static class Claw {
        public final void setGrab() {
            consts.claw.setPosition(0.4);
        }

        public final void setRelease() {
            consts.claw.setPosition(0);
        }
    }

    public static class Slide {
        public final void goUp() {
            consts.slideL.setPower(1);
            consts.slideR.setPower(1);
        }

        public final void goDown() {
            consts.slideL.setPower(-1);
            consts.slideR.setPower(-1);
        }

        public final void stay() {
            consts.slideL.setPower(0);
            consts.slideR.setPower(0);
        }

        public final void hang() {
            consts.slideL.setPower(0.2);
            consts.slideR.setPower(0.2);
        }
    }

    // really doesnt belong here, but fuck it we ball
    public final void fieldCentricDrive(Gamepad gamepad, double motorPower) {
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
}
