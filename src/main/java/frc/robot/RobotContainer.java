// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix6.SignalLogger;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutoConstants;
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
import frc.robot.commands.tower.SetIntakeTower;
import frc.robot.commands.tower.SetPickupTower;
import frc.robot.commands.auto.AutoCycle;
import frc.robot.commands.auto.RevPrepShot;
import frc.robot.commands.climber.ExtendArm;
import frc.robot.commands.climber.RetractArm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tilt;
import frc.robot.subsystems.Climber;
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
    private final Climber climber = new Climber();

    private final DriverController mainController = new DriverController(Constants.DeviceIds.driverController);
    private final DriverController alternateController = new DriverController(Constants.DeviceIds.alternateController);

    private final ShooterCalculator shooterCalculator = new ShooterCalculator(drivetrain);

    private SendableChooser<Command> commandChooser;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        SignalLogger.enableAutoLogging(false);
        configureButtonBindings();
        configureAutoCommands();
    }

    public void configureAutoCommands() {
        AutoBuilder.configureHolonomic(
            drivetrain::getPose, // Robot pose supplier
            drivetrain::setDrivetrainPose, // Method to reset odometry (will be called if your auto has a starting pose)
            drivetrain::getChassisSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
            drivetrain::setDrivetrainSpeedsAuto, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
            AutoConstants.autoConfig,
            () -> {
              // Boolean supplier that controls when the path will be mirrored for the red alliance
              // This will flip the path being followed to the red side of the field.
              // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

              var alliance = DriverStation.getAlliance();
              if (alliance.isPresent()) {
                return alliance.get() == DriverStation.Alliance.Red;
              }
              return false;
            },
            drivetrain // Reference to this subsystem to set requirements
    );

    NamedCommands.registerCommand("Prep", new RevPrepShot(elevator, tilt, shooter, shooterCalculator));
    NamedCommands.registerCommand("Intermediate", new AutoCycle(elevator, tilt, shooter, intake, shooterCalculator));
    NamedCommands.registerCommand("Shoot", new ShootNote(shooter, intake));
    NamedCommands .registerCommand("PrepNoTime", new PrepShot(shooter, elevator, tilt, shooterCalculator));
    
    this.commandChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", this.commandChooser);
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
            new ParallelCommandGroup(
                new IntakeNote(intake),
                new SetIntakeTower(elevator, tilt)
            )
        );

        this.mainController.buttons.get("A").onFalse(
            new RetractTower(elevator, tilt)
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

        this.mainController.buttons.get("L").whileTrue(
            new ParallelCommandGroup(
                new IntakeNote(intake),
                new SetIntakeTower(elevator, tilt)
            )
        );

        this.mainController.buttons.get("L").onFalse(
            new RetractTower(elevator, tilt)
        );

        this.mainController.buttons.get("R").whileTrue(
            new ParallelCommandGroup(
                new RetractArm(climber.leftHook),
                new RetractArm(climber.rightHook),
                new SetIntakeTower(elevator, tilt)
            )
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

        this.alternateController.buttons.get("L").whileTrue(
            new ParallelCommandGroup(
                new ExtendArm(climber.leftHook),
                new ExtendArm(climber.rightHook)
            )
        );

        this.alternateController.buttons.get("R").whileTrue(
            new ParallelCommandGroup(
                new RetractArm(climber.leftHook),
                new RetractArm(climber.rightHook)
            )
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
