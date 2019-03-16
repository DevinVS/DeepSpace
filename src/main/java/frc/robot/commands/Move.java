/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;


public class Move extends TimedCommand {
  private double power;
  public Move(double timeout, double power) {
    super(timeout);
    requires(Robot.drivetrain);
    Robot.drivetrain.zero();
    this.power = power;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.set(ControlMode.PercentOutput, power, power);
    System.out.println("Executing Move Command");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    

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
