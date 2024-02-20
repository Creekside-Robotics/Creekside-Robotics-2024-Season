// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.tower;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Tilt;

public class SetTiltPosition extends Command {
  /** Creates a new SetTiltPosition. */
  private Tilt tilt;
  private Supplier<Double> positionSupplier;
  private boolean hold;

  public SetTiltPosition(Tilt tilt, double position, boolean hold) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.tilt = tilt;
    this.positionSupplier = () -> position;
    this.hold = hold;

    addRequirements(this.tilt);
  }

  public SetTiltPosition(Tilt tilt, Supplier<Double> positionSupplier, boolean hold) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.tilt = tilt;
    this.positionSupplier = positionSupplier;
    this.hold = hold;

    addRequirements(this.tilt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.tilt.setPosition(this.positionSupplier.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !this.hold && this.tilt.atPosition();
  }
}
