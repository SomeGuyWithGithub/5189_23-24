package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class meepmeep {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep = new MeepMeep(800);

        double centerLine, leftLine, rightLine;

        double color = 1.; // 1. for red, -1. for blue
        centerLine = -37.5;
        leftLine = -39.;
        rightLine = -31.;
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(17.92127, 14)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.78)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(centerLine, -63*color, Math.toRadians(-90*color))
                                )
                                .lineTo(new Vector2d(leftLine - 10, -50))
                                .lineToLinearHeading(new Pose2d(rightLine - 1, -30, Math.toRadians(180)))
                                .lineTo(new Vector2d(leftLine, -30))
                                .lineTo(new Vector2d(centerLine, -42))

                                .lineTo(new Vector2d(-35, -12 * color))
                                .lineTo(new Vector2d(38, -12 * color))
                                .lineTo(new Vector2d(50, -41 * color))

                                .lineTo(new Vector2d(53, -49 * color))

                                .lineTo(new Vector2d(50, -10 * color))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}