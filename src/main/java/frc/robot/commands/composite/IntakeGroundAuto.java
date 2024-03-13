package frc.robot.commands.composite;

import frc.robot.commands.drivetrain.DriveToNote;
import frc.robot.commands.intake.IntakeNote;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.utils.DriverController;
import frc.robot.utils.RotationSupplier;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class IntakeGroundAuto extends ParallelCommandGroup {    
    public IntakeGroundAuto(Intake intake, Drivetrain drivetrain, DriverController driverController, RotationSupplier rotationSupplier) {
        addCommands(
            new IntakeNote(intake),
            new DriveToNote(drivetrain, driverController, rotationSupplier)
        );
    }

    
}
