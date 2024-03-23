package org.firstinspires.ftc.teamcode.code.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.code.constants.Consts;
import org.firstinspires.ftc.teamcode.code.constants.PIDFConsts;
import org.firstinspires.ftc.teamcode.code.constants.TeleConsts;

@TeleOp
public class TeleGoOPAUGHGHGHGHGHHGHGHGH extends OpMode {

    Consts consts;
    PIDFConsts.slidePIDF slidePIDF;
    TeleConsts teleConsts;
    TeleConsts.Arm arm;
    TeleConsts.Claw claw;
    TeleConsts.Slide slide;

    private int slideTarget;
    private int slideTimer;

    double motorPower;

    @Override
    public void init() {
        consts = new Consts(hardwareMap);
        slidePIDF = new PIDFConsts.slidePIDF(hardwareMap);
        teleConsts = new TeleConsts(hardwareMap);
        arm = new TeleConsts.Arm();
        claw = new TeleConsts.Claw();
        slide = new TeleConsts.Slide();

        consts.setInit();

        slideTarget = 0;
        slideTimer = 0;
    }

    @Override
    public void loop() {
        slideTimer ++;
        if (slideTimer == 5) {
            slideTimer = 0;
            slidePIDF.setMotors(slideTarget);
        }
        // EXAMPLE "hang"
        if (gamepad1.y) {
            slideTarget = 1000; // pretend like this is correct position
        }

        //SECOND PLAYER
        if(gamepad2.dpad_down){
            motorPower = arm.setGrab();
        }
        if(gamepad2.dpad_up){
            motorPower = arm.setRest();
        }
        if(gamepad2.y){
            motorPower = arm.setScore();
        }
        if(gamepad2.right_bumper){
            claw.setGrab();
        }
        if(gamepad2.left_bumper){
            claw.setRelease();
        }


        //FIRST PLAYER
        if (gamepad1.options) {
            consts.imu.resetYaw();
        }

        teleConsts.fieldCentricDrive(gamepad1, motorPower);

        if (gamepad1.y) {
            slide.goUp();
        }
        if (gamepad1.a) {
            slide.goDown();
        }
        if (gamepad1.b) {
            slide.stay();
            if (gamepad1.x){
                slide.hang();
            }
        }
    }
}
