package org.firstinspires.ftc.teamcode.code.baseClasses;

import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class basePIDF {
    protected static Consts consts;
    protected static double p, i, d, f;
    protected static double ticksPerDegree;
    protected static PIDController controller;

    public double getCurrentPos() {
        return 0;
    }

    public double getPower(int target) {
        double pid = controller.calculate(getCurrentPos(), target);
        double ff = Math.cos(Math.toRadians(target / ticksPerDegree)) * f;

        return pid + ff;
    }
}
