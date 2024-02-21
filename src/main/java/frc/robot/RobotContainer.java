// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.composite.PrepShot;
import frc.robot.commands.composite.ShootNote;
import frc.robot.commands.drivetrain.DriveToAmp;
import frc.robot.commands.drivetrain.DriveToPickup;
import frc.robot.commands.drivetrain.DriveToShoot;
import frc.robot.commands.drivetrain.ManualDrive;
import frc.robot.commands.intake.IntakeAmp;
import frc.robot.commands.intake.IntakeNote;
import frc.robot.commands.intake.SetIntakeVoltage;
import frc.robot.commands.shooter.RevShooter;
import frc.robot.commands.shooter.SetShooterVoltage;
import frc.robot.commands.tower.RetractTower;
import frc.robot.commands.tower.SetAmpTower;
import frc.robot.commands.tower.SetPickupTower;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tilt;
import frc.robot.utils.DriverController;
import frc.robot.utils.ShooterCalculator;

/**
 * This class is where the bulk of the robot is declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here.
    private final Drivetrain drivetrain = new Drivetrain();
    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Elevator elevator = new Elevator();
    private final Tilt tilt = new Tilt();

    private final DriverController mainController = new DriverController(Constants.DeviceIds.driverController);
    private final DriverController alternateController = new DriverController(Constants.DeviceIds.alternateController);

    private final ShooterCalculator shooterCalculator = new ShooterCalculator(drivetrain);

    private final SendableChooser<Command> commandChooser = new SendableChooser<>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * This command is used to configure command and button bindings.
     */
    private void configureButtonBindings() {
        this.drivetrain.setDefaultCommand(new ManualDrive(drivetrain, mainController));
        this.shooter.setDefaultCommand(new SetShooterVoltage(shooter, ShooterConstants.idleVoltage));
        this.intake.setDefaultCommand(new SetIntakeVoltage(intake, 0));

        this.mainController.buttons.get("B").whileTrue(
            new ParallelCommandGroup(
                new DriveToShoot(drivetrain, mainController, shooterCalculator),
                new PrepShot(shooter, elevator, tilt, shooterCalculator)
            )
        );
        this.mainController.buttons.get("B").onFalse(
            new SequentialCommandGroup(
                new ShootNote(shooter, intake),
                new RetractTower(elevator, tilt)
            )
        );

        this.mainController.buttons.get("Y").whileTrue(
            new ParallelCommandGroup(
                new DriveToAmp(drivetrain, mainController),
                new SetAmpTower(elevator, tilt)
            )
        );
        this.mainController.buttons.get("Y").onFalse(
            new SequentialCommandGroup(
                new IntakeAmp(intake),
                new RetractTower(elevator, tilt)
            )
        );

        this.mainController.buttons.get("A").whileTrue(
            new IntakeNote(intake)
        );

        this.mainController.buttons.get("X").whileTrue(
            new ParallelCommandGroup(
                new DriveToPickup(drivetrain, mainController),
                new SetPickupTower(elevator, tilt),
                new IntakeNote(intake)
            )
        );
        this.mainController.buttons.get("X").onFalse(
            new RetractTower(elevator, tilt)
        );

        this.alternateController.buttons.get("B").whileTrue(
            new SequentialCommandGroup(
                new RevShooter(shooter),
                new ShootNote(shooter, intake)
            )
        );

        this.alternateController.buttons.get("Y").whileTrue(
            new SetAmpTower(elevator, tilt)
        );
        this.alternateController.buttons.get("Y").onFalse(
            new SequentialCommandGroup(
                new IntakeAmp(intake),
                new RetractTower(elevator, tilt)
            )
        );

        this.alternateController.buttons.get("A").whileTrue(
            new RetractTower(elevator, tilt)
        );

        this.alternateController.buttons.get("X").whileTrue(
            new ParallelCommandGroup(
                new SetPickupTower(elevator, tilt),
                new IntakeNote(intake)
            )
        );
        this.alternateController.buttons.get("X").onFalse(
            new RetractTower(elevator, tilt)
        );
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return this.commandChooser.getSelected();
    }
}
