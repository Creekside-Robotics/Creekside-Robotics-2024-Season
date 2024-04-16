package frc.robot.commands.composite;

import frc.robot.commands.intake.IntakeNote;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

public class IntakeGroundAuto extends ParallelDeadlineGroup {    
    public IntakeGroundAuto(Drivetrain drivetrain, Intake intake) {
        super(
            new IntakeNote(intake),
            new IntakeGroundAuto(drivetrain, intake)
        );    
    }
}
