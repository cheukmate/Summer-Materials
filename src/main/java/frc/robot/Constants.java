// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class Constants {
 /* define your constants here :)
  * these are all of the motors that will be used for the subsystems
  arm
  intake
  elevator
  shooter
  drivetrain
  pivot
  */
        // drive motors
    public static final int leftLeader = 1;
    public static final int rightLeader = 3;
    public static final int leftFollower = 2;
    public static final int rightFollower = 4;
    
        //elevator motors
    public static final int elevatorMotor1 = 9;
    public static final int elevatorMotor2 = 10;
        //arm motors
    public static final int windmillMotor = 11;
    public static final int pivotMotor = 12;
    public static final int pivotMotor2 = 13;
    public static final int outtakeMotor = 14;
        //intake motors
    public static final int intakeWheels1 = 15;
    public static final int intakeWheels2 = 16;
    public static final int intakePivot1 =  17;
    public static final int intakePivot2 = 18;
        //shooter 
    public static final int flywheel = 19;

        // pivot motors
    public static final int pivot = 20;
    public static final int wheels = 21;
    
        // controller ports
    public static final int DRIVERPORT = 0;
    public static final int OPERATORPORT= 1;
    public static final double MAX_SPEED = 0;

}
