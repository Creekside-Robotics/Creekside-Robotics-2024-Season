// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.tower;

import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Tilt;
import frc.robot.utils.ShooterCalculator;

/** Add your docs here. */
public class SetShootTower extends SetTowerPosition {
    public SetShootTower(Elevator elevator, Tilt tilt, ShooterCalculator calculator) {
        super(elevator, tilt, ElevatorConstants.shootPosition, calculator::getAngle);
    }
}
