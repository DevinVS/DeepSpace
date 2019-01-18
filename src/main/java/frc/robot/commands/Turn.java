/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Robot;


public class Turn extends Command {

  private static final double pulsesPerDegree = 1;
  private WPI_TalonSRX leftMasterTalon;
  private WPI_TalonSRX rightMasterTalon;
  private double targetDistance;



  public Turn(double degrees) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveSubsystem);
    this.targetDistance = pulsesPerDegree * degrees;
    leftMasterTalon = Robot.driveSubsystem.leftMasterTalon;
    rightMasterTalon = Robot.driveSubsystem.rightMasterTalon;
  }
  
    

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    leftMasterTalon.set(ControlMode.MotionMagic, targetDistance);
    rightMasterTalon.set(ControlMode.MotionMagic, targetDistance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (leftMasterTalon.isMotionProfileFinished() && rightMasterTalon.isMotionProfileFinished());
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
