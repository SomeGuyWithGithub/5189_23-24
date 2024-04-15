package org.firstinspires.ftc.teamcode.code.constants.PIDConsts;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.baseClasses.basePIDF;
import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class SlidePID extends basePIDF {
    public SlidePID(HardwareMap _hardwareMap) {
        consts = new Consts(_hardwareMap);

        p = 0.0089;
        i = 0;
        d = 0.0002;
        f = 0.024;
        ticksPerDegree = 537.7 / 360;

        controller = new PIDController(p, i, d);
    }


    @Override
    public double getCurrentPos() {
        return (consts.slideL.getCurrentPosition());
    }


    @Override
    protected void setMotors(double power) {
        consts.slideL.setPower(power);
        consts.slideR.setPower(power);
    }
}
