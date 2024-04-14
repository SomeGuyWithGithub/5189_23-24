package org.firstinspires.ftc.teamcode.code.constants.hardwareConsts;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class Arm {
    Consts consts;

    public Arm(HardwareMap _hardwareMap) {
        consts = new Consts(_hardwareMap);
    }

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
