// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.BASICCONCEPTDEMOS;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// OBJECTIVE : TEACH STUDENT HOW TO ASSIGN BASIC MOTOR FUNCTIONALITY LIKE IDS, CONFIGURATIONS TO MAKE A BASIC DIFFERENTIAL DRIVE SUBSYSTEM
public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  // define your motors and give them values :)
  /*
   * The motors are called leaders and followers instead of their positions on the chassis for ease
   * 
   * Front Left = Left Leader
   * Front Right = Right Leader
   * 
   * Back Left = Left Follower
   * Back Right = Right Follower
   * 
   */

  // These are private so they cannot be accessed by any other subsystems, static so they can be called throughout the subsystem, and final so they cannot be changed.
  // Assign them their IDs (set in the orange place) so that they can function. Motor type depends on what kind of motor you're using. Ask mechanical.

  private static final SparkMax leftLeader = new SparkMax(Constants.leftLeader, MotorType.kBrushed);
  private static final SparkMax leftFollower = new SparkMax(Constants.leftFollower, MotorType.kBrushed);
  private static final SparkMax rightLeader = new SparkMax(Constants.rightLeader, MotorType.kBrushed);
  private static final SparkMax rightFollower = new SparkMax(Constants.rightFollower, MotorType.kBrushed); 

  //create the differential drive object
  public static DifferentialDrive diffDrive = new DifferentialDrive(leftLeader, rightLeader);

  //create your configuration objects 
  private SparkMaxConfig globalConfig;
  private SparkMaxConfig RLconfig;
  private SparkMaxConfig LFconfig;
  private SparkMaxConfig RFconfig;

  public Drivetrain() {

    // give them life

    globalConfig = new SparkMaxConfig();
    RLconfig = new SparkMaxConfig();
    LFconfig = new SparkMaxConfig();
    RFconfig = new SparkMaxConfig();

    //global config which also is just the left leader
    globalConfig
     .smartCurrentLimit(50)
     .idleMode(IdleMode.kBrake);

     // Right leader configuration
    RLconfig
    .apply(globalConfig)
    .inverted(true);
    // Left follower configuration
    LFconfig
    .apply(globalConfig)
    .follow(leftLeader);

    // Right follower configuration
    RFconfig
    .apply(globalConfig)
    .follow(rightLeader);


    // actually configure them!

    leftLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftFollower.configure(LFconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightLeader.configure(RLconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightFollower.configure(RFconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // Awesome, you've configured all of your motors on your drivetrain! Now, make the actual command that makes it drive.
        }
      
        
      // Create your Drive Command. Use the parameters speed and rotation when you call it in RobotContainer.java. 

      public void driveCommand(double xSpeed, double Rotation){
        diffDrive.arcadeDrive(xSpeed, Rotation);
      }
       
      
      
      
        @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
