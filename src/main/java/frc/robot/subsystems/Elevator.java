// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DeviceIds;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  private PIDController elevatorController = new PIDController(
    ElevatorConstants.pP, 
    ElevatorConstants.pI,
    ElevatorConstants.pD
  );

  private CANSparkMax leftMotor = new CANSparkMax(DeviceIds.elevatorLeft, MotorType.kBrushless);
  private CANSparkMax rightMotor = new CANSparkMax(DeviceIds.elevatorRight, MotorType.kBrushless);

  private RelativeEncoder encoder;

  private double goalPosition = ElevatorConstants.lowerHeightLimit;


  public Elevator() {
    leftMotor.setInverted(true);
    rightMotor.setInverted(false);
    leftMotor.setSmartCurrentLimit(ElevatorConstants.currentLimit);
    rightMotor.setSmartCurrentLimit(ElevatorConstants.currentLimit);

    encoder = leftMotor.getEncoder();
    encoder.setPositionConversionFactor(ElevatorConstants.conversionFactor);
    encoder.setPosition(ElevatorConstants.lowerHeightLimit);
  }

  @Override
  public void periodic() {
    setVoltage(
      MathUtil.clamp(
        elevatorController.calculate(encoder.getPosition(), goalPosition), 
        -ElevatorConstants.maxVoltage, 
        ElevatorConstants.maxVoltage
      ) + ElevatorConstants.kS
    );
  }

  private void setVoltage(double voltage) {
    leftMotor.setVoltage(voltage);
    rightMotor.setVoltage(voltage);
  }

  public void setPosition(double position) {
    goalPosition = position;
  }

  public boolean atPosition() {
    return elevatorController.atSetpoint();
  }
}
