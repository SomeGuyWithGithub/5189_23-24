package org.firstinspires.ftc.teamcode.code.autonomous.code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.code.autonomous.camera.MainCameraPipeline;
import org.firstinspires.ftc.teamcode.code.autonomous.pathing.MainAutoPath;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous
public class MainAutonomous extends OpMode
{
    public enum State{
        PLACE_PURPLE,
        PLACE_YELLOW,
        SCORE,
        PARK,
        Idle
    }
    State state;

    public enum YellowState{
        DRIVE,
        POSITION,
        PLACE
    }
    YellowState yellowState;

    public enum Score{
        // TODO: do this later
    }

    String startDis, endDis, color;

    SampleMecanumDrive drive;

    TrajectorySequence purplePath, purpleToBackdropPrath, yellowPlacePath, parkPath;

    Servo yellowArm;

    private ElapsedTime runtime;

    private MainCameraPipeline cameraPipeline;
    private VisionPortal portal;

    double centerLine, leftLine, rightLine;

    Servo arm, joint, claw;

    MainAutoPath pathingTool;

    public void init() {
        arm = hardwareMap.get(Servo.class, "idk1");
        joint = hardwareMap.get(Servo.class, "idk2");
        claw = hardwareMap.get(Servo.class, "idk3");
        yellowArm = hardwareMap.get(Servo.class, "pickol");

        arm.setDirection(Servo.Direction.FORWARD);
        joint.setDirection(Servo.Direction.REVERSE);
        claw.setDirection(Servo.Direction.FORWARD);
        yellowArm.setDirection(Servo.Direction.FORWARD);

        yellowArm.setPosition(0);

        runtime = new ElapsedTime();

        state = State.PLACE_PURPLE;
        yellowState = YellowState.DRIVE;

        drive = new SampleMecanumDrive(hardwareMap);

        pathingTool = new MainAutoPath();

        color = "red";
        startDis = "close";
        endDis = "edge";

        pathingTool.initVarsAndCamera(hardwareMap, drive, telemetry, color, startDis, endDis);
    }

    public void start() {
        runtime.reset();
        while (runtime.time() < 1.5) {}
        purplePath = pathingTool.makePurplePath();
        purpleToBackdropPrath = pathingTool.makePurpleToBackdropPath();
        yellowPlacePath = pathingTool.makeYellowPlacePath();
        parkPath = pathingTool.makeParkPath();

        drive = pathingTool.getDrive();
        drive.followTrajectorySequenceAsync(purplePath);
    }

    @Override
    public void loop()
    {
        switch(state)
        {
            case PLACE_PURPLE:
                if (!drive.isBusy()) {
                    state = State.PLACE_YELLOW;
                    drive.followTrajectorySequenceAsync(purpleToBackdropPrath);
                }
                break;
            case PLACE_YELLOW:
                switch(yellowState)
                {
                    case DRIVE:
                        if (!drive.isBusy()) {
                            yellowState = YellowState.POSITION;
                            drive.followTrajectorySequenceAsync(yellowPlacePath);
                        }
                        break;
                    case POSITION:
                        if (!drive.isBusy()) {
                            runtime.reset();
                            yellowState = YellowState.PLACE;
                        }
                        break;
                    case PLACE:
                        yellowArm.setPosition(0.6);
                        if (runtime.time() > 1) {
                            yellowArm.setPosition(0);
                            state = State.SCORE;
                        }
                        break;
                }
                break;
            case SCORE:
                state = State.PARK;
                drive.followTrajectorySequenceAsync(parkPath);
                break;
            case PARK:
                if (!drive.isBusy()) {
                    state = State.Idle;
                }
                break;
            case Idle:
                break;
        }

        drive.update();
    }
}
