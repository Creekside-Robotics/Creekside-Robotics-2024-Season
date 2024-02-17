// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DrivetrainConstants;

/**
 * DriveController serves as an extention to the typical Joystick
 * with additional support for commonly used functions.
 */
public class DriverController extends XboxController {
    public Map<Integer, JoystickButton> buttons = new HashMap<Integer, JoystickButton>();

    /**
     * Creates a new drive controller, extends the Joystick class with additional
     * functionality.
     * 
     * @param port Port number of the controller (start at 0)
     */
    public DriverController(int port) {
        super(port);
        this.generateButtons();
    }

    private void generateButtons() {
        buttons.put(1, new JoystickButton(this, 1));
        buttons.put(2, new JoystickButton(this, 2));
        buttons.put(3, new JoystickButton(this, 3));
        buttons.put(4, new JoystickButton(this, 4));
        buttons.put(5, new JoystickButton(this, 5));
        buttons.put(6, new JoystickButton(this, 6));
        buttons.put(7, new JoystickButton(this, 7));
        buttons.put(8, new JoystickButton(this, 8));
        buttons.put(9, new JoystickButton(this, 9));
        buttons.put(10, new JoystickButton(this, 10));
        buttons.put(11, new JoystickButton(this, 11));
        buttons.put(12, new JoystickButton(this, 12));

    }

    /**
     * Quick way to get the drivetrain output determined by the controller
     * 
     * @return A 3D Pose vector <xVel, yVel, rotVel> in the format of the WPILIB
     *         ChassisSpeeds class, factors alliance color
     */
    public ChassisSpeeds getDrivetrainOutput(boolean fieldOriented) {
        double controllerThrottle = 1.0 - this.getRightTriggerAxis();
        Alliance robotAlliance;
        try {
            robotAlliance = DriverStation.getAlliance().get();
        } catch (NoSuchElementException e) {
            robotAlliance = Alliance.Blue;
        }

        switch (robotAlliance) {
            case Blue:
                return new ChassisSpeeds(
                        -this.getLeftY() * DrivetrainConstants.translationConstraints.maxVelocity * controllerThrottle,
                        -this.getLeftX() * DrivetrainConstants.translationConstraints.maxVelocity * controllerThrottle,
                        -this.getRightX() * DrivetrainConstants.rotationalConstraints.maxVelocity * controllerThrottle);
            case Red:
                return new ChassisSpeeds(
                        this.getLeftY() * DrivetrainConstants.translationConstraints.maxVelocity * controllerThrottle,
                        this.getLeftX() * DrivetrainConstants.translationConstraints.maxVelocity * controllerThrottle,
                        -this.getRightX() * DrivetrainConstants.rotationalConstraints.maxVelocity * controllerThrottle);
            default:
                return new ChassisSpeeds();
        }
    }
}