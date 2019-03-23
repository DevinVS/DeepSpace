/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.TreeMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.vision.Block;

public class Align extends InstantCommand {
  public Align() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  private static double kP = 0.005;
  private static Block target1;
  private static Block target2;
  private static int pixyOffset = 79; // was at 75
  private static double error;

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double blocksPos = getBlocksPos();
    error = blocksPos - pixyOffset;
    double rOut = limit(kP * -error);
    double lOut = limit(kP * error);
    Robot.drivetrain.set(ControlMode.PercentOutput, lOut, rOut);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println(error);
    return error < 2;
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

  private static double getBlocksPos(){
    getBlocks();
    if(target1 == null || target2 == null){
      return pixyOffset;
    }else{
      return (target1.x + target2.x)/2;
    }
  }

  private static void getBlocks(){
    Block[] blocks = Robot.pixy2SpiJNI.blocksBuffer.poll();
    //Hashtable<Integer, Block> centeredBlocks = new Hashtable<Integer, Block>();
    TreeMap<Integer, Block> centeredBlocks = new TreeMap<Integer, Block>();
    if(blocks !=null && blocks.length>0){
    for(Block b: blocks){

      if(b.sig == 2){ 
        if(centeredBlocks.size()>=2 && b.x-pixyOffset < centeredBlocks.lastKey()){
          centeredBlocks.pollLastEntry();
          centeredBlocks.put(b.x-pixyOffset, b);
        }else{
          centeredBlocks.put(b.x-pixyOffset, b);
        }
      }
    }

    if(centeredBlocks.size() ==2){
      target1 = centeredBlocks.pollFirstEntry().getValue();
      target2 = centeredBlocks.pollFirstEntry().getValue();
      System.out.println("found targets");
    }
  }
    
  }
  private static double limit(double num){
    if(num>1){
      return 1;
    }else if(num<-1){
      return -1;
    }else{
      return num;
    }
  }
}
