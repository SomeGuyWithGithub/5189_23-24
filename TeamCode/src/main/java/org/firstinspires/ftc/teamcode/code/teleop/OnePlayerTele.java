package org.firstinspires.ftc.teamcode.code.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.code.constants.Consts;
import org.firstinspires.ftc.teamcode.code.constants.Movement;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Arm;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Claw;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Lights;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Popper;
import org.firstinspires.ftc.teamcode.code.constants.hardwareConsts.Slide;

@TeleOp
public class OnePlayerTele extends OpMode {
    private Consts consts;
    private Movement movement;
    private Arm arm;
    private Slide slide;
    private Claw claw;
    private Popper popper;
    private Lights lights;

    private MultipleTelemetry telem;

    private ElapsedTime runtime;

    private double motorPower = 1.;

    @Override
    public void init() {
        consts = new Consts(hardwareMap);
        movement = new Movement(hardwareMap);
        arm = new Arm(hardwareMap);
        slide = new Slide(hardwareMap);
        claw = new Claw(hardwareMap);
        popper = new Popper(hardwareMap);
        lights = new Lights(hardwareMap);

        telem = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);

        runtime = new ElapsedTime();

        consts.setInit();
        telem.addLine("Initialized");
        telem.update();
    }

    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        // Only player
        if (gamepad1.left_stick_button) {
            movement.changeMode(runtime);
        }
        if (gamepad1.right_bumper) {
            claw.switchPos(runtime);
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
        if (gamepad1.y) {
            slide.goUp();
        }
        if (gamepad1.dpad_left) {
            popper.goUp();
        }
        if (gamepad1.dpad_right) {
            popper.goDown();
        }
        if (gamepad1.dpad_down) {
            motorPower = arm.setGrab();
        }
        if (gamepad1.dpad_up) {
            motorPower = arm.setRest();
        }
        if (gamepad1.left_bumper) {
            motorPower = arm.setScore();
        }
        if (gamepad1.left_trigger >= 0.75) {
            motorPower = arm.setLong();
        }

        // Final Updates
        movement.run(gamepad1, motorPower, telem);
    }
}
