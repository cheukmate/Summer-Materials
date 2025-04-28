// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

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
    
        //shooter 
    public static final int flywheel = 19;

        // pivot motors
    public static final int pivot = 20;
    public static final int wheels = 21;

        // limit switch port
    public static final int limitSwitchPort = 27;
    
        // controller ports
    public static final int DRIVERPORT = 0;
    public static final int OPERATORPORT= 1;
        // CANdle port
    public static final int CANdle = 45;

    public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
    public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
    public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
    public static final double MAX_SPEED  = Units.feetToMeters(14.5);

    public static final double DEADBAND        = 0.1;
    public static final double LEFT_Y_DEADBAND = 0.1;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final double TURN_CONSTANT    = 6;
    public static double homeOffset = 0;
    public static double gearRatio = 0;
    public static double pulleyCircumference = 0;

    
}


