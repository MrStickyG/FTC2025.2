package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.PIDController;

public class Extension {
    private DcMotor Extension;
    PIDController extensionPID = new PIDController(0.007);
    private int extensionSetpoint = 0;
    public Extension(HardwareMap hardwareMap){
        Extension = hardwareMap.get(DcMotor.class, "exten");


    }
    public void extension(boolean extensionOut, boolean extensionIn){
        if (extensionOut){
            extensionSetpoint += 9;

        } else if (extensionIn){
            extensionSetpoint -= 7.5;

        }
        //extensionSetpoint = MathUtils.clamp(extensionSetpoint,);
        double outputE = extensionPID.calculate(Extension.getCurrentPosition(),extensionSetpoint);
        Extension.setPower(outputE + 0.01);
    }
}
