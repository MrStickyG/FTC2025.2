package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Dispenser {
    private CRServo dispenser;

    public Dispenser(HardwareMap hardwareMap){
        dispenser = hardwareMap.get(CRServo.class, "dispenser");

    }
    public void dispenser(boolean dispenserOut, boolean dispenserIn){
        if (dispenserIn)
        {
            dispenser.setPower(-1);

        }
        else if (dispenserOut){
            dispenser.setPower(1);

        }
        else{
            dispenser.setPower(0);
        }

    }
}
