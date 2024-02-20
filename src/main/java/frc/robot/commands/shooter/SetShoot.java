// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

/** Add your docs here. */
public class SetShoot extends SetShooterVoltage {
    public SetShoot(Shooter shooter) {
        super(shooter, ShooterConstants.shootingVoltage);
    }
}
