/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.LiftSubsystem;

public class UpCommand extends Command {

  private int distance;
  public UpCommand(int distance) {
    // Use requires() here to declare subsystem dependencies
     requires(Robot.liftSubsystem);
     this.distance = distance;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.liftSubsystem.moveUpComand();

    Robot.robotMap.liftPid.setReference(distance, ControlType.kPosition);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double error = Math.abs((Robot.robotMap.liftEncoder1.getPosition() - distance) / distance);
    return error <= 0.05;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.liftSubsystem.stopLift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.liftSubsystem.stopLift();
  }
}
