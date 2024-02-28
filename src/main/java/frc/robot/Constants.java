// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Vector;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.numbers.N3;
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
    public static int alternateController = 1;
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
    public static double positionMultiplier = 48.0 / 48.0;

    public static double driveCurrentLimit = 20.0;

    public static double translationMaxVelocity = 4.0;
    public static double rotationMaxVelocity = 2.0 * Math.PI;

    public static double translationKP = 8.0;
    public static double translationKD = 0.0;
    public static double rotationKP = 8.0;
    public static double rotationKD = 0.0;

    public static double frontLeftEncoderOffset = Math.toRadians(-89.82) - Math.PI / 2.0;
    public static double frontRightEncoderOffset = Math.toRadians(-180.703) + Math.PI / 2.0;
    public static double backLeftEncoderOffset = Math.toRadians(-80.069 + 17.5) - Math.PI / 2.0;
    public static double backRightEncoderOffset = Math.toRadians(-88.330) + Math.PI / 2.0;

    public static Vector<N3> stateStandardDeviation = VecBuilder.fill(0.01, 0.01, 0.02);
    public static Vector<N3> visionStandardDeviation = VecBuilder.fill(0.5, 0.5, 1.0);

    public static double adjustmentSpeed = 0.5;
  }

  public static class ElevatorConstants {
    public static double lowerHeightLimit = 0.375;
    public static double upperHeightLimit = 0.914;

    public static double ampPosition = 0.95;
    public static double shootPosition = 0.609;
    public static double pickupPosition = 0.550;

    public static double pP = 75;
    public static double pI = 0;
    public static double pD = 0.0;

    public static double kS = 0.5;

    public static double maxVoltage = 10;

    public static double tolerance = 0.03;

    public static double conversionFactor = 0.0128;

    public static int currentLimit = 20;
  }

  public static class TiltConstants {
    public static double lowerLimit = -Math.PI / 3.0;
    public static double upperLimit = Math.PI / 3.0;

    public static double ampAngle = Math.PI / 12.0;
    public static double pickupAngle = - Math.PI / 4;

    public static double hangingAngle = Math.PI / 4.0;
    public static double backlashAngle = 1.0 / 8.0;

    public static double pP = 20;
    public static double pI = 0;
    public static double pD = 0.00;
    
    public static double kS = 0.1;
    
    public static double maxVoltage = 8.0;

    public static double tolerance = 0.03;

    public static double conversionFactor = 0.1047 * 60.0 / 125.0;

    public static int currentLimit = 10;
  }

  public static class ClimberConstants {
    public static double extendVoltage = 8; /* Figure out optimal voltage */
    public static double retractVoltage = -8; /* Figure out optimal voltage */
    
    public static int currentLimit = 10; /* Check the limit */

    public static double positionConversionRate = 0.0022; /* 1/((Counts per revolution * gear ratio)/(pi*pulley diameter) * length of travel) */
  }

  public static class ShooterConstants {
    public static double idleVoltage = 4.0;
    public static double shootingVoltage = 10.0;

    public static double revtime = 2.0;

    public static int currentLimit = 20;
  }

  public static class IntakeConstants {
    public static double idleVoltage = 0.0;
    public static double intakeVoltage = 6.0;
    public static double exitVoltage = -10.0;

    public static double shootTime = 0.5;
    public static double ampTime = 1.0;

    public static int currentLimit = 20;
  }

  public static class AutoConstants {
    public static HolonomicPathFollowerConfig autoConfig = new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
      new PIDConstants(DrivetrainConstants.translationKP, 0.0, DrivetrainConstants.translationKD), // Translation PID constants
      new PIDConstants(DrivetrainConstants.rotationKP, 0.0, DrivetrainConstants.rotationKD), // Rotation PID constants
      DrivetrainConstants.AbsoluteMaxWheelVelocity, // Max module speed, in m/s
      DrivetrainConstants.wheelBaseLength * Math.sqrt(2.0) / 2.0, // Drive base radius in meters. Distance from robot center to furthest module.
      new ReplanningConfig() // Default path replanning config. See the API for the options here
    );
  }

  public static class BlueTeamWaypoints {
    public static Pose2d speaker = new Pose2d(1.40,5.60,new Rotation2d(-90.00));
    public static Pose2d amp = new Pose2d(1.85,7.25,new Rotation2d(Math.PI/2));
    public static Pose2d pickup = new Pose2d(15.15,1.60,new Rotation2d(30.00));
    public static Pose2d chainLeft = new Pose2d(4.00,5.50,new Rotation2d(30.00));
    public static Pose2d chainRight = new Pose2d(4.00,2.65,new Rotation2d(-30.00));
    public static Pose2d chainBack = new Pose2d(6.40,4.10,new Rotation2d(90.00));
  }

  public static class FieldConstants {
    public static double fieldLength = 16.54;

    public static Pose2d speakerPosition = new Pose2d(0.25, 5.55, new Rotation2d());
    public static double speakerHeight = 2.032;

    public static Pose2d speaker = new Pose2d(1.40,5.60,new Rotation2d(-90.00));
    public static Pose2d amp = new Pose2d(1.83, 7.69, new Rotation2d(Math.PI / 2.0));
    public static Pose2d pickup = new Pose2d(15.15,1.60,new Rotation2d(30.00));

    public static Pose2d chainLeft = new Pose2d(4.00,5.50,new Rotation2d(Math.PI * 2.0 / 3.0));
    public static Pose2d chainRight = new Pose2d(4.00,2.65,new Rotation2d(-Math.PI * 2.0 / 3.0));
    public static Pose2d chainBack = new Pose2d(6.40,4.10,new Rotation2d(0.0));

    public static Pose2d[] climbPositions = {chainLeft, chainRight, chainBack};


    public static Pose2d tranformPoseBluePose(Pose2d bluePose) {
      Pose2d redPose = new Pose2d(
        fieldLength - bluePose.getX(),
        bluePose.getY(),
        new Rotation2d(Math.PI - bluePose.getRotation().getRadians())
      );

      Optional<Alliance> alliance = DriverStation.getAlliance();

      if (alliance.isEmpty()) {
        return bluePose;
      } else {
        switch (alliance.get()) {
          case Blue:
            return bluePose;
          case Red:
            return redPose;
          default:
            return bluePose;
        }
      }
    }

    public static Pose2d[] transformBluePoses(Pose2d[] bluePoses) {
      Pose2d[] tranformedPoses = bluePoses.clone();

      for (int i = 0; i < bluePoses.length; i++) {
        tranformedPoses[i] = tranformPoseBluePose(bluePoses[i]);
      }

      return tranformedPoses;
    }
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
