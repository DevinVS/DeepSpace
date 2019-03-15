/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;



import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Raise extends Command {
  private double targetPos;
  public Raise(double targetPos) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.targetPos = targetPos;
    requires(Robot.lift);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.compressor.stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.lift.setLift((-26)*targetPos);
    Robot.lift.setElevator((-36)*targetPos);

    

    
    // double power = Math.abs(Robot.m_oi.stick.getY())>.05? Robot.m_oi.stick.getY(): 0;
    // Robot.lift.setDrive(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
