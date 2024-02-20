// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.Drivetrain;

/** Add your docs here. */
public class ShooterCalculator {
    private Drivetrain drivetrain;

    public ShooterCalculator(Drivetrain drivetrain){
        this.drivetrain = drivetrain;
    }

    public Rotation2d getRotation(){
        return new Rotation2d();
    }

    public double getAngle(){
        return 0;
    }
}
