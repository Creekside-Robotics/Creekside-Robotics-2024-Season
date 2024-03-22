// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.composite.PrepShot;
import frc.robot.commands.composite.ShootNote;
import frc.robot.commands.intake.IntakeNote;
import frc.robot.commands.tower.SetIntakeTower;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tilt;
import frc.robot.utils.ShooterCalculator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoCycle extends SequentialCommandGroup {
  /** Creates a new AutoCycle. */
  public AutoCycle(Elevator elevator, Tilt tilt, Shooter shooter, Intake intake, ShooterCalculator shooterCalculator) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShootNote(shooter, intake),
      new ParallelDeadlineGroup(
        new IntakeNote(intake),
        new SetIntakeTower(elevator, tilt)
      ),
      new PrepShot(shooter, elevator, tilt, shooterCalculator)
    );
  }
}
