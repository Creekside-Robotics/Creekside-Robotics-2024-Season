// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants.FieldConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.DriverController;

/** Add your docs here. */
public class DriveToPickup extends DriveToPosePID{
    public DriveToPickup(Drivetrain drivetrain, DriverController driverController) {
        super(
            drivetrain, 
            driverController, 
            () -> FieldConstants.tranformPoseBluePose(FieldConstants.pickup), 
            new boolean[]{true, true, true}, 
            new Pose2d(0.03, 0.03, new Rotation2d(0.03)),
            false
        );
    }
}
