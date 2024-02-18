// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.NoSuchElementException;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Vector;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 * 
 * <p>
 * All constants entered should use the SI unit system (AKA commie units).
 */
public final class Constants {

  /**
   * All Ids are stored in the ids class
   * 
   * <p>
   * Use the following format: public static {deviceName} = {IdNumber};
   */
  public static class DeviceIds {
    public static int frontLeftTurn = 1;
    public static int frontLeftDrive = 2;
    public static int frontRightDrive = 3;
    public static int frontRightTurn = 4;
    public static int backLeftDrive = 5;
    public static int backLeftTurn = 6;
    public static int backRightDrive = 7;
    public static int backRightTurn = 8;
    public static int elevatorLeft = 9;
    public static int elevatorRight = 10;
    public static int tiltLeft = 11;
    public static int tiltRight = 12;
    public static int intakeLeft = 13;
    public static int intakeRight = 14;
    public static int shooterLeft = 15;
    public static int shooterRight = 16;
    public static int climberLeft = 17;
    public static int climberRight = 18;

    public static int frontLeftEncoder = 1;
    public static int frontRightEncoder = 2;
    public static int backLeftEncoder = 3;
    public static int backRightEncoder = 4;

    public static int driverController = 0;
  }

  public static class ControllerConstants {
  }

  public static class DrivetrainConstants {
    public static double wheelBaseLength = 0.62865;
    public static double trackWidthLength = 0.62865;

    public static double MaxVoltage = 12.0;
    public static double AbsoluteMaxWheelVelocity = 5880.0 / 60.0 *
        SdsModuleConfigurations.MK4I_L2.getDriveReduction() *
        SdsModuleConfigurations.MK4I_L2.getWheelDiameter() * Math.PI;
    public static double AbsoluteMaxAngularVelocity = AbsoluteMaxWheelVelocity /
        Math.hypot(wheelBaseLength / 2.0, wheelBaseLength / 2.0);

    public static double driveCurrentLimit = 20.0;

    public static TrapezoidProfile.Constraints translationConstraints = new Constraints(5, 5);
    public static TrapezoidProfile.Constraints rotationalConstraints = new Constraints(5, 5);

    public static double translationKP = 5;
    public static double rotationKP = 5;

    public static double frontLeftEncoderOffset = Math.toRadians(-89.82) - Math.PI / 2.0;
    public static double frontRightEncoderOffset = Math.toRadians(-180.703) + Math.PI / 2.0;
    public static double backLeftEncoderOffset = Math.toRadians(-80.069) - Math.PI / 2.0;
    public static double backRightEncoderOffset = Math.toRadians(-88.330) + Math.PI / 2.0;

    public static Vector<N3> stateStandardDeviation = VecBuilder.fill(0.02, 0.02, Math.PI / 30.0);
    public static Vector<N3> visionStandardDeviation = VecBuilder.fill(0.8, 0.8, Math.PI / 2.0);

    public static double adjustmentSpeed = 0.15;
  }

  public static class ElevatorConstants {
    public static double lowerHeightLimit = 0.375;
    public static double upperHeightLimit = 0.914;

    public static double pP = 0;
    public static double pI = 0;
    public static double pD = 0;

    public static double kS = 0;
    public static double kG = 0;
    public static double kA = 0;
    public static double kV = 0;
    
    public static TrapezoidProfile.Constraints constraints = new Constraints(0.5, 0.5);

    public static double conversionFactor = 0.0169;

    public static int currentLimit = 20;
  }

  public static class TiltConstants {
    public static double lowerLimit = -Math.PI / 3.0;
    public static double upperLimit = Math.PI / 3.0;

    public static double pP = 0;
    public static double pI = 0;
    public static double pD = 0;

    public static double kS = 0;
    public static double kG = 0;
    public static double kA = 0;
    public static double kV = 0;
    
    public static TrapezoidProfile.Constraints constraints = new Constraints(0.5, 0.5);

    public static double conversionFactor = 0.1047;

    public static int currentLimit = 10;
  }

  public static class ShooterConstants {
    public static double idleVoltage = 4.0;
    public static double shootingVoltage = 10;

    public static int currentLimit = 20;
  }

  public static class IntakeConstants {
    public static double idleVoltage = 0.0;
    public static double intakeVoltage = 10.0;
    public static double exitVoltage = -10.0;

    public static int currentLimit = 30;
  }

  public static class AutoConstants {
    public static double maxVelocity = 2;
    public static double maxAcceleration = 1.5;
  }

  public static class BlueTeamWaypoints {
    public static Pose2d speaker = new Pose2d(1.40,5.60,new Rotation2d(-90.00));
    public static Pose2d amp = new Pose2d(1.85,7.25,new Rotation2d(0.00));
    public static Pose2d pickup = new Pose2d(15.15,1.60,new Rotation2d(30.00));
    public static Pose2d chainLeft = new Pose2d(4.00,5.50,new Rotation2d(30.00));
    public static Pose2d chainRight = new Pose2d(4.00,2.65,new Rotation2d(-30.00));
    public static Pose2d chainBack = new Pose2d(6.40,4.10,new Rotation2d(90.00));
  }

  public static class RedTeamWaypoints {
    public static Pose2d speaker = new Pose2d(15.10,5.65,new Rotation2d(90.00));
    public static Pose2d amp = new Pose2d(14.85,7.15,new Rotation2d(180));
    public static Pose2d pickup = new Pose2d(1.20,1.75,new Rotation2d(150.00));
    public static Pose2d chainLeft = new Pose2d(12.55,2.65,new Rotation2d(-150.00));
    public static Pose2d chainRight = new Pose2d(12.60,5.50,new Rotation2d(150.00));
    public static Pose2d chainBack = new Pose2d(10.15,4.00,new Rotation2d(90.00));
  }

  public static class TeamWaypoints {
    public static Pose2d speaker;
    public static Pose2d amp;
    public static Pose2d pickup;
    public static Pose2d chainLeft;
    public static Pose2d chainRight;
    public static Pose2d chainBack;
    public static void main(String[] args) {
      Alliance robotAlliance;
        try {
            robotAlliance = DriverStation.getAlliance().get();
        } catch (NoSuchElementException e) {
            robotAlliance = Alliance.Blue;
        }

        switch (robotAlliance) {
            case Blue:
                speaker = BlueTeamWaypoints.speaker;
                amp = BlueTeamWaypoints.amp;
                pickup = BlueTeamWaypoints.pickup;
                chainLeft = BlueTeamWaypoints.chainLeft;
                chainRight = BlueTeamWaypoints.chainRight;
                chainBack = BlueTeamWaypoints.chainBack;
            case Red:
                speaker = RedTeamWaypoints.speaker;
                amp = RedTeamWaypoints.amp;
                pickup = RedTeamWaypoints.pickup;
                chainLeft = RedTeamWaypoints.chainLeft;
                chainRight = RedTeamWaypoints.chainRight;
                chainBack = RedTeamWaypoints.chainBack;
        }
    }
    }
  }
