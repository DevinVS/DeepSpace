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
  private double eTargetPos;
  private double lTargetPos;

  public Raise(double eTargetPos, double lTargetPos ) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.eTargetPos = eTargetPos;
    this.lTargetPos = lTargetPos;
    requires(Robot.lift);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.compressor.stop();
    // System.out.println(String.format("Executing Raise %2f", eTargetPos));
    // Robot.lift.setLift((-26)*targetPos);
    // Robot.lift.setElevator((-36)*targetPos);

    Robot.lift.setLift(lTargetPos);
    Robot.lift.setElevator(eTargetPos);


  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // lift -26    
    // double power = Math.abs(Robot.m_oi.stick.getY())>.05? Robot.m_oi.stick.getY(): 0;
    // Robot.lift.setDrive(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // double ePosition = Robot.lift.getElevatorPosition();
    // double lPosition = Robot.lift.getElevatorPosition();

    // if((ePosition > (eTargetPos -1)) & (ePosition < (eTargetPos +1))){

    // }


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
