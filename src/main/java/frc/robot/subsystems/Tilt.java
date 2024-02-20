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
import frc.robot.Constants.TiltConstants;

public class Tilt extends SubsystemBase {
  /** Creates a new Elevator. */
  private PIDController tiltController = new PIDController(
    TiltConstants.pP, 
    TiltConstants.pI,
    TiltConstants.pD
  );

  private CANSparkMax leftMotor = new CANSparkMax(DeviceIds.tiltLeft, MotorType.kBrushless);
  private CANSparkMax rightMotor = new CANSparkMax(DeviceIds.tiltRight, MotorType.kBrushless);

  private RelativeEncoder encoder;

  private double goalPosition = TiltConstants.upperLimit;


  public Tilt() {
    leftMotor.setInverted(true);
    rightMotor.setInverted(false);
    leftMotor.setSmartCurrentLimit(TiltConstants.currentLimit);
    rightMotor.setSmartCurrentLimit(TiltConstants.currentLimit);

    encoder = leftMotor.getEncoder();
    encoder.setPositionConversionFactor(TiltConstants.conversionFactor);
    encoder.setPosition(TiltConstants.upperLimit);

    tiltController.setTolerance(TiltConstants.tolerance);
  }

  @Override
  public void periodic() {
    setVoltage(
      MathUtil.clamp(
        tiltController.calculate(encoder.getPosition(), goalPosition), 
        -TiltConstants.maxVoltage, 
        TiltConstants.maxVoltage
      ) + TiltConstants.kS
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
    return tiltController.atSetpoint();
  }
}
