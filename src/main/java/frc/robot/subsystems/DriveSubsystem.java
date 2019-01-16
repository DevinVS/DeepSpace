/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {

  //1 = back left
  //2 = front left
  //3 = back right
  //4 =  back left

  public static WPI_TalonSRX rightMasterTalon;
  private static WPI_TalonSRX rightSlaveTalon;
  public static WPI_TalonSRX leftMasterTalon;
  private static WPI_TalonSRX leftSlaveTalon;
  

  private static double encoderRevsPerWheelRev = 7.5;
  private static double wheelDiameterFeet = 0.5;
  public static double encoderPulsePerDistance;
  public DifferentialDrive tankDrive;

  public DriveSubsystem(){

    //return (int) (((encoderRevsPerWheelRev * feetPerSec) / (wheelDiameterFeet * Math.PI)) * 4096) / 10;
    
   encoderPulsePerDistance = (((encoderRevsPerWheelRev) / (wheelDiameterFeet * Math.PI)) * 4096) / 10;
    
    
    leftMasterTalon = RobotMap.leftMasterTalon;
    leftSlaveTalon = RobotMap.leftSlaveTalon;
    rightMasterTalon = RobotMap.rightMasterTalon;
    rightSlaveTalon = RobotMap.rightSlaveTalon;
  


    leftSlaveTalon.follow(leftMasterTalon);
    rightSlaveTalon.follow(rightMasterTalon);

    

    tankDrive = new DifferentialDrive(leftMasterTalon, rightMasterTalon);   
  }

  public void Stop(){
    rightMasterTalon.set(ControlMode.PercentOutput, 0);
    leftMasterTalon.set(ControlMode.PercentOutput, 0);
    
  }

  public static void MoveLR (double leftValue, double rightValue){
    leftMasterTalon.set(leftValue);
    rightMasterTalon.set(rightValue);
  }

  

  @Override
  public void initDefaultCommand(){
    setDefaultCommand(new JoystickCommand());

  }
}
