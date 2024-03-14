package org.firstinspires.ftc.teamcode.code.constants;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.baseClasses.basePIDF;

public class PIDFConsts {
    public static final class slidePIDF extends basePIDF {
        public slidePIDF(HardwareMap hardwareMap) {
            consts = new Consts(hardwareMap);

            p = 0;
            i = 0;
            d = 0;
            f = 0;
            ticksPerDegree = 537.7 / 360;

            controller = new PIDController(p, i, d);
        }

        @Override
        public double getCurrentPos() {
            return (consts.slideL.getCurrentPosition() + consts.slideR.getCurrentPosition()) / 2.;
        }
    }
}

