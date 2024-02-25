// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants.FieldConstants;
import frc.robot.Constants.ElevatorConstants;

/** Add your docs here. */
public class ShooterCalculator {
    private Drivetrain drivetrain;
    private double shootSpeed = 15.0;
    private double gravity = 9.8;
    private double timeDelay = 0.25;

    public ShooterCalculator(Drivetrain drivetrain){
        this.drivetrain = drivetrain;
    }

    public double[] getMovedPosition() {
        Pose2d currentPosition = this.drivetrain.getPose();
        double[] currentVelocity = this.drivetrain.getDrivetrainVelocity();
        double[] futurePosition = new double[]{
            currentPosition.getX() + currentVelocity[0] * timeDelay, 
            currentPosition.getY() + currentVelocity[1] * timeDelay
        };
        return futurePosition;
    }

    public double shotDistance() {
        return Math.hypot(
            getMovedPosition()[0] - FieldConstants.tranformPoseBluePose(FieldConstants.speakerPosition).getX(),
            getMovedPosition()[1] - FieldConstants.tranformPoseBluePose(FieldConstants.speakerPosition).getY()
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

    public double[] targetPositionDifference(){
        double[] currentVelocity = this.drivetrain.getDrivetrainVelocity();
        double[] targetPosition = new double[]{
            FieldConstants.tranformPoseBluePose(FieldConstants.speakerPosition).getX() - currentVelocity[0] * shotTime(),
            FieldConstants.tranformPoseBluePose(FieldConstants.speakerPosition).getY() - currentVelocity[1] * shotTime()
        };

        return new double[]{
            targetPosition[0] - getMovedPosition()[0],
            targetPosition[1] - getMovedPosition()[1]
        };
    }

    public Rotation2d getRotation(){
        return new Rotation2d(-targetPositionDifference()[0], -targetPositionDifference()[1]);
    }

    public double getAngle(){
        return Math.atan(targetHeightDifference() / Math.hypot(targetPositionDifference()[0], targetPositionDifference()[1]));
    }
}
