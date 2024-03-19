package org.firstinspires.ftc.teamcode.code.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

@TeleOp
public class MotorTest extends OpMode {
    Consts consts;

    @Override
    public void init() {
        consts = new Consts(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a)
        {
            consts.backLeft.setPower(1);
        }
        if (gamepad1.b)
        {
            consts.backRight.setPower(1);
        }
        if (gamepad1.x)
        {
            consts.frontLeft.setPower(1);
        }
        if (gamepad1.y)
        {
            consts.frontRight.setPower(1);
        }

        consts.frontLeft.setPower(0);
        consts.frontRight.setPower(0);
        consts.backLeft.setPower(0);
        consts.backRight.setPower(0);
    }
}
