package org.firstinspires.ftc.teamcode.code.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.code.constants.Consts;
import org.firstinspires.ftc.teamcode.code.constants.PIDConsts.SlidePID;
import org.firstinspires.ftc.teamcode.code.constants.TeleConsts;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Arm;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Claw;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Lights;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Popper;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Slide;

@TeleOp
public class TeleGoOPAUGHGHGHGHGHHGHGHGH extends OpMode {
    private Consts consts;
    private SlidePID slidePID;
    private TeleConsts teleConsts;
    private Arm arm;
    private Slide slide;
    private Claw claw;
    private Popper popper;
    private Lights lights;

    private MultipleTelemetry telem;

    private ElapsedTime runtime;

    private double driveSwitchTime = 0.;
    private boolean robotCentricBool = false;

    private double motorPower = 1.;

    private int slideTarget = 0;

    @Override
    public void init() {
        telem = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        consts = new Consts(hardwareMap);
        slidePID = new SlidePID(hardwareMap);
        teleConsts = new TeleConsts(hardwareMap);
        arm = new Arm(hardwareMap);
        slide = new Slide(hardwareMap);
        claw = new Claw(hardwareMap);
        popper = new Popper(hardwareMap);
        lights = new Lights(hardwareMap);

        runtime = new ElapsedTime();
        driveSwitchTime = 0;

        consts.setInit();
        telem.addLine("Initialized.");
        telem.update();
    }

    @Override
    public void loop() {

        // Player 1
        if (gamepad1.y) {
            slide.goUp();
        }
        if (gamepad1.a) {
            slide.goDown();
        }
        if (gamepad1.b) {
            slide.stay();
        }
        if (gamepad1.x){
            slide.hang();
        }
        if (gamepad1.dpad_left) {
            popper.goUp();
        }
        if (gamepad1.dpad_right) {
            popper.goDown();
        }
        if (gamepad1.options) {
            consts.imu.resetYaw();
        }

        if (gamepad1.right_bumper & runtime.seconds() - driveSwitchTime >= 0.15) {
            robotCentricBool = !robotCentricBool;
            driveSwitchTime = runtime.seconds();
        }
        if (robotCentricBool) {teleConsts.robotCentricDrive(gamepad1, motorPower, telem);}
        else {teleConsts.fieldCentricDrive(gamepad1, motorPower, telem);}


        // Player 2
        if(gamepad2.dpad_down){
            motorPower = arm.setGrab();
            lights.lightStates = Lights.LightStates.CLAWDOWN;
        }
        if(gamepad2.dpad_up){
            motorPower = arm.setRest();
            lights.lightStates = Lights.LightStates.CLAWUP;
        }
        if(gamepad2.y){
            motorPower = arm.setScore();
            lights.lightStates = Lights.LightStates.SCORE;
        }
//        if (gamepad2.right_bumper) {
//            claw.switchPos(runtime);
//        }

        if (gamepad2.left_bumper) {
            slideTarget = 0;
        }
        if (gamepad2.right_bumper) {
            slideTarget = 1000;
        }
        // Final things to update after every loop
        slidePID.run(slideTarget);
        telem.addData("Slide Target", slideTarget);
        telem.addData("Slide Pos", slidePID.getCurrentPos());
        telem.addData("Slide Power", slidePID.power);

        lights.setLights();

        telem.update();
    }

    public double getCurrentPos() {
        return (consts.slideL.getCurrentPosition() + consts.slideR.getCurrentPosition()) / 2.;
    }
}
