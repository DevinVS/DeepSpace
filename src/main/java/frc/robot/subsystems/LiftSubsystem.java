/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
/**
 * Add your docs here.
 */
public class LiftSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static CANSparkMax testBrushLess;



  public LiftSubsystem(){
    // maping the Brushless motor in the Robot Map
    testBrushLess = RobotMap.testBrushLess;

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    
  }

  public void moveUpComand() {
    
  }

  public void moveDownComand() {

  }

  public void stopLift(){
    //Cuts Power to lift
    testBrushLess.stopMotor();
  }

}
