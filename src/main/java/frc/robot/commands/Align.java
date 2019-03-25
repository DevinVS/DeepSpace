/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.TreeMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;
import frc.robot.vision.Block;

public class Align extends TimedCommand {
  
  // The emergency timeout is so that we don't lock the driver out for too long while aligning.
  public Align(double emergencyTimeout) {
    super(emergencyTimeout);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("INFO: Align initialize");
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
    System.out.println(String.format("INFO: Align error: %.01f", error));
    if (isTimedOut()) {
      System.out.println("INFO: Align timed out");
    }
    return Math.abs(error) < 2 || isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("INFO: Align end");
    Robot.drivetrain.set(ControlMode.PercentOutput, 0.0, 0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  private static double getBlocksPos() {
    getBlocks();
    if (target1 == null || target2 == null) {
      return pixyOffset;
    } else {
      return (target1.x + target2.x) * 0.5;
    }
  }

  private static void getBlocks() {
    Block[] blocks = Robot.pixy2SpiJNI.blocksBuffer.poll();
    //Hashtable<Integer, Block> centeredBlocks = new Hashtable<Integer, Block>();
    TreeMap<Integer, Block> centeredBlocks = new TreeMap<Integer, Block>();
    if (blocks !=null && blocks.length>0) {
      for (Block b: blocks){

        if (b.sig == 2) {
          int pixyOffsetError = b.x-pixyOffset;
          if (centeredBlocks.size()>=2 && Math.abs(pixyOffsetError) < Math.abs(centeredBlocks.lastKey())) {
            centeredBlocks.pollLastEntry();
            centeredBlocks.put(pixyOffsetError, b);
          } else {
            centeredBlocks.put(pixyOffsetError, b);
          }
        }
      }

      if (centeredBlocks.size() == 2) {
        target1 = centeredBlocks.pollFirstEntry().getValue();
        target2 = centeredBlocks.pollFirstEntry().getValue();
        System.out.println(String.format("INFO: Align found targets: x1: %03d x2: %03d", target1.x, target2.x));
      }
  }
    
  }
  private static double limit(double num){
    if (num>1) {
      return 1;
    } else if (num<-1) {
      return -1;
    } else {
      return num;
    }
  }
}
