// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.ejml.data.ElementLocation;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.tShirtCannon;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
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
  private final SwerveSubsystem m_swerve = new SwerveSubsystem();

// make your controllers exist
   private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.DRIVERPORT);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(Constants.OPERATORPORT);

    //configure bindings
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    
   // set your default command for driving
    m_drive.setDefaultCommand(new InstantCommand(()-> m_drive.driveCommand(-m_driverController.getLeftY(), -m_operatorController.getRightX())));
    
  
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
