package org.firstinspires.ftc.teamcode.code.constants.hardwareConsts;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class Claw {
    Consts consts;

    public Claw(HardwareMap _hardwareMap) {
        consts = new Consts(_hardwareMap);
    }

    public final void setGrab() {
        consts.claw.setPosition(0.6);
    }

    public final void setRelease() {
        consts.claw.setPosition(0);
    }
}
