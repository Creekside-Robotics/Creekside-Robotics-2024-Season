// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Hook;

public class RetractArm extends Command{
    /* Figure out encoder's position output to determine further branching*/

    private Hook hook;

    public RetractArm(Hook hook){
        // Use addRequirements() here to declare subsystem dependencies.
        this.hook = hook;
        
        addRequirements(this.hook);
    }

     // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        this.hook.setVoltage(ClimberConstants.retractVoltage);        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        this.hook.setVoltage(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return this.hook.getPosition() <= 0.0 || this.hook.getVelocity() >= 0;
    }
}
