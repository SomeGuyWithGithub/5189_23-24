package org.firstinspires.ftc.teamcode.code.autonomous.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.code.baseClasses.MainAutonomous;

@Autonomous
public class BlueFarMid extends MainAutonomous {
    @Override
    public void setVariables() {
        color = "blue";
        startDis = "far";
        endDis = "mid";
    }
}
