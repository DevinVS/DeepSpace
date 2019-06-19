/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TestTheLift extends Command {
  public TestTheLift() {
    requires(Robot.lift);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("INFO: initialize TestTheLift");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double joyX = OI.stick.getX();
    Robot.lift.setLiftSpeed(joyX);
    System.out.println("Executing TestTheLift "+joyX);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("INFO: end TestTheLift");
    Robot.lift.setLiftSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("INFO: interrupted TestTheLift");
    Robot.lift.setLiftSpeed(0.0);
  }
}
