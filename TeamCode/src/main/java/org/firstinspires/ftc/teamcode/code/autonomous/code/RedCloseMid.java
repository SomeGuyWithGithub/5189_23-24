package org.firstinspires.ftc.teamcode.code.autonomous.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.code.baseClasses.MainAutonomous;

@Autonomous
public class RedCloseMid extends MainAutonomous {
    @Override
    public void setVariables() {
        color = "red";
        startDis = "close";
        endDis = "mid";
    }
}
