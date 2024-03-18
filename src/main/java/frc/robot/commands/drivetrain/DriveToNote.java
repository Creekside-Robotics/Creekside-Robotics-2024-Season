// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;

/** Add your docs here. */
public class DriveToNote extends SetDrivetrainOutput{
    public DriveToNote(Drivetrain drivetrain) {
        super(
            drivetrain,
            () -> new ChassisSpeeds(
                DrivetrainConstants.autoIntakeSpeed,
                0.0,
                drivetrain.getNoteAlignmentDrivetrainOutput()
            ),
            false
        );
    }
}
