/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class LiftDrive extends TimedCommand {

private double power;
  public LiftDrive(double timeout, double power) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.lift);
    this.power = power;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.lift.liftDrive(power);
  } 
  

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.lift.liftDrive(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
