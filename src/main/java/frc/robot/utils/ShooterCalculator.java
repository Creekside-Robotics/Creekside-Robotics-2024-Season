// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.FieldConstants;
import frc.robot.Constants.ElevatorConstants;

/** Add your docs here. */
public class ShooterCalculator {
    private Drivetrain drivetrain;
    private double shootSpeed = 12.0;
    private double gravity = 9.8;

    public ShooterCalculator(Drivetrain drivetrain){
        this.drivetrain = drivetrain;
    }

    public double[] targetPositionDifference(){
        double[] targetPosition = new double[]{
            FieldConstants.tranformPoseBluePose(FieldConstants.speakerPosition).getX(),
            FieldConstants.tranformPoseBluePose(FieldConstants.speakerPosition).getY()
        };

        return new double[]{
            targetPosition[0] - this.drivetrain.getPose().getX(),
            targetPosition[1] - this.drivetrain.getPose().getY()
        };
    }

    public double shotDistance() {
        return Math.hypot(
            targetPositionDifference()[0],
            targetPositionDifference()[1]
        );
    }

    public double heightDifference() {
        return FieldConstants.speakerHeight - ElevatorConstants.shootPosition;
    }

    public double shotTime() {
        double shotLength = Math.hypot(shotDistance(), heightDifference());
        double shootHorizontalVelocity = (shotDistance() / shotLength) * shootSpeed;
        return shotDistance() / shootHorizontalVelocity;
    }

    public double targetHeightDifference() {
        return heightDifference() + 0.5 * gravity * Math.pow(shotTime(), 2);
    }


    public Rotation2d getRotation(){
        return new Rotation2d(-targetPositionDifference()[0], -targetPositionDifference()[1]);
    }

    public double getAngle(){
        return Math.atan(targetHeightDifference() / Math.hypot(targetPositionDifference()[0], targetPositionDifference()[1]));
    }
}
