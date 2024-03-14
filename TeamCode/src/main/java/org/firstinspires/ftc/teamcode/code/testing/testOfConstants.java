package org.firstinspires.ftc.teamcode.code.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class testOfConstants extends OpMode {
    Consts constants;
    @Override
    public void init() {
         constants = new Consts(hardwareMap);



    }

    @Override
    public void loop() {

    }
}
