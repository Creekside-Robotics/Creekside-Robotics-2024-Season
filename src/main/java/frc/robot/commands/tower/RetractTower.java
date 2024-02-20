// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.tower;

import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.TiltConstants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Tilt;

/** Add your docs here. */
public class RetractTower extends SetTowerPosition {
    public RetractTower(Elevator elevator, Tilt tilt) {
        super(elevator, tilt, ElevatorConstants.lowerHeightLimit, TiltConstants.upperLimit);
    }
}
