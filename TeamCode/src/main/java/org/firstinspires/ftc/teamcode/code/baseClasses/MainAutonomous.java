package org.firstinspires.ftc.teamcode.code.baseClasses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.code.autonomous.camera.MainCameraPipeline;
import org.firstinspires.ftc.teamcode.code.autonomous.pathing.MainAutoPath;
import org.firstinspires.ftc.teamcode.code.autonomous.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.code.autonomous.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.code.constants.AutoConsts;
import org.firstinspires.ftc.teamcode.code.constants.Consts;
import org.firstinspires.ftc.vision.VisionPortal;

@Disabled
@Autonomous
public abstract class MainAutonomous extends OpMode
{
    Consts consts;
    AutoConsts.autoEnums autoEnums;

    public String startDis, endDis, color;

    SampleMecanumDrive drive;

    TrajectorySequence purplePath, purpleToBackdropPath, yellowPlacePath, gotoWhitePath, parkPath;

    private ElapsedTime runtime;



    private MainCameraPipeline cameraPipeline;
    private VisionPortal portal;

    double centerLine, leftLine, rightLine;

    MainAutoPath pathingTool;

    public abstract void setVariables();

    public void init() {
        setVariables();

        consts = new Consts(hardwareMap);
        autoEnums = new AutoConsts.autoEnums();

        consts.setInit();

        runtime = new ElapsedTime();

        drive = new SampleMecanumDrive(hardwareMap);

        pathingTool = new MainAutoPath();

        pathingTool.initVarsAndCamera(hardwareMap, drive, telemetry, color, startDis, endDis);
    }


    public void start() {
        runtime.reset();
        while (runtime.time() < 1.5) {}
        purplePath = pathingTool.makePurplePath();
        purpleToBackdropPath = pathingTool.makePurpleToBackdropPath();
        yellowPlacePath = pathingTool.makeYellowPlacePath();
        parkPath = pathingTool.makeParkPath();

        drive = pathingTool.getDrive();
        drive.followTrajectorySequenceAsync(purplePath);
    }

    @Override
    public void loop()
    {
        switch(autoEnums.state)
        {
            case PLACE_PURPLE:
                if (!drive.isBusy()) {
                    autoEnums.state = AutoConsts.State.PLACE_YELLOW;
                    drive.followTrajectorySequenceAsync(purpleToBackdropPath);
                }
                break;
            case PLACE_YELLOW:
                switch(autoEnums.yellowState)
                {
                    case DRIVE:
                        if (!drive.isBusy()) {
                            autoEnums.yellowState = AutoConsts.YellowState.POSITION;
                            drive.followTrajectorySequenceAsync(yellowPlacePath);
                        }
                        break;
                    case POSITION:
                        if (!drive.isBusy()) {
                            runtime.reset();
                            autoEnums.yellowState = AutoConsts.YellowState.PLACE;
                        }
                        break;
                    case PLACE:
                        consts.popper.setPosition(0.6);
                        if (runtime.time() > 1) {
                            consts.popper.setPosition(0);
                            autoEnums.state = AutoConsts.State.SCORE;
                        }
                        break;
                }
                break;
            case SCORE:
                autoEnums.state = AutoConsts.State.PARK;
                drive.followTrajectorySequenceAsync(parkPath);
                break;
            case PARK:
                if (!drive.isBusy()) {
                    autoEnums.state = AutoConsts.State.Idle;
                }
                break;
            case Idle:
                break;
        }

        drive.update();
    }
}
