package org.firstinspires.ftc.teamcode.code.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.code.constants.Consts;
import org.firstinspires.ftc.teamcode.code.constants.PIDFConsts;

@Config
@TeleOp
public class pidf_test extends OpMode {
    Consts consts;
    PIDFConsts.slidePIDF slidePIDF;

    public static int target = 0;

    @Override
    public void init() {
        consts = new Consts(hardwareMap);
        slidePIDF = new PIDFConsts.slidePIDF(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        slidePIDF.setMotors(target);

        telemetry.addData("position ", slidePIDF.getCurrentPos());
        telemetry.addData("target ", target);
    }
}
