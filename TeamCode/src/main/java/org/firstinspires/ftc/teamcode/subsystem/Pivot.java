package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import androidx.core.math.MathUtils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.PIDController;

public class Pivot {
    private DcMotorEx Pivot;
    PIDController pivotPID = new PIDController(0.007);
    private int pivotSetpoint = 0;
    int home = 132;
    //boolean goToHome=gamepad1.x;
    public Pivot(HardwareMap hardwareMap){
        Pivot = hardwareMap.get(DcMotorEx.class, "Pivot");
        Pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Pivot.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        Pivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Pivot.setDirection(DcMotorSimple.Direction.REVERSE);


    }
    public void pivot(float pivotUp, float pivotDown, boolean goToHome){
        if (goToHome){
            pivotSetpoint = home;

        }
        if(pivotUp > 0.01){
            pivotSetpoint += pivotUp*20;

        } else if (pivotDown > 0.01) {
            pivotSetpoint -= pivotDown*20;
        }

        pivotSetpoint = MathUtils.clamp(pivotSetpoint, 144, 2200);
        double output = pivotPID.calculate(Pivot.getCurrentPosition(),pivotSetpoint);
        output = MathUtils.clamp(output, -0.25, 1.0);
        Pivot.setPower(output + 0.01);
    }
}
