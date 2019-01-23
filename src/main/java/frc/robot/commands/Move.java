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


public class Move extends Command {

  private static final double pulsesPerFoot = 1;
  private WPI_TalonSRX leftMasterTalon;
  private WPI_TalonSRX rightMasterTalon;
  private double targetVel;



  public Move(int distance) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveSubsystem);
    leftMasterTalon = Robot.driveSubsystem.leftMasterTalon;
    rightMasterTalon = Robot.driveSubsystem.rightMasterTalon;

    this.targetVel = distance;
  }
  
    

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveSubsystem.zero();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    

    //leftTargetDistance *= pulsesPerFoot;
    //rightTargetDistance *= pulsesPerFoot;
    //leftMasterTalon.set(ControlMode.Velocity, targetVel);
    rightMasterTalon.set(ControlMode.Velocity, targetVel);
    Robot.driveSubsystem.tankDrive.feed();
    
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double error = rightMasterTalon.getSelectedSensorVelocity() - targetVel;
    System.out.println(error);
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
