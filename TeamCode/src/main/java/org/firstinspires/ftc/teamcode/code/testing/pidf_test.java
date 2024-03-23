package org.firstinspires.ftc.teamcode.code.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

@Config
@TeleOp
public class pidf_test extends OpMode {
    Consts consts;

    PIDController controller;

    public static double p = 0, i = 0, d = 0, f = 0;
    public final double ticksPerDegree = 537.7 / 360;

    public static int target = 0;

    public double getCurrentPos() {
        return (consts.slideL.getCurrentPosition() + consts.slideR.getCurrentPosition()) / 2.;
    }

    @Override
    public void init() {
        consts = new Consts(hardwareMap);
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        controller.setPID(p, i, d);

        double pid = controller.calculate(getCurrentPos(), target);
        double ff = Math.cos(Math.toRadians(target / ticksPerDegree)) * f;

        double power = pid + ff;

        consts.slideR.setPower(power);
        consts.slideL.setPower(power);

        telemetry.addData("target: ", target);
        telemetry.addData("Current Pos: ", getCurrentPos());
        telemetry.update();
    }
}
