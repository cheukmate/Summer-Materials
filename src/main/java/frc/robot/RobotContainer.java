// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;



import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.IntakePivotCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BASICCONCEPTDEMOS.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.BASICCONCEPTDEMOS.LEDs;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.BASICCONCEPTDEMOS.Shooter;
import frc.robot.subsystems.BASICCONCEPTDEMOS.tShirtCannon;
import frc.robot.subsystems.YAGSL.SwerveSubsystem;
import swervelib.SwerveInputStream;
import frc.robot.subsystems.Elevator;

public class RobotContainer {
  // Define your subsystems and commands.

  private final Drivetrain m_drive = new Drivetrain();
  private final Arm m_arm = new Arm();
  private final Intake m_intake = new Intake();
  private final Elevator m_elevator = new Elevator();
  private final Pivot m_pivot = new Pivot();
  private final Shooter m_shooter = new Shooter();
  private final tShirtCannon m_cannon = new tShirtCannon();
  private final LEDs m_leds = new LEDs();
  private final IntakePivotCommand intakePivotCommand = new IntakePivotCommand(m_intake);
    private final SwerveSubsystem m_swerve  = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                                            "neo"));

// make your controllers exist
   private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.DRIVERPORT);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(Constants.OPERATORPORT);

    //configure bindings

  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(m_swerve.getSwerveDrive(),
                                                                () -> m_driverController.getLeftY() * -1,
                                                                () -> m_driverController.getLeftX() * -1)
                                                            .withControllerRotationAxis(m_driverController::getRightX)
                                                          
                                                            .scaleTranslation(0.8)
                                                            .allianceRelativeControl(true);

  /**
   * Clone's the angular velocity input stream and converts it to a fieldRelative input stream.
   */
  SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(m_driverController::getRightX,
                                                                                             m_driverController::getRightY)
                                                           .headingWhile(true);

 
  public RobotContainer() {
  
    configureBindings();


  }

  private void configureBindings() {
    //drive commands
    Command driveFieldOrientedDirectAngle      = m_swerve.driveFieldOriented(driveDirectAngle);
    Command driveFieldOrientedAnglularVelocity = m_swerve.driveFieldOriented(driveAngularVelocity);
    Command driveSetpointGen = m_swerve.driveWithSetpointGeneratorFieldRelative(
        driveDirectAngle);
    
   // set your default command for driving
   // 1 is tank, 2 is swerve.
    m_drive.setDefaultCommand(new InstantCommand(()-> m_drive.driveCommand(-m_driverController.getLeftY(), -m_operatorController.getRightX()))); // Tank chassis for the sake of example
    //m_swerve.setDefaultCommand(driveFieldOrientedAnglularVelocity); for if you want to use swerve
    
  }

  public Command getAutonomousCommand() {
    return m_swerve.getAutonomousCommand("Score Autonomous"); //whatever path you create, make sure to redeploy with the new name and every time you change it.
  }
}
