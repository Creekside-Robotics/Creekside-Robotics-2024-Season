// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Climber;

public class ExtendArms extends Commands {
    /* Figure out encoder's position output to determine further branching*/
    
    private Climber climber;
    private double voltage;

    public ExtendArms(){
        // Use addRequirements() here to declare subsystem dependencies.
        this.climber = climber;

        addRequirements(climber);
    }

     // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        this.voltage = ClimberConstants.extendVoltage;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (climber.getPosition() == 1) return; /* Skip if hooks are already extended */
        /* Keep going until climbers encoder stops updating its value with the same rate */
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return this.climber.notePresent();
    }
}
