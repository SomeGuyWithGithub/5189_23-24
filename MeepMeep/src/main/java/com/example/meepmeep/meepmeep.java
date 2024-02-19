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

        double center_line;
        double left_line;
        double right_line;

        double color = 1.; // 1. for red, -1. for blue
        center_line = 12;
        left_line = 8.;
        right_line = 15.5;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(17.92127, 14)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.78)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(center_line, -61*color, Math.toRadians(-90*color))
                                )
                                .lineTo(new Vector2d(right_line + 10, -50))
                                .lineToLinearHeading(new Pose2d(left_line, -30, Math.toRadians(0)))
                                .lineTo(new Vector2d(right_line, -30))
                                .lineTo(new Vector2d(center_line, -42))
                                .turn(Math.toRadians(180))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}