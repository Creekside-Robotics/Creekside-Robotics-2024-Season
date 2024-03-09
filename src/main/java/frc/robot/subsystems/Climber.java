// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.DeviceIds;

public class Climber extends SubsystemBase{
    /** Creates a new Climber **/

    public CANSparkMax motor = new CANSparkMax(DeviceIds.climber, MotorType.kBrushless);
    public RelativeEncoder encoder;
    public Climber(){
            motor.setInverted(false);
            motor.setSmartCurrentLimit(ClimberConstants.currentLimit);

            encoder = motor.getEncoder();
            encoder.setPositionConversionFactor(ClimberConstants.positionConversionRate);
            encoder.setVelocityConversionFactor(ClimberConstants.positionConversionRate/(60.0));
            encoder.setPosition(1.0);
        }
    
    public void setVoltage (double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Hooks Position", encoder.getPosition());
        SmartDashboard.putNumber("Hooks Velocity", encoder.getVelocity());
    }
}