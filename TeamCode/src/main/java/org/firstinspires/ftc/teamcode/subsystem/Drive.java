package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.openftc.apriltag.ApriltagDetectionJNI.getPoseEstimate;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class Drive {
    private DcMotor FRMotor = null;
    private DcMotor FLMotor = null;
    private DcMotor BRMotor;
    private DcMotor BLMotor;
    double DConstant = 1;

    public Drive(HardwareMap hardwareMap) {
        FRMotor = hardwareMap.get(DcMotor.class, "FrontRightMotor");
        FLMotor = hardwareMap.get(DcMotor.class, "FrontLeftMotor");
        BLMotor = hardwareMap.get(DcMotor.class, "BackLeftMotor");
        BRMotor = hardwareMap.get(DcMotor.class, "BackRightMotor");

        FRMotor.setDirection(DcMotor.Direction.REVERSE);
        BRMotor.setDirection(DcMotor.Direction.FORWARD);

    }

    public void drive(double turn, double drive, double rotate) {
        double BLpower = DConstant * Range.clip(-drive - turn + rotate, -1.0, 1.0);
        double FLpower = DConstant * Range.clip(drive - turn - rotate, -1.0, 1.0);
        double FRpower = DConstant * Range.clip(drive + turn + rotate, -1.0, 1.0);
        double BRpower = DConstant * Range.clip(-drive + turn - rotate, -1.0, 1.0);

        // Send calculated power to wheels
        FLMotor.setPower(FLpower);
        BLMotor.setPower(BLpower);
        BRMotor.setPower(BRpower);
        FRMotor.setPower(FRpower);

    }

    public void driveToPosition(double x, double y, double heading) {
        Trajectory trajectory = TrajectoryBuilder(getPoseEstimate())
                .lineToLinearHeading(new Pose2d(x, y, Math.toRadians(heading)))
                .build();

        MecanumDrive.FollowTrajectoryAction(trajectory);

    }
}