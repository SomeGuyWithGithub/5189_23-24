package org.firstinspires.ftc.teamcode.code.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.ejml.equation.IntegerSequence;
import org.firstinspires.ftc.teamcode.code.constants.Consts;

@Config
@TeleOp
public class ServoPositionTest extends OpMode {
    Consts consts;
    public static boolean yellowArmInverted = false;
    public static double yellowArmPos = 0.0;


    @Override
    public void init() {
        consts = new Consts(hardwareMap);
    }

    @Override
    public void loop() {
        if (yellowArmInverted) {consts.popper.setDirection(Servo.Direction.REVERSE);}
        else                   {consts.popper.setDirection(Servo.Direction.FORWARD);}
        consts.popper.setPosition(yellowArmPos);
    }
}
