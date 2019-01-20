/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class PIDTurn extends PIDCommand {

  private PIDController pidController;

  public PIDTurn(double degrees) {
    // Use requires() here to declare subsystem dependencies
    super(.05,0,0);
    requires(Robot.driveSubsystem);

    pidController = getPIDController();
    pidController.setInputRange(-180,180);
    pidController.setAbsoluteTolerance(1);
    pidController.setSetpoint(degrees);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    pidController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pidController.onTarget();
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

  @Override
  protected double returnPIDInput() {
    return 0;
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.driveSubsystem.leftMasterTalon.set(ControlMode.PercentOutput, output);
    Robot.driveSubsystem.rightMasterTalon.set(ControlMode.PercentOutput, -output);
  }
}
