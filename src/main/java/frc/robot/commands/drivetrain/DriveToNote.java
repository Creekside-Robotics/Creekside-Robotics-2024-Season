package frc.robot.commands.drivetrain;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.DriverController;
import frc.robot.utils.RotationSupplier;

public class DriveToNote extends DriveToPosePID{
    public DriveToNote(Drivetrain drivetrain, DriverController driverController, RotationSupplier rotationSupplier){
        super(
            drivetrain,
            driverController,
            () -> new Pose2d(
                drivetrain.getPose().getTranslation().getX()+5*drivetrain.getPose().getRotation().plus(new Rotation2d(rotationSupplier.getValue())).getCos(), // Moves the robot on the straight linetowards the note
                drivetrain.getPose().getTranslation().getY()+5*drivetrain.getPose().getRotation().plus(new Rotation2d(rotationSupplier.getValue())).getSin(), //
                drivetrain.getPose().getRotation().plus(new Rotation2d(rotationSupplier.getValue()))),
            new boolean[]{false, false, true},
            new Pose2d(0.03, 0.03, new Rotation2d(0.03)),
            true
        );
    }
}
