package org.firstinspires.ftc.teamcode.code.constants.hardwareConsts;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class Slide {
    Consts consts;

    public Slide(HardwareMap _hardwareMap) {
        consts = new Consts(_hardwareMap);
    }

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
        consts.slideL.setPower(0.3);
        consts.slideR.setPower(0.3);
    }
}
