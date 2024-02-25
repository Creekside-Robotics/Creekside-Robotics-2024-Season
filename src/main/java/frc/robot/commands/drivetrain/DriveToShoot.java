// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.DriverController;
import frc.robot.utils.ShooterCalculator;

/** Add your docs here. */
public class DriveToShoot extends DriveToPosePID{
    public DriveToShoot(Drivetrain drivetrain, DriverController driverController, ShooterCalculator calculator) {
        super(
            drivetrain, 
            driverController, 
            () -> new Pose2d(0, 0, calculator.getRotation()), 
            new boolean[]{false, false, true}, 
            new Pose2d(0.03, 0.03, new Rotation2d(0.03)),
            true
        );
    }
}
