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
import frc.robot.vision.Block;
import frc.robot.Constants;

public class FollowBall extends Command {
  public FollowBall() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double pos;
    double rightPower =0, leftPower = 0;
    
    if((pos = getClosestBallPos())<Constants.screenWidth){
      if(pos<=0){
        leftPower= 1+(pos/(Constants.screenWidth/2));
        rightPower=1;
      }else{
        leftPower=1;
        rightPower= 1-(pos/(Constants.screenWidth/2));
      }
    }
    System.out.println(rightPower + " " + leftPower);
    double  multiplier = -.25;
    Robot.drivetrain.leftMasterTalon.set(ControlMode.Velocity, Constants.kMaxVelocity * leftPower * multiplier);
    Robot.drivetrain.rightMasterTalon.set(ControlMode.Velocity, Constants.kMaxVelocity * rightPower * multiplier);

    try{
      Thread.sleep(100);
    }catch (Exception e){

    }
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
  }

  private double getClosestBallPos(){
    int closestPos = 99999;
    Block[] blocks = Robot.pixy2SpiJNI.blocksBuffer.poll();
    if((blocks!= null) && blocks.length > 0){
      for(Block b: blocks){
        if(b.sig ==1 && (b.x - Constants.screenWidth) < closestPos){
          closestPos = b.x - Constants.screenWidth/2;
          System.out.println(closestPos);
        }
      }
    }

    return closestPos;
  }
}
