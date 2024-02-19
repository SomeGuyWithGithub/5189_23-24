package org.firstinspires.ftc.teamcode.code.autonomous.pathing;

import android.util.Size;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.code.autonomous.camera.MainCameraPipeline;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.VisionPortal;

import java.util.Objects;

public class MainAutoPath {
    private Telemetry telemetry;
    private double centerLine, leftLine, rightLine, color;
    private String startDis, endDis, position;

    private Pose2d startPos, endPos;

    private MainCameraPipeline cameraProcessor;

    private SampleMecanumDrive drive;

    private TrajectorySequence purplePath, purpleToBackdropPath, yellowPlacePath, parkPath;

    public void initVarsAndCamera(HardwareMap hardwareMap, SampleMecanumDrive drive, Telemetry telemetry, String color, String startDis, String endDis) {
        // generate lines that the purple placement will depend upon
        // changes whether the bot starts close or far side
        centerLine = 12.;
        leftLine = 8.;
        rightLine = 15.5;
        if (Objects.equals(startDis, "far")) {
            centerLine = -35.;
            leftLine = -39.;
            rightLine = -31.;
        }

        // sets other variables used in path making
        this.startDis = startDis;
        this.drive = drive;
        this.endDis = endDis;
        this.telemetry = telemetry;
        if (Objects.equals(color, "red")) {
            this.color = 1.;
        } else if (Objects.equals(color, "blue")) {
            this.color = -1.;
        }

        // start position of the bot on roadrunner's coordinate place
        startPos = new Pose2d(centerLine, -61 * this.color, Math.toRadians(-90 * this.color));
        drive.setPoseEstimate(startPos);

        // sets up the the processor
        cameraProcessor = new MainCameraPipeline();
        // sets the processor to detect red or blue, depending on what the color is
        cameraProcessor.setColor(color);

        // sets the camera with the previously build processor
        VisionPortal portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Goof"))
                .setCameraResolution(new Size(640, 480))
                .setCamera(BuiltinCameraDirection.BACK)
                .addProcessor(cameraProcessor)
                .enableLiveView(true)
                .build();
    }

    private String getTeamElementPos() {
        String position = cameraProcessor.getPropPosition();

        telemetry.addData("Position: ", position);
        telemetry.update();

        return position;
    }

