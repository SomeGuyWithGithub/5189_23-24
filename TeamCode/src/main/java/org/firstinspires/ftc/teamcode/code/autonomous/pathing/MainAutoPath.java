package org.firstinspires.ftc.teamcode.code.autonomous.pathing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.code.constants.AutoConsts;
import org.firstinspires.ftc.teamcode.code.constants.Consts;
import org.firstinspires.ftc.teamcode.code.autonomous.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.code.autonomous.roadrunner.trajectorysequence.TrajectorySequence;

import java.util.Objects;

public class MainAutoPath {
    private MultipleTelemetry telemetry;

    private AutoConsts autoConsts;

    private double centerLine, leftLine, rightLine, color;
    private String startDis, endDis, position;

    private Pose2d startPos, endPos;

    private SampleMecanumDrive drive;

    private TrajectorySequence purplePath, purpleToBackdropPath, yellowPlacePath, gotoWhitePath,  parkPath;

    public void initVarsAndCamera(HardwareMap hardwareMap, SampleMecanumDrive drive, Telemetry telemetry, String color, String startDis, String endDis) {
        autoConsts = new AutoConsts(hardwareMap);

        // generate lines that the purple placement will depend upon
        // changes whether the bot starts close or far side
        centerLine = 14;
        leftLine = 8.;
        rightLine = 15.5;
        if (Objects.equals(startDis, "far")) {
            centerLine = -37.5;
            leftLine = -39.;
            rightLine = -31.;
        }

        // sets other variables used in path making
        this.drive = drive;
        this.startDis = startDis;
        this.endDis = endDis;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        if (Objects.equals(color, "red")) {
            this.color = 1.;
        } else if (Objects.equals(color, "blue")) {
            this.color = -1.;
        }

        // start position of the bot on roadrunner's coordinate place
        startPos = new Pose2d(centerLine, -61 * this.color, Math.toRadians(-90 * this.color));
        drive.setPoseEstimate(startPos);

        // sets the processor to detect red or blue, depending on what the color is
        autoConsts.setProcessor();
        autoConsts.processor.setColor(color);

        // sets the camera with the previously build processor
        autoConsts.setCamera();
    }

    private String getTeamElementPos() {
        String position = autoConsts.processor.getPropPosition();
        autoConsts.stopCamera();

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
                            .lineToLinearHeading(new Pose2d(leftLine + 1, -30, Math.toRadians(0)))
                            .lineTo(new Vector2d(rightLine + 1, -30))
                            .lineTo(new Vector2d(centerLine, -42))
                            .turn(Math.toRadians(180))
                            .build();
                } else if (Objects.equals(position, "mid")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(centerLine, -40))
                            .lineTo(new Vector2d(centerLine - 1, -32))
                            .lineTo(new Vector2d(centerLine, -42))
                            .turn(Math.toRadians(-90))
                            .build();
                } else {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(rightLine + 7, -38))
                            .lineTo(new Vector2d(rightLine + 7, -45))
                            .lineTo(new Vector2d(centerLine, -42))
                            .turn(Math.toRadians(-90))
                            .build();
                }
            } else if (Objects.equals(startDis, "far")) {
                if (Objects.equals(position, "left")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(leftLine - 7.5, -38))
                            .lineTo(new Vector2d(leftLine - 7.5, -45))
                            .lineTo(new Vector2d(centerLine, -42))
                            .turn(Math.toRadians(-90))
                            .build();
                } else if (Objects.equals(position, "mid")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(centerLine, -40))
                            .lineTo(new Vector2d(centerLine + 1, -32))
                            .lineTo(new Vector2d(centerLine, -42))
                            .build();
                } else {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(leftLine - 10, -50))
                            .lineToLinearHeading(new Pose2d(rightLine - 1, -30, Math.toRadians(180)))
                            .lineTo(new Vector2d(leftLine, -30))
                            .lineTo(new Vector2d(centerLine, -42))
                            .build();
                }
            }

        } else {
            if (Objects.equals(startDis, "close")) {
                if (Objects.equals(position, "left")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(rightLine + 7, 38))
                            .lineTo(new Vector2d(rightLine + 7, 45))
                            .lineTo(new Vector2d(centerLine, 42))
                            .turn(Math.toRadians(-90))
                            .build();
                } else if (Objects.equals(position, "mid")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(centerLine, 40))
                            .lineTo(new Vector2d(centerLine - 1, 32))
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
                            .lineToLinearHeading(new Pose2d(rightLine - 1, 30, Math.toRadians(180)))
                            .lineTo(new Vector2d(leftLine, 30))
                            .lineTo(new Vector2d(centerLine, 42))
                            .build();
                } else if (Objects.equals(position, "mid")) {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(centerLine, 40))
                            .lineTo(new Vector2d(centerLine + 1, 32))
                            .lineTo(new Vector2d(centerLine, 42))
                            .turn(Math.toRadians(90))
                            .build();
                } else {
                    purplePath = drive.trajectorySequenceBuilder(startPos)
                            .lineTo(new Vector2d(leftLine - 7.5, 38))
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
                        .lineTo(new Vector2d(-53, -2 * color))
                        .lineTo(new Vector2d(38, -2 * color))
                        .lineTo(new Vector2d(50, -41 * color))
                        .build();
            } else {
                purpleToBackdropPath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(-35, -2 * color))
                        .lineTo(new Vector2d(38, -2 * color))
                        .lineTo(new Vector2d(50, -41 * color))
                        .build();
            }
        }
        endPos = new Pose2d(50, -41 * color, Math.toRadians(180));

        return purpleToBackdropPath;
    }

    public TrajectorySequence makeYellowPlacePath() {
        if (color == 1.) {
            if (Objects.equals(position, "left")) {
                yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(53, -35 * color))
                        .build();
                endPos = new Pose2d(53, -35 * color, Math.toRadians(180));
            } else if (Objects.equals(position, "mid")) {
                yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(53, -39 * color))
                        .build();
                endPos = new Pose2d(53, -39 * color, Math.toRadians(180));
            } else {
                yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(53, -49 * color))
                        .build();
                endPos = new Pose2d(53, -49 * color, Math.toRadians(180));
            }
        } else {
            if (Objects.equals(position, "left")) {
                yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(53, -34 * color))
                        .build();
                endPos = new Pose2d(53, -34 * color, Math.toRadians(180));
            } else if (Objects.equals(position, "mid")) {
                yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(53, -28 * color))
                        .build();
                endPos = new Pose2d(53, -27 * color, Math.toRadians(180));
            } else {
                yellowPlacePath = drive.trajectorySequenceBuilder(endPos)
                        .lineTo(new Vector2d(53, -21 * color))
                        .build();
                endPos = new Pose2d(53, -21 * color, Math.toRadians(180));
            }
        }
        return yellowPlacePath;
    }

    public TrajectorySequence makeParkPath() {
        if (Objects.equals(endDis, "edge")) {
            parkPath = drive.trajectorySequenceBuilder(endPos)
                    .lineTo(new Vector2d(50, -60 * color))
                    .build();
        } else {
            parkPath = drive.trajectorySequenceBuilder(endPos)
                    .lineTo(new Vector2d(50, -10 * color))
                    .build();
        }

        return parkPath;
    }

    public SampleMecanumDrive getDrive() {return drive;}
}