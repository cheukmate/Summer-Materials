// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ElevatorToSetpoint extends Command {
  /** Creates a new ElevatorToSetpoint. */
  private Elevator elevator;
  private double userGoal;
  private ElevatorFeedforward feedforward;
  private TrapezoidProfile profile;
  private TrapezoidProfile.State goal = new TrapezoidProfile.State();
  private TrapezoidProfile.State setpoint = new TrapezoidProfile.State();
  private boolean homing;

  public ElevatorToSetpoint(Elevator elevator, double userGoal, boolean homing) {
   
    this.elevator = elevator;
    this.userGoal = userGoal;
    this.homing = homing;
    feedforward = 
      new ElevatorFeedforward(0, 0, 0, 0);

    addRequirements(elevator);
  }

  public ElevatorToSetpoint(Elevator elevator, double userGoal) {
   
    this.elevator = elevator;
    this.userGoal = userGoal;
    this.homing = false;
    feedforward = 
      new ElevatorFeedforward(0, 0, 0, 0);

    addRequirements(elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    profile = new TrapezoidProfile(new TrapezoidProfile.Constraints(5, 0.5));
    goal = new TrapezoidProfile.State(userGoal, 0);
    setpoint = new TrapezoidProfile.State(elevator.getPos(), 0);
    elevator.homing = this.homing;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // retrieve profiled setpoint for the next timestep.
    // moves toward the goal while obeying constraints :)

    setpoint = profile.calculate(.2, setpoint, goal);

    elevator.setPositionClosedLoopWithFF(setpoint.position, feedforward.calculate(setpoint.velocity));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
