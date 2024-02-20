// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.composite;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.shooter.SetShoot;
import frc.robot.commands.tower.SetShootTower;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tilt;
import frc.robot.utils.ShooterCalculator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PrepShot extends ParallelCommandGroup {
  /** Creates a new PrepShot. */
  public PrepShot(Shooter shooter, Elevator elevator, Tilt tilt, ShooterCalculator shooterCalculator) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetShootTower(elevator, tilt, shooterCalculator),
      new SetShoot(shooter)
    );
  }
}
