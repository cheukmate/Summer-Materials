// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BASICCONCEPTDEMOS;



import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.PhoenixUtil;

//subsystem 
public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  // constructor

  // talonfx configuration example
  TalonFX flywheel = new TalonFX(Constants.flywheel);
  private TalonFXConfiguration config;
  public Shooter() {

  flywheel.getConfigurator().apply(config);

  config = new TalonFXConfiguration();

  flywheel.setNeutralMode(NeutralModeValue.Brake);
  config.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
  config.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
  config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = 0.05;

    config.Slot0 =
        new Slot0Configs()
            .withKP(0)
            .withKI(0)
            .withKD(0);

  PhoenixUtil.tryUntilOk(5, () -> flywheel.getConfigurator().apply(config));


     var velocity = flywheel.getVelocity();
     var appliedVoltage = flywheel.getMotorVoltage();
     var supplyCurrent = flywheel.getSupplyCurrent();
     var torqueCurrent = flywheel.getTorqueCurrent();
     var tempCelsius = flywheel.getDeviceTemp();

     PhoenixUtil.tryUntilOk(
        5,
        () ->
            BaseStatusSignal.setUpdateFrequencyForAll(
                50.0,
        
                velocity,
                appliedVoltage,
                supplyCurrent,
                torqueCurrent,
                tempCelsius));
 
                var slot0Configs = new Slot0Configs();
                slot0Configs.kS = 0.31;
                slot0Configs.kV = 0.12;
                slot0Configs.kP = 0;
                slot0Configs.kI = 0;
                slot0Configs.kD = 0;
            
                flywheel.getConfigurator().apply(slot0Configs);
              }
   
  

  // function / cmd
  public void SetVoltage(double voltage){
    flywheel.setVoltage(voltage);
  }

  public void SetPower(double power){
    flywheel.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
