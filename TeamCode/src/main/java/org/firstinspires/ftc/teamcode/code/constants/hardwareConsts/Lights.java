package org.firstinspires.ftc.teamcode.code.constants.hardwareConsts;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.code.constants.Consts;

public class Lights {
    public enum LightStates {
        ENDGAME,
        SCORE,
        GRAB,
        OPEN,
        CLAWDOWN,
        CLAWUP,
        INITIALIZE
    }

    Consts consts;

    public LightStates lightStates;

    public Lights(HardwareMap _hardwareMap) {
        consts = new Consts(_hardwareMap);

        lightStates = LightStates.INITIALIZE;
    }

    public void setLights() {
        switch (lightStates) {
            case INITIALIZE:
                consts.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.HOT_PINK);
            case GRAB:
                consts.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.VIOLET);
            case OPEN:
                consts.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
            case SCORE:
                consts.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_FOREST_PALETTE);
            case CLAWUP:
                consts.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BREATH_BLUE);
            case CLAWDOWN:
                consts.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED);
            case ENDGAME:
                consts.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_RAINBOW_PALETTE);
                consts.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_2_BEATS_PER_MINUTE);
        }
    }
}
