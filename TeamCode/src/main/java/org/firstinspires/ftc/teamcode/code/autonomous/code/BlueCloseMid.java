package org.firstinspires.ftc.teamcode.code.autonomous.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.code.baseClasses.MainAutonomous;

@Autonomous
public class BlueCloseMid extends MainAutonomous {
    @Override
    public void setVariables() {
        color = "blue";
        startDis = "close";
        endDis = "mid";
    }
}
