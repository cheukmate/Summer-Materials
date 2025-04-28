// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BASICCONCEPTDEMOS;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.ColorFlowAnimation;
import com.ctre.phoenix.led.LarsonAnimation;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix.led.TwinkleAnimation;
import com.ctre.phoenix.led.CANdle.LEDStripType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LEDs extends SubsystemBase {
  /** Creates a new LEDs. */
  // demonstrate LED lights
  private CANdle candle;
  private int totalLEDS = 129; // some number
  private boolean animateDir = false;
  public enum AnimationTypes{
    BLUE,
    RED,
    WHITE,
    CORAL,
    Rainbow,
    L1,
    L2,
    L3,
    L4
  }
  private AnimationTypes prevAnimation = AnimationTypes.Rainbow;

  public LEDs() {
    candle = new CANdle(Constants.CANdle);
    CANdleConfiguration config = new CANdleConfiguration();
    config.brightnessScalar = 0.5;
    config.stripType = LEDStripType.RGB;
    candle.configAllSettings(config);
  }

  public void setPattern(AnimationTypes animation){
    if (prevAnimation != animation){
      prevAnimation = animation;
      Animation toAnimate = null;
      switch (animation){

      case L4:
        toAnimate = new LarsonAnimation(255, 30, 0, 0, 0.75, 68, LarsonAnimation.BounceMode.Back, 9, 8);
        break;
       case L3: 
        toAnimate = new ColorFlowAnimation(255, 0, 0, 0, 0.5, 68, ColorFlowAnimation.Direction.Forward, 0);
        break;
      case L2: 
        toAnimate = new TwinkleAnimation(255, 105, 180, 0, 0.75, 68, TwinkleAnimation.TwinklePercent.Percent88, 0 );
        break;
      case L1:
        toAnimate = new TwinkleAnimation(255, 105, 180, 0, 0.75, 68, TwinkleAnimation.TwinklePercent.Percent88, 0 );
        break;
      case BLUE:
        toAnimate = new TwinkleAnimation(0, 0, 255, 0, 0.75, 68, TwinkleAnimation.TwinklePercent.Percent88, 0 );
        break;
      case RED:
        toAnimate = new TwinkleAnimation(255, 0, 0, 0, 0.75, 68, TwinkleAnimation.TwinklePercent.Percent88, 0 );
        break;
      case WHITE:
        toAnimate = new TwinkleAnimation(255, 255, 255, 0, 0.75, 68, TwinkleAnimation.TwinklePercent.Percent88, 0 );
          break;
      case CORAL:
        toAnimate = new ColorFlowAnimation(255, 255, 255, 0, 0.5, 68, ColorFlowAnimation.Direction.Forward, 0);
        break;
      case Rainbow:
        toAnimate = new RainbowAnimation(1,0.7,totalLEDS);
          break;
      default:
        toAnimate = new RainbowAnimation(1, .8, totalLEDS);
        }
    }
  }

  public void setForAllianceDefault(){
    var alliance = DriverStation.getAlliance();
    
    if (alliance.isPresent() && alliance.get() == DriverStation.Alliance.Red){
      setPattern(AnimationTypes.RED);
    }
    else if (alliance.isPresent() && alliance.get() == DriverStation.Alliance.Blue){
      setPattern(AnimationTypes.BLUE);
    }
    else {
      setPattern(AnimationTypes.Rainbow);
    }
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
