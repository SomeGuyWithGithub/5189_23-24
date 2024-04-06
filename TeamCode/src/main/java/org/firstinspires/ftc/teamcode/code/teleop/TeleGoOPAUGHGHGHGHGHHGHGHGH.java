package org.firstinspires.ftc.teamcode.code.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.code.constants.Consts;
import org.firstinspires.ftc.teamcode.code.constants.PIDFConsts;
import org.firstinspires.ftc.teamcode.code.constants.TeleConsts;

@TeleOp
public class TeleGoOPAUGHGHGHGHGHHGHGHGH extends OpMode {
    Consts consts;
    PIDFConsts.SlidePIDF slidePIDF;
    TeleConsts teleConsts;
    TeleConsts.Arm arm;
    TeleConsts.Slide slide;
    TeleConsts.Claw claw;
    TeleConsts.Popper popper;

    MultipleTelemetry telem;

    private double motorPower = 1.;
    double p = 0.0089, i = 0, d = 0.0002, f = 0.024, ticksPerDegree = 537.7 / 360;

    PIDController controller = new PIDController(p, i, d);

    private int slideTarget = 0;

    @Override
    public void init() {
        telem = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        consts = new Consts(hardwareMap);
        slidePIDF = new PIDFConsts.SlidePIDF(hardwareMap);
        teleConsts = new TeleConsts(hardwareMap);
        arm = new TeleConsts.Arm();
        slide = new TeleConsts.Slide();
        claw = new TeleConsts.Claw();
        popper = new TeleConsts.Popper();

        consts.setInit();
        telem.addLine("Initialized.");
        telem.update();
    }

    @Override
    public void loop() {
        double pid = controller.calculate(getCurrentPos(), slideTarget);
        double ff = Math.cos(Math.toRadians(slideTarget / ticksPerDegree)) * f;

        double power =  pid + ff;

        consts.slideR.setPower(power);
        consts.slideL.setPower(power);

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
        teleConsts.fieldCentricDrive(gamepad1, motorPower, telem);


        // Player 2
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

        telem.update();
    }

    public double getCurrentPos() {
        return (consts.slideL.getCurrentPosition() + consts.slideR.getCurrentPosition()) / 2.;
    }
}
