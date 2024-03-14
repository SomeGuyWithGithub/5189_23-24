package org.firstinspires.ftc.teamcode.code.testing;

import org.firstinspires.ftc.teamcode.code.baseClasses.MainAutonomous;

public class childTest extends MainAutonomous {
    @Override
    public void setVariables() {
        color = "red"; // blue or red
        startDis = "close"; // close or far
        endDis = "mid"; // edge or mid
    }
}
