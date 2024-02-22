package org.firstinspires.ftc.teamcode.code.autonomous.code;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d(0, 0, 0));

        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(30)
                .strafeRight(10)
                .build();

        waitForStart();
        if (isStopRequested()) return;

        drive.followTrajectory(myTrajectory);

        while (opModeIsActive()) {
            if (!drive.isBusy()) {
                // place yellow
            }
        }
    }
}
