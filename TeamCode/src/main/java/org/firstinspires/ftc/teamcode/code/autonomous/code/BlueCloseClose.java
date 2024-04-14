package org.firstinspires.ftc.teamcode.code.autonomous.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.code.baseClasses.MainAutonomous;

@Autonomous
public class BlueCloseClose extends MainAutonomous {
    @Override
    public void setVariables() {
        color = "blue";
        startDis = "close";
        endDis = "close";
    }
}
