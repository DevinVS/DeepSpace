/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.vision.Block;

public class AlignDistance extends Command {
  public AlignDistance() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
  }
  static double kP = .01;
  static int pixyOffset = 0;
  static double lDistance, rDistance, error, lOut, rOut;

  static Block line, target1, target2;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // This acts as a PID loop using only the 'P' constant, trying to reduce the distances to 0
  @Override
  protected void execute() {
    alignParallel();
    center();
    //alignParallel();
  }

  @Override
  protected boolean isFinished(){
    return (error < 10) || line.equals(null) || target1.equals(null) || target2.equals(null);
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

  private static void getDistances(){
    //Change this
    lDistance = 100;
    rDistance = 80;
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

  private static void getBlocks(){
    Block[] blocks = Robot.pixy2SpiJNI.blocksBuffer.poll();
    //Hashtable<Integer, Block> centeredBlocks = new Hashtable<Integer, Block>();
    TreeMap<Integer, Block> centeredBlocks = new TreeMap<Integer, Block>();

    for(Block b: blocks){

      if(b.sig == 2){ 
        if(centeredBlocks.size()>=3 && b.x-pixyOffset < centeredBlocks.lastKey()){
          centeredBlocks.pollLastEntry();
          centeredBlocks.put(b.x-pixyOffset, b);
        }else{
          centeredBlocks.put(b.x-pixyOffset, b);
        }
      }
    }
    
    if(centeredBlocks.size() == 3){
      int lowestY = 99999;
      int lowestKey = 0;
      for(int i: centeredBlocks.keySet()){
        if(centeredBlocks.get(i).y<lowestY){
          lowestY = centeredBlocks.get(i).y;
          lowestKey = i;
        }
      }
      line = centeredBlocks.get(lowestKey);
      centeredBlocks.remove(lowestKey);
      target1 = centeredBlocks.pollFirstEntry().getValue();
      target2 = centeredBlocks.pollFirstEntry().getValue();
    }
  }

  private static void alignParallel(){
    getDistances();
    error = lDistance - rDistance;

    rOut = limit(kP*-error);
    lOut = limit(kP*error);

    Robot.drivetrain.set(ControlMode.PercentOutput, lOut, rOut);
  }

  private static void center(){
    getBlocks();
    getDistances();

    double angle = getAngle();
    Robot.drivetrain.turn(angle);
    error = lDistance - rDistance;
    while(Math.abs(error) > 10){
      error = lDistance - rDistance;
      Robot.drivetrain.set(ControlMode.PercentOutput, 1., 1.);
    }

  }

  private static double getAngle(){
    return 20;
  }

}
