/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class TestPID extends Command {

  double targetPos = 1;
  public TestPID() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.zero();
    Robot.drivetrain.setPIDSlot(1);
    //Robot.drivetrain.getConstants();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("running pid test");
    Robot.drivetrain.set(ControlMode.PercentOutput, targetPos, targetPos);
    // Robot.drivetrain.set(ControlMode.Velocity, Constants.kMaxVelocity, Constants.kMaxVelocity);
    SmartDashboard.putNumber("Lerror", Constants.kMaxVelocity - Math.abs(Robot.drivetrain.getVelocity()[0]));
    SmartDashboard.putNumber("Rerror", Constants.kMaxVelocity - Math.abs(Robot.drivetrain.getVelocity()[1]));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    int[] vels = Robot.drivetrain.getVelocity();
    SmartDashboard.putNumber("Left Velocity", vels[0]);
    SmartDashboard.putNumber("Right Velocity", vels[1]);
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
