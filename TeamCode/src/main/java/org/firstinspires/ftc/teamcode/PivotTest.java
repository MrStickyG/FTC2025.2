package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.util.PIDController;

@TeleOp(name = "Pivot Test")
public class PivotTest extends OpMode {
    DcMotorEx pivot;

    private int pivotSetpoint = 0;

    private double kp = 0.007;
    private PIDController pidController;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        pivot = hardwareMap.get(DcMotorEx.class, "Pivot");

        pidController = new PIDController(kp);

        pivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.update();
    }

    @Override
    public void init_loop() {
        telemetry.addData("Encoder Position", pivot.getCurrentPosition());
        telemetry.update();
    }

    @Override
    public void loop() {
        boolean pivotUp = gamepad1.dpad_up;
        boolean pivotDown = gamepad1.dpad_down;

        if (pivotUp && pivotDown) {
            pivotUp = false;
        }

        if (pivotUp) {
            pivotSetpoint += 5;
        } else if (pivotDown) {
            pivotSetpoint -= 5;
        }

        double output = pidController.calculate(pivot.getCurrentPosition(), pivotSetpoint);
        pivot.setPower(output);

        telemetry.addData("Pivot Setpoint", pivotSetpoint);
        telemetry.addData("Encoder Position", pivot.getCurrentPosition());
        telemetry.update();
    }
}