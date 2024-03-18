package frc.robot.commands.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;


public class setDrivetrainToNoteOutput extends SetDrivetrainOutput{
    public setDrivetrainToNoteOutput(Drivetrain drivetrain){
        super(
            drivetrain,
            () -> new ChassisSpeeds(
                DrivetrainConstants.autoIntakeSpeed,
                0.0,
                drivetrain.getNoteRotationDrivetrainOutput()
            ),
            false
        );
    }
}
