// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import java.lang.reflect.Array;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Climber;

public class RetractHooks extends Command{
    /* Figure out encoder's position output to determine further branching*/

    private Climber climber;

    public RetractHooks(){
        // Use addRequirements() here to declare subsystem dependencies.
        this.climber = climber;
        
        addRequirements(this.climber);
    }

     // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        climber.setVoltage(ClimberConstants.retractVoltage);        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return this.climber.getVelocity() <= 0;
    }
}
