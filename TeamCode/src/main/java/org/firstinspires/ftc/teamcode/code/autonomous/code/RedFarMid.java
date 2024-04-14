package org.firstinspires.ftc.teamcode.code.autonomous.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.code.baseClasses.MainAutonomous;

@Autonomous
public class RedFarMid extends MainAutonomous {
    @Override
    public void setVariables() {
        color = "red";
        startDis = "far";
        endDis = "mid";
    }
}
