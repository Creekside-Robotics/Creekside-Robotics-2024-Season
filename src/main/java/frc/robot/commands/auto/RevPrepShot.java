// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.commands.shooter.RevShooter;
import frc.robot.commands.tower.SetShootTower;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tilt;
import frc.robot.utils.ShooterCalculator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RevPrepShot extends ParallelDeadlineGroup {
  /** Creates a new RevPrepShot. */
  public RevPrepShot(Elevator elevator, Tilt tilt, Shooter shooter, ShooterCalculator shooterCalculator) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(
      new RevShooter(shooter),
      new SetShootTower(elevator, tilt, shooterCalculator)
    );
    // addCommands(new FooCommand(), new BarCommand());
  }
}
