package org.firstinspires.ftc.teamcode.code.constants;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.baseClasses.basePIDF;

public class PIDFConsts {
    public static final class slidePIDF extends basePIDF {
        slidePIDF slidePIDF;
        public slidePIDF(HardwareMap hardwareMap) {
            consts = new Consts(hardwareMap);
            slidePIDF = new slidePIDF(hardwareMap);

            p = 0.0089;
            i = 0;
            d = 0.0002;
            f = 0.024;
            ticksPerDegree = 537.7 / 360;

            controller = new PIDController(p, i, d);
        }

        @Override
        public double getCurrentPos() {
            return (consts.slideL.getCurrentPosition() + consts.slideR.getCurrentPosition()) / 2.;
        }

        @Override
        public void setMotors(int target) {
            controller.setPID(p, i, d);

            double power = slidePIDF.getPower(target);

            consts.slideR.setPower(power);
            consts.slideL.setPower(power);
        }
    }
}

