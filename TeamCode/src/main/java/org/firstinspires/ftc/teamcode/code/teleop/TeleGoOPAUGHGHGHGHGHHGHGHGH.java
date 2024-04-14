package org.firstinspires.ftc.teamcode.code.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
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
    Consts consts;
    SlidePID slidePID;
    TeleConsts teleConsts;
    Arm arm;
    Slide slide;
    Claw claw;
    Popper popper;
    Lights lights;

    MultipleTelemetry telem;

    ElapsedTime runtime;
    double driveSwitchTime;
    boolean robotCentricBool;

    private double motorPower = 1.;
    double p = 0.0089, i = 0, d = 0.0002, f = 0.024, ticksPerDegree = 537.7 / 360;

    PIDController controller = new PIDController(p, i, d);

    private int slideTarget = 0;

    @Override
    public void init() {
        telem = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        consts = new Consts(hardwareMap);
//        slidePID = new SlidePID(hardwareMap);
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
//        double pid = controller.calculate(getCurrentPos(), slideTarget);
//        double ff = Math.cos(Math.toRadians(slideTarget / ticksPerDegree)) * f;
//
//        double power =  pid + ff;
//
//        consts.slideR.setPower(power);
//        consts.slideL.setPower(power);

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
        if(gamepad2.right_bumper){
            claw.setGrab();
            lights.lightStates = Lights.LightStates.GRAB;
        }
        if(gamepad2.left_bumper){
            claw.setRelease();
            lights.lightStates = Lights.LightStates.OPEN;
        }

        lights.setLights();

        telem.update();
    }

    public double getCurrentPos() {
        return (consts.slideL.getCurrentPosition() + consts.slideR.getCurrentPosition()) / 2.;
    }
}
