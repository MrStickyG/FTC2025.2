package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Twist2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Localizer;

public class GoBildaLocalizer implements Localizer {
    GoBildaPinpointDriver odometryDriver;

    public GoBildaLocalizer(HardwareMap hardwareMap, Pose2d pose) {
        odometryDriver = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");

        odometryDriver.setOffsets(0.0, 0.0);
        odometryDriver.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometryDriver.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);

        setPose(pose);
    }

    @Override
    public void setPose(Pose2d pose) {
        odometryDriver.resetPosAndIMU();
        odometryDriver.setPosition(
                new Pose2D(DistanceUnit.MM,
                        pose.position.x,
                        pose.position.y,
                        AngleUnit.DEGREES,
                        pose.heading.real)
        );
        odometryDriver.update();
    }

    @Override
    public Pose2d getPose() {
        odometryDriver.update();
        Pose2D pose2D = odometryDriver.getPosition();
        return new Pose2d(pose2D.getX(DistanceUnit.MM),
                pose2D.getY(DistanceUnit.MM),
                pose2D.getHeading(AngleUnit.DEGREES));
    }

    @Override
    public PoseVelocity2d update() {
        odometryDriver.update();
        Pose2D vel = odometryDriver.getVelocity();
        return new PoseVelocity2d(
                new Vector2d(vel.getX(DistanceUnit.MM), vel.getY(DistanceUnit.MM)),
                vel.getHeading(AngleUnit.DEGREES)
        );
    }
}
