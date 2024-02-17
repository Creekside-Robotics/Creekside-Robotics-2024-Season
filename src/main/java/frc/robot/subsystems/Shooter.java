// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DeviceIds;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private CANSparkMax leftMotor = new CANSparkMax(DeviceIds.shooterLeft, MotorType.kBrushless);
  private CANSparkMax rightMotor = new CANSparkMax(DeviceIds.shooterRight, MotorType.kBrushless);

  public Shooter() {
    leftMotor.setInverted(true);
    rightMotor.setInverted(true);
    leftMotor.setSmartCurrentLimit(ShooterConstants.currentLimit);
    rightMotor.setSmartCurrentLimit(ShooterConstants.currentLimit);
  }

  @Override
  public void periodic() {}

  public void setVoltage (double voltage) {
    leftMotor.setVoltage(voltage);
    rightMotor.setVoltage(voltage);
  }
}
