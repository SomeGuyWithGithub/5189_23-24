package org.firstinspires.ftc.teamcode.code.constants.hardwareConsts;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class Popper {
    Consts consts;

    public Popper(HardwareMap _hardwareMap) {
        consts = new Consts(_hardwareMap);
    }

    public final void goUp() {
        consts.popper.setPosition(0.6);
    }

    public final void goDown() {
        consts.popper.setPosition(0);
    }
}
