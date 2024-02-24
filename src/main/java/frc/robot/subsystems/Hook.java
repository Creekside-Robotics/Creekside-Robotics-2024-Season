package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class Hook extends SubsystemBase {
    private CANSparkMax motor;
    private RelativeEncoder encoder;
    
    public Hook(int motorID) {
        motor = new CANSparkMax(motorID, MotorType.kBrushless);
        motor.setInverted(false);
        motor.setSmartCurrentLimit(ClimberConstants.currentLimit);

        encoder = motor.getEncoder();
        encoder.setPositionConversionFactor(ClimberConstants.positionConversionRate);
        encoder.setVelocityConversionFactor(ClimberConstants.positionConversionRate/(60.0));
        encoder.setPosition(1.0);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public double getPosition() {
        /** 
         * 1 - extended
         * 0 - retracted 
        **/

        double position = encoder.getPosition();
        if (position >= 1) return 1;
        else if (position <= 0) return 0;
        else return position;
    }

    public double getVelocity() {
        return this.encoder.getVelocity();
    }
}