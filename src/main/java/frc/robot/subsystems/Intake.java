// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.PhoenixUtil;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  // wheels motors, make the config
  SparkMax intakeWheels1 = new SparkMax(Constants.intakeWheels1, MotorType.kBrushless);
  SparkMax intakeWheels2 = new SparkMax(Constants.intakeWheels2, MotorType.kBrushless);
  private SparkMaxConfig SPARKconfig = new SparkMaxConfig();
  private SparkMaxConfig globalConfig = new SparkMaxConfig();

  // pivot motor, make the config
  TalonFX pivotMotor = new TalonFX(Constants.pivotMotor);
  private TalonFXConfiguration config;

  public Intake() {
    // init configs
    config = new TalonFXConfiguration();
    pivotMotor.setNeutralMode(NeutralModeValue.Brake);
    
    // music is fun
    config.Audio.AllowMusicDurDisable = true;

    // enable limit switches
    config.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
    config.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    // give pid values (closed loop control)
    config.Slot0.kP = 0;
    config.Slot0.kI = 0;
    config.Slot0.kP = 0;
    // set voltage limits
    config.Voltage.withPeakForwardVoltage(.8)
      .withPeakReverseVoltage(-.8);
    
    
    PhoenixUtil.tryUntilOk(5, () -> pivotMotor.getConfigurator().apply(config));


    globalConfig
    .smartCurrentLimit(30)
    .idleMode(IdleMode.kBrake);
    
   SPARKconfig
   .follow(15)
   .apply(globalConfig);
    
    intakeWheels1.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    intakeWheels2.configure(SPARKconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }
// i think this is how it works...
  public void setPivotPosition(double pos){
    var request = new PositionDutyCycle(pos);
      pivotMotor.setControl(request);
  }

  public void intakeyTake(double speed){
    intakeWheels1.set(speed);

  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
