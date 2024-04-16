package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.composite.IntakeGroundAuto;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class AutoIntake extends ParallelCommandGroup {
    public AutoIntake(Drivetrain drivetrain, Intake intake) {
        addCommands(new IntakeGroundAuto(drivetrain, intake).withTimeout(IntakeConstants.autoIntakeTime));
    }
}
