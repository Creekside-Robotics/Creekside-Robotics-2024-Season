// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.composite.ShootNote;
import frc.robot.commands.drivetrain.DriveToShoot;
import frc.robot.commands.shooter.RevShooter;
import frc.robot.commands.tower.SetShootTower;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tilt;
import frc.robot.utils.DriverController;
import frc.robot.utils.ShooterCalculator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShoot extends ParallelDeadlineGroup {
  /** Creates a new RevPrepShot. */
  public AutoShoot(Elevator elevator, Tilt tilt, Shooter shooter, Intake intake, Drivetrain drivetrain, DriverController driverController, ShooterCalculator shooterCalculator) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(
      new SequentialCommandGroup(
        new RevShooter(shooter),
        new ShootNote(shooter, intake)
      ),
      new DriveToShoot(drivetrain, driverController, shooterCalculator),
      new SetShootTower(elevator, tilt, shooterCalculator)
    );
    // addCommands(new FooCommand(), new BarCommand());
  }
}
