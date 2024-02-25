// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class SetShooterVoltage extends Command {
  /** Creates a new SetShooterVoltage. */
  private Shooter shooter;
  private double voltage;

  public SetShooterVoltage(Shooter shooter, double voltage) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    this.voltage = voltage;

    addRequirements(this.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.shooter.setVoltage(voltage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.shooter.setVoltage(ShooterConstants.idleVoltage);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
