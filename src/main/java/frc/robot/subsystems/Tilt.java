// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  

  public Tilt() {
    leftMotor.setInverted(true);
    rightMotor.setInverted(false);
    leftMotor.setSmartCurrentLimit(TiltConstants.currentLimit);
    rightMotor.setSmartCurrentLimit(TiltConstants.currentLimit);
    leftMotor.setIdleMode(IdleMode.kCoast);
    rightMotor.setIdleMode(IdleMode.kCoast);

    encoder = leftMotor.getEncoder();
    encoder.setPositionConversionFactor(TiltConstants.conversionFactor);
    encoder.setPosition(TiltConstants.upperLimit);

    tiltController.setTolerance(TiltConstants.tolerance);
    tiltController.setSetpoint(TiltConstants.upperLimit);
  }

  @Override
  public void periodic() {
    setVoltage(
      MathUtil.clamp(
        tiltController.calculate(encoder.getPosition()), 
        -TiltConstants.maxVoltage, 
        TiltConstants.maxVoltage
      ) + TiltConstants.kS * Math.sin(encoder.getPosition() - TiltConstants.hangingAngle)
    );
    SmartDashboard.putNumber("Tilt Position", this.encoder.getPosition());
  }

  private void setVoltage(double voltage) {
    leftMotor.setVoltage(voltage);
    rightMotor.setVoltage(voltage);
  }

  public void setPosition(double position) {
    if (position > TiltConstants.hangingAngle) {
      tiltController.setSetpoint(position + TiltConstants.backlashAngle);
    } else {
      tiltController.setSetpoint(position);
    }
  }

  public boolean atPosition() {
    return tiltController.atSetpoint();
  }
}
