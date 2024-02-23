// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import edu.wpi.first.math.geometry.Rotation2d;
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
    public Map<String, JoystickButton> buttons = new HashMap<String, JoystickButton>();

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
        buttons.put("A", new JoystickButton(this, 1));
        buttons.put("B", new JoystickButton(this, 2));
        buttons.put("X", new JoystickButton(this, 3));
        buttons.put("Y", new JoystickButton(this, 4));
        buttons.put("L", new JoystickButton(this, 5));
        buttons.put("R", new JoystickButton(this, 6));
        buttons.put("BA", new JoystickButton(this, 7));
        buttons.put("ST", new JoystickButton(this, 8));
    }

    /**
     * Quick way to get the drivetrain output determined by the controller
     * 
     * @return A 3D Pose vector <xVel, yVel, rotVel> in the format of the WPILIB
     *         ChassisSpeeds class, factors alliance color
     */
    public ChassisSpeeds getDrivetrainOutput() {
        Alliance robotAlliance;
        try {
            robotAlliance = DriverStation.getAlliance().get();
        } catch (NoSuchElementException e) {
            robotAlliance = Alliance.Blue;
        }

        switch (robotAlliance) {
            case Blue:
                return new ChassisSpeeds(
                        -this.getLeftY() * DrivetrainConstants.translationMaxVelocity,
                        -this.getLeftX() * DrivetrainConstants.translationMaxVelocity,
                        -this.getRightX() * DrivetrainConstants.rotationMaxVelocity);
            case Red:
                return new ChassisSpeeds(
                        this.getLeftY() * DrivetrainConstants.translationMaxVelocity,
                        this.getLeftX() * DrivetrainConstants.translationMaxVelocity,
                        -this.getRightX() * DrivetrainConstants.rotationMaxVelocity);
            default:
                return new ChassisSpeeds();
        }
    }

    public ChassisSpeeds getPOVDrivetrainOutput(){
        int povAngle = this.getPOV();
        Rotation2d correctedAngle = new Rotation2d(-Math.toRadians(povAngle));

        if (povAngle == -1) {
            return null;
        } else {
            return new ChassisSpeeds(
                correctedAngle.getCos() * DrivetrainConstants.adjustmentSpeed,
                correctedAngle.getSin() * DrivetrainConstants.adjustmentSpeed,
                0.0
            );
        }
    }
}
