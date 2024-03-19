package org.firstinspires.ftc.teamcode.code.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.code.baseClasses.MainAutonomous;

@Autonomous
public class childTest extends MainAutonomous {
    @Override
    public void setVariables() {
        color = "red"; // blue or red
        startDis = "close"; // close or far
        endDis = "mid"; // edge or mid
    }
}