    public TrajectorySequence makePurplePath() {
        this.position = getTeamElementPos();

        if (color == 1.) {
            if (Objects.equals(startDis, "close")) {
                if (Objects.equals(position, "left")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(rightLine + 10, -50))
                            .lineToLinearHeading(new Pose2d(leftLine, -30, Math.toRadians(0)))
                            .lineTo(new Vector2d(rightLine, -30))
                            .lineTo(new Vector2d(centerLine, -42))
                            .turn(Math.toRadians(180))
                            .build();
                } else if (Objects.equals(position, "mid")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(centerLine, -31))
                            .lineTo(new Vector2d(centerLine, -42))
                            .turn(Math.toRadians(-90))
                            .build();
                } else {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(rightLine + 7, -33))
                            .lineTo(new Vector2d(rightLine + 7, -45))
                            .lineTo(new Vector2d(centerLine, -42))
                            .turn(Math.toRadians(-90))
                            .build();
                }
            } else if (Objects.equals(startDis, "far")) {
                if (Objects.equals(position, "left")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(leftLine - 7.5, -33))
                            .lineTo(new Vector2d(leftLine - 7.5, -45))
                            .lineTo(new Vector2d(centerLine, -42))
                            .build();
                } else if (Objects.equals(position, "mid")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(centerLine, -31))
                            .lineTo(new Vector2d(centerLine, -42))
                            .build();
                } else {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(leftLine - 10, -50))
                            .lineToLinearHeading(new Pose2d(rightLine + 1, -30, Math.toRadians(180)))
                            .lineTo(new Vector2d(leftLine, -30))
                            .lineTo(new Vector2d(centerLine, -42))
                            .turn(Math.toRadians(90))
                            .build();
                }
            }

        } else {
            if (Objects.equals(startDis, "close")) {
                if (Objects.equals(position, "left")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(rightLine + 7, 33))
                            .lineTo(new Vector2d(rightLine + 7, 45))
                            .lineTo(new Vector2d(centerLine, 42))
                            .turn(Math.toRadians(90))
                            .build();
                } else if (Objects.equals(position, "mid")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(centerLine, 31))
                            .lineTo(new Vector2d(centerLine, 42))
                            .turn(Math.toRadians(90))
                            .build();
                } else {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(rightLine + 10, 50))
                            .lineToLinearHeading(new Pose2d(leftLine, 30, Math.toRadians(0)))
                            .lineTo(new Vector2d(rightLine, 30))
                            .lineTo(new Vector2d(centerLine, 42))
                            .turn(Math.toRadians(180))
                            .build();
                }
            } else if (Objects.equals(startDis, "far")) {
                if (Objects.equals(position, "left")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(leftLine - 10, 50))
                            .lineToLinearHeading(new Pose2d(rightLine, 30, Math.toRadians(180)))
                            .lineTo(new Vector2d(leftLine, 30))
                            .lineTo(new Vector2d(centerLine, 42))
                            .build();
                } else if (Objects.equals(position, "mid")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(centerLine, 31))
                            .lineTo(new Vector2d(centerLine, 42))
                            .turn(Math.toRadians(90))
                            .build();
                } else {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(leftLine - 7.5, 33))
                            .lineTo(new Vector2d(leftLine - 7.5, 45))
                            .lineTo(new Vector2d(centerLine, 42))
                            .turn(Math.toRadians(90))
                            .build();
                }
            }
        }
        endPos = new Pose2d(centerLine, -42 * color, Math.toRadians(180));

        return purplePath;
    }

    public TrajectorySequence makePurpleToBackdropPath() {
        if (Objects.equals(startDis, "close")) {
            purpleToBackdropPath = drive.trajectorySequenceBuilder(endPos)
                    .lineTo(new Vector2d(50, -41 * color))
                    .build();
        } else {
            if (Objects.equals(position, "mid")) {
                purpleToBackdropPath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(-53, -42 * color))
                        .lineTo(new Vector2d(-53, -12 * color))
                        .lineTo(new Vector2d(38, -12 * color))
                        .lineTo(new Vector2d(50, -41 * color))
                        .build();
            } else {
                purpleToBackdropPath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(-35, -12 * color))
                        .lineTo(new Vector2d(38, -12 * color))
                        .lineTo(new Vector2d(50, -41 * color))
                        .build();
            }
        }
        endPos = new Pose2d(50, -41 * color, Math.toRadians(180));

        return purpleToBackdropPath;
    }

    public TrajectorySequence makeYellowPlacePath() {
        if (Objects.equals(position, "left")) {
            yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                    .lineTo(new Vector2d(50, -36 * color))
                    .build();
            endPos = new Pose2d(50, -36 * color, Math.toRadians(180));
        } else if (Objects.equals(position, "mid")) {
            yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                    .lineTo(new Vector2d(50, -42 * color))
                    .build();
            endPos = new Pose2d(50, -42 * color, Math.toRadians(180));
        } else {
            yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                    .lineTo(new Vector2d(50, -51 * color))
                    .build();
            endPos = new Pose2d(50, -51 * color, Math.toRadians(180));
        }

        return yellowPlacePath;
    }

    public TrajectorySequence makeParkPath() {
        if (Objects.equals(endDis, "close")) {
            parkPath = drive.trajectorySequenceBuilder(endPos)
                    .lineTo(new Vector2d(50, -10 * color))
                    .build();
        } else {
            parkPath = drive.trajectorySequenceBuilder(endPos)
                    .lineTo(new Vector2d(50, -60 * color))
                    .build();
        }

        return parkPath;
    }

    public SampleMecanumDrive getDrive() {return drive;}
}