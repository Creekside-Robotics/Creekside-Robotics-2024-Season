// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.tower;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.TiltConstants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Tilt;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetTowerPosition extends SequentialCommandGroup {
  /** Creates a new SetTowerPosition. */
  public SetTowerPosition(Elevator elevator, Tilt tilt, double elevatorPosition, double tiltPosition) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetTiltPosition(tilt, TiltConstants.upperLimit, false),
      new SetElevatorPosition(elevator, elevatorPosition, false),
      new SetTiltPosition(tilt, tiltPosition, false)
    );
  }

  public SetTowerPosition(Elevator elevator, Tilt tilt, double elevatorPosition, Supplier<Double> tiltPositionSupplier) {
    addCommands(
      new SetTiltPosition(tilt, TiltConstants.upperLimit, false),
      new SetElevatorPosition(elevator, elevatorPosition, false),
      new SetTiltPosition(tilt, tiltPositionSupplier, true)
    );
  }

}
