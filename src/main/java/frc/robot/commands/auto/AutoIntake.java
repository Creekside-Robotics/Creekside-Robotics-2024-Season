// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.commands.intake.IntakeNote;
import frc.robot.commands.tower.SetIntakeTower;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Tilt;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoIntake extends ParallelDeadlineGroup {
  /** Creates a new AutoAlignmentIntake. */
  public AutoIntake(Drivetrain drivetrain, Intake intake, Elevator elevator, Tilt tilt) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(
      new IntakeNote(intake),
      new SetIntakeTower(elevator, tilt)
    );
    // addCommands(new FooCommand(), new BarCommand());
  }
}
