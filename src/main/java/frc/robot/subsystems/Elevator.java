// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.SparkUtil;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */

  boolean atHome = false;
  public boolean homing = false;
  private SparkMax motor = new SparkMax(Constants.elevatorMotor1, MotorType.kBrushless);
  private SparkMaxConfig config = new SparkMaxConfig();


  public Elevator() {
    config
    .inverted(false)
    .idleMode(IdleMode.kBrake);
    config.alternateEncoder.countsPerRevolution(8192);
    config
      .closedLoop
      .feedbackSensor(FeedbackSensor.kAlternateOrExternalEncoder)
      .pid(0, 0, 0)
      .maxOutput(0.7)
      .minOutput(-.7);

      
            motor.configure(
                config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

 
  public void setPos(double position){
      // calc the position the motor should go to, go to position
      motor
        .getClosedLoopController()
        .setReference(findPosInInches(position), ControlType.kPosition);
    
  }

  public void setPosInches(double position){
    motor
    .getClosedLoopController()
    .setReference(findPosInInches(position), ControlType.kPosition);
    }
  
  public void setVoltage(double voltage){
    motor.getClosedLoopController().setReference(voltage, ControlType.kVoltage);
  
  }

  public void setToZero(){
    motor.getEncoder().setPosition(0);
  }

  public double findPosInInches(double pos){
return Constants.homeOffset + pos * Constants.gearRatio * Constants.pulleyCircumference;

  }

  public double findPosFromInches(double posInInches){
    return posInInches / Constants.gearRatio / Constants.pulleyCircumference - Constants.homeOffset;
  }

  public void setPower(double power){
    motor.set(power);
  }

  public void setPID(double kP, double kI, double kD){
config.closedLoop.pid(kP, kI, kD);


      motor.configure(
          config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void setPositionClosedLoopWithFF(double position, double arbFF){

    motor
    .getClosedLoopController()
    .setReference(position, ControlType.kPosition, ClosedLoopSlot.kSlot0, arbFF);
  }

  public boolean checkLimitSwitch(){

    return motor.getReverseLimitSwitch().isPressed();

  }

  public double getPos(){

    return motor.getEncoder().getPosition();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
