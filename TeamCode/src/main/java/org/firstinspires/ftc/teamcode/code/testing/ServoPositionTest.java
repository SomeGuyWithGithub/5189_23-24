package org.firstinspires.ftc.teamcode.code.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class ServoPositionTest extends LinearOpMode {
    public static boolean yellowArmInverted = false;
    public static double yellowArmPos = 0.0;
    @Override
    public void runOpMode() throws InterruptedException {
        Servo yellowArm = hardwareMap.get(Servo.class, "pickol");

        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            if (yellowArmInverted) {yellowArm.setDirection(Servo.Direction.REVERSE);}
            else                   {yellowArm.setDirection(Servo.Direction.FORWARD);}
            yellowArm.setPosition(yellowArmPos);
        }
    }
}
