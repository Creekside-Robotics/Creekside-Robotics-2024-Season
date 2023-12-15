// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants.AutoConstants;
import frc.robot.commands.drivetrain.ManualDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.DriverController;

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

    private final DriverController mainController = new DriverController(Constants.DeviceIds.driverController);

    private final SendableChooser<Command> commandChooser = new SendableChooser<>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        configureAutoCommands();
    }

    /**
     * Builds autoCommand based on the pathgroup provided.
     * 
     * @param pathName Name of pathplanner file
     * @return Full Auto command
     */
    public Command buildAutoCommand(String pathName) {
        ArrayList<PathPlannerTrajectory> pathGroup = (ArrayList<PathPlannerTrajectory>) PathPlanner
                .loadPathGroup(pathName, new PathConstraints(AutoConstants.maxVelocity, AutoConstants.maxAcceleration));

        HashMap<String, Command> eventMap = new HashMap<>();

        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
                drivetrain::getPose,
                drivetrain::setDrivetrainPose,
                AutoConstants.translationConstants,
                AutoConstants.rotationConstants,
                drivetrain::setDrivetrainSpeedsAuto,
                eventMap,
                drivetrain);

        return autoBuilder.fullAuto(pathGroup);
    }

    /**
     * This command configures auto-commands and displays for driver to choose.
     */
    private void configureAutoCommands() {
        this.commandChooser.setDefaultOption("None", null);

        File[] autoRoutines = new File(Filesystem.getDeployDirectory()+"/pathplanner").listFiles();
        for(File file : autoRoutines) {
                if (!file.isFile()) continue;
                String fileName = file.getName().replace(".path", "");
                this.commandChooser.addOption(fileName, buildAutoCommand(fileName));
        }

        SmartDashboard.putData(this.commandChooser);
    }

    /**
     * This command is used to configure command and button bindings.
     */
    private void configureButtonBindings() {
        this.drivetrain.setDefaultCommand(new ManualDrive(drivetrain, mainController));
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
