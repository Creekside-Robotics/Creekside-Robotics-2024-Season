// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DeviceIds;

public class Climber extends SubsystemBase{
    /** Creates a new Climber **/

    public Hook leftHook = new Hook(DeviceIds.climberLeft);
    public Hook rightHook = new Hook(DeviceIds.climberRight);
    
    public Climber(){}

    public void setVoltage (double voltage) {
        leftHook.setVoltage(voltage);
        rightHook.setVoltage(voltage);
    }

    @Override
    public void periodic() {
        // Left logs
        SmartDashboard.putNumber("LeftHookPosition", leftHook.getPosition());
        SmartDashboard.putNumber("LeftHookVelocity", leftHook.getVelocity());
        // Right logs
        SmartDashboard.putNumber("RightHookPosition", rightHook.getPosition());
        SmartDashboard.putNumber("RightHookVelocity", rightHook.getVelocity());
    }
}