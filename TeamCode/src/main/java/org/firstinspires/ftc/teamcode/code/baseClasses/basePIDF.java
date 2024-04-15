package org.firstinspires.ftc.teamcode.code.baseClasses;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.constants.Consts;
import org.firstinspires.ftc.teamcode.code.constants.PIDConsts.SlidePID;

public abstract class basePIDF {
    protected Consts consts;
    protected double p, i, d, f;
    protected double ticksPerDegree;
    public double power;
    protected static PIDController controller;

    public abstract double getCurrentPos();

    protected abstract void setMotors(double power);

    public void run(int target) {
        double pid = controller.calculate(getCurrentPos(), target);
        double ff = Math.cos(Math.toRadians(target / ticksPerDegree)) * f;

        power = pid + ff;
        setMotors(power);
    }
}
