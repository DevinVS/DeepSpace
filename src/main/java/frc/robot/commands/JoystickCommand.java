/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

//import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.OI;
import frc.robot.subsystems.DriveSubsystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class JoystickCommand extends Command {
  private static final float INPUT_DRIVE_RATIO = 1f;

  public JoystickCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rightStickValueY = Robot.m_oi.right.getY() *INPUT_DRIVE_RATIO;
    double rightStickValueX = -Robot.m_oi.right.getX() *INPUT_DRIVE_RATIO;
    
    // If(Robot.m_oi.right.getRawButtonPressed(1) = 1){

    // }

    /*if (Math.abs(leftStickValue) < .1){
        leftStickValue = 0;
    }
    if (Math.abs(rightStickValue) < .1){
        rightStickValue = 0;
    }*/

   Robot.driveSubsystem.tankDrive.arcadeDrive(rightStickValueX, rightStickValueY, true);
    System.out.println(DriveSubsystem.leftMasterTalon.getActiveTrajectoryVelocity());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
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
    Robot.driveSubsystem.Stop();
  }
}
