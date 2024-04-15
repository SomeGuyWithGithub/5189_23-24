package org.firstinspires.ftc.teamcode.code.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class triggerTest extends OpMode {
    Consts consts;
    @Override
    public void init() {}

    @Override
    public void loop() {
        telemetry.addData("Left Trigger", gamepad1.left_trigger);
    }
}
