package org.firstinspires.ftc.teamcode.code.constants.PIDConsts;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.baseClasses.basePIDF;
import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class SlidePID extends basePIDF {
    SlidePID slidePID;

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
        return (consts.slideL.getCurrentPosition() + consts.slideR.getCurrentPosition()) / 2.;
    }


    @Override
    public void setMotors(int target) {
        controller.setPID(p, i, d);

        double power = slidePID.getPower(target);

        consts.slideR.setPower(power);
        consts.slideL.setPower(power);
    }
}
