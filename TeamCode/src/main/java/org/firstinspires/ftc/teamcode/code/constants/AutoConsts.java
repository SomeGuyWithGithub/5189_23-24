package org.firstinspires.ftc.teamcode.code.constants;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.code.autonomous.camera.MainCameraPipeline;
import org.firstinspires.ftc.vision.VisionPortal;

public class AutoConsts {
    private static HardwareMap hardwareMap;
    private static VisionPortal portal;

    public MainCameraPipeline processor;

    public AutoConsts(HardwareMap _hardwareMap) {
        hardwareMap = _hardwareMap;
    }

    public void setProcessor() {
        processor = new MainCameraPipeline();
    }

    public void setCamera() {
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Goof"))
                .setCameraResolution(new Size(640, 480))
                .setCamera(BuiltinCameraDirection.BACK)
                .addProcessor(processor)
                .enableLiveView(true)
                .build();
    }

    public void stopCamera() {
        portal.close();
    }

    public enum State{
        PLACE_PURPLE,
        PLACE_YELLOW,
        SCORE,
        PARK,
        Idle
    }
    public enum YellowState{
        DRIVE,
        POSITION,
        PLACE
    }
    public enum Score{
        // TODO: do this later
    }

    public static class autoEnums {

        public State state = State.PLACE_PURPLE;
        public YellowState yellowState = YellowState.DRIVE;
    }
}
