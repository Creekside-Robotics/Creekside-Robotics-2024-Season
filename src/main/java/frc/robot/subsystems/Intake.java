// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DeviceIds;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax leftMotor = new CANSparkMax(DeviceIds.intakeLeft, MotorType.kBrushless);
  private CANSparkMax rightMotor = new CANSparkMax(DeviceIds.intakeRight, MotorType.kBrushless);

  private ColorSensorV3 colorSensor = new ColorSensorV3(Port.kOnboard);

  private final ColorMatch colorMatcher = new ColorMatch();
  
  public Intake() {
    leftMotor.setInverted(false);
    rightMotor.setInverted(false);
    leftMotor.setSmartCurrentLimit(IntakeConstants.currentLimit);
    rightMotor.setSmartCurrentLimit(IntakeConstants.currentLimit);

    this.colorSensor.configureColorSensor(ColorSensorV3.ColorSensorResolution.kColorSensorRes16bit, ColorSensorV3.ColorSensorMeasurementRate.kColorRate25ms, ColorSensorV3.GainFactor.kGain3x);

    colorMatcher.addColorMatch(Color.kOrange);
    colorMatcher.setConfidenceThreshold(0.5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run'
    double getColor = colorSensor.getRed();
    SmartDashboard.putBoolean("Note", getColor > IntakeConstants.redLimit);
  }

  public void setVoltage(double voltage) {
    leftMotor.setVoltage(voltage);
    rightMotor.setVoltage(voltage);
  }

  public void setCurvedVoltage(double voltage) {
    leftMotor.setVoltage(voltage);
    rightMotor.setVoltage(voltage / 2.0);
  }

  public boolean notePresent() {
    boolean getProximity = colorSensor.getRed() > IntakeConstants.redLimit;
    return getProximity;
  }
}
