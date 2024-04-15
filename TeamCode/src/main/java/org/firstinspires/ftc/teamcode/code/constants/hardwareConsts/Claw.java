package org.firstinspires.ftc.teamcode.code.constants.hardwareConsts;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class Claw {
    Consts consts;
    Lights lights;

    private boolean clawOpen = true;
    private double clawChangeTime = 0.;

    public Claw(HardwareMap _hardwareMap) {
        consts = new Consts(_hardwareMap);
        lights = new Lights(_hardwareMap);
    }

    public final void setClose() {
        consts.claw.setPosition(0.6);
    }

    public final void setOpen() {
        consts.claw.setPosition(0);
    }

    public final void switchPos(ElapsedTime runtime) {
        if (runtime.seconds() - clawChangeTime >= 0.15) {
            clawOpen = !clawOpen;
            if (clawOpen) {
                setOpen();
                lights.lightStates = Lights.LightStates.OPEN;
            } else {
                setClose();
                lights.lightStates = Lights.LightStates.CLOSE;
            }
            clawChangeTime = runtime.seconds();
        }
    }
}
