// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DeviceIds;
import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase{
    /** Creates a new Climber **/
    private CANSparkMax leftMotor = new CANSparkMax(DeviceIds.climberLeft, MotorType.kBrushless);
    private CANSparkMax rightMotor = new CANSparkMax(DeviceIds.climberRight, MotorType.kBrushless);

    private RelativeEncoder encoder;
    
    public Climber(){
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);
        leftMotor.setSmartCurrentLimit(ClimberConstants.currentLimit);
        rightMotor.setSmartCurrentLimit(ClimberConstants.currentLimit);

        encoder = leftMotor.getEncoder();
        encoder.setPositionConversionFactor(ClimberConstants.conversionRate);
    }

    public void setVoltage (double voltage) {
        leftMotor.setVoltage(voltage);
        rightMotor.setVoltage(voltage);
    }

    public int getPosition() {
        /** 
         * 1 - extended
         * 0 - retracted 
        **/

        double position = encoder.getPosition();
        if (position >= 1) return 1;
        else return 0;
    }
}
