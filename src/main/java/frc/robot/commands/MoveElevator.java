/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class MoveElevator extends Command {
  double targetPos;
  public MoveElevator(double targetPos) {
    requires(Robot.lift);
    this.targetPos = targetPos;



  }



  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    targetPos = (targetPos > 150)? 150: targetPos;
    Robot.lift.setElevator(targetPos);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println("Executing MoveElevator Command");

    // 27 = lowest ball
    //56 = middle ball
    // 85 = tallest ball

    // 10 = lowest hatch
    // 40 = middle hatch
    // 70 = tallest hatch

    
    // System.out.println("Elevator Posotion " + Robot.lift.getElevatorPosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.lift.getElevatorPosition() - targetPos) < 1;
    //had to chaange is finished to 1 from .1 
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
