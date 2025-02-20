package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Dispenser;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Extension;
import org.firstinspires.ftc.teamcode.subsystem.Pivot;

@TeleOp(name="TeleopOp_Roadrunner")

public class TeleopOpRoadrunner extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    Pose2d startPose = new Pose2d(0,0, 0);
    MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);
    Extension extension = new Extension(hardwareMap);
    Pivot pivot = new Pivot(hardwareMap);
    Dispenser dispenser = new Dispenser(hardwareMap);

    @Override
    public void runOpMode(){
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()){
            drive.drive(-gamepad1.left_stick_x, -gamepad1.left_stick_y, -gamepad1.right_stick_x);
            extension.extension(gamepad1.right_bumper, gamepad1.left_bumper);
            pivot.pivot(gamepad1.left_trigger, gamepad1.right_trigger, gamepad1.x);
            dispenser.dispenser(gamepad1.a, gamepad1.b);

            // Show the elapsed game time and wheel power.
            //telemetry.addData("Left Trigger", -intake);
            //telemetry.addData("Right Trigger", dispense);
            //telemetry.addData("Arm Langth", Extension.getCurrentPosition());
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Pivot Angle", Pivot.getCurrentPosition());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }

    }

}
