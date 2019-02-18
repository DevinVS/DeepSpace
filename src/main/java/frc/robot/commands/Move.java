/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Constants;

public class Move extends Command {

  private int targetDistance;

  public Move(int distance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    this.targetDistance = distance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.zero();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.drivetrain.move("pos", targetDistance);
    //Robot.drivetrain.move("pow", .5);
    //Robot.drivetrain.move("vel", 60000);

    Robot.drivetrain.setPIDSlot(0);
    Robot.drivetrain.set(ControlMode.MotionMagic, targetDistance, targetDistance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Constants.kAllowableClosedLoopError > Math.abs(targetDistance - Robot.drivetrain.getPosition()[0]);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.set(ControlMode.PercentOutput, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
