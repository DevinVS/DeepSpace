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
  private static Block leftTarget1;
  private static Block leftTarget2;
  private static Block rightTarget1;
  private static Block rightTarget2;
  private static int leftPixyOffset = 79; // was at 75
  private static int rightPixyOffset = 79; // was at 75
  private static double leftError;
  private static double rightError;

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double[] blocksPos = getBlocksPos();
    leftError = blocksPos[0] - leftPixyOffset;
    rightError = blocksPos[1] - rightPixyOffset;
    // TODO: How to triangulate and move motors?
    // double rOut = limit(kP * -leftError);
    // double lOut = limit(kP * leftError);
    // Robot.drivetrain.set(ControlMode.PercentOutput, lOut, rOut);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println(String.format("INFO: left error: %d, right error: %d", leftError, rightError));
    // return error < 2;
    // TODO: How to determine if this Align is now finished?
    return true;
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


//    private static void getBlocks() {
//     Block[] blocks = Robot.pixy2SpiJNI.blocksBuffer.poll();
//     //Hashtable<Integer, Block> centeredBlocks = new Hashtable<Integer, Block>();
//     TreeMap<Integer, Block> centeredBlocks = new TreeMap<Integer, Block>();
//     if (blocks!=null && blocks.length>0) {
//       for (Block b: blocks) {
//         if (b.sig == 2) {
//           int pixyOffsetError = b.x-pixyOffset;
//           if (centeredBlocks.size()>=2 && Math.abs(pixyOffsetError) < Math.abs(centeredBlocks.lastKey())) {
//             centeredBlocks.pollLastEntry();
//             centeredBlocks.put(pixyOffsetError, b);
//           } else {
//             centeredBlocks.put(pixyOffsetError, b);
//           }
//         }
//       }

//       if (centeredBlocks.size() == 2) {
//         target1 = centeredBlocks.pollFirstEntry().getValue();
//         target2 = centeredBlocks.pollFirstEntry().getValue();
//         System.out.println(String.format("INFO: Align found target1.x: %03d target2.x: %03d", target1.x, target2.x));
//       }
//     } else {
//       target1 = null;
//       target2 = null;
//     }

  private static double[] getBlocksPos(){
    getBlocks();
    if(leftTarget1 == null || leftTarget2 == null || rightTarget1 == null || rightTarget2 == null  ) {
      return new double[]{leftPixyOffset, rightPixyOffset};
    } else {
      return new double[]{(leftTarget1.x + leftTarget2.x) * 0.5, (rightTarget1.x + rightTarget2.x) * 0.5};
    }
  }

  private static void getBlocks(){
    Block[][] blocks = Robot.pixy2SpiJNI.blocksBuffer.poll();
    //Hashtable<Integer, Block> centeredBlocks = new Hashtable<Integer, Block>();
    TreeMap<Integer, Block> centeredBlocks = new TreeMap<Integer, Block>();
// TODO: Reimplement getBlocks to generate four targets instead of just two?
    // if(blocks !=null && blocks.length>0) {
    //   for(Block b: blocks){

    //     if(b.sig == 2){ 
    //       if(centeredBlocks.size()>=2 && b.x-pixyOffset < centeredBlocks.lastKey()){
    //         centeredBlocks.pollLastEntry();
    //         centeredBlocks.put(b.x-pixyOffset, b);
    //       }else{
    //         centeredBlocks.put(b.x-pixyOffset, b);
    //       }
    //     }
    //   }

    //   if(centeredBlocks.size() ==2) {
    //     target1 = centeredBlocks.pollFirstEntry().getValue();
    //     target2 = centeredBlocks.pollFirstEntry().getValue();
    //     System.out.println("found targets");
    //   }
    // }
  }

  private static double limit(double num) {
    if (num>1) {
      return 1;
    } else if (num<-1) {
      return -1;
    } else {
      return num;
    }
  }
}
