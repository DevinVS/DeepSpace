/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // Master Talons are the motors closest to the back
  public static int leftMasterTalonPort = 1;
  public static int leftSlaveTalonPort = 2;

  public static int rightMasterTalonPort = 3;
  public static int rightSlaveTalonPort = 4;

  public static int leftIntakeTalonPort = 5;
  public static int rightIntakeTalonPort = 6;

  public static int intakeSolenoidInPort = 7;
  public static int intakeSolenoidOutport = 5;

  public static int shiftSolenoidInPort = 4;
  public static int shiftSolenoidOutPort = 6;

  public static int placeSolenoidInPort = 0;
  public static int placeSolenoidOutPort = 1;

  public static int brakeSolenoidInPort = 2;
  public static int brakeSolenoidOutPort = 3;
  
  public static int backDriveTalon = 7;
  
  public static int elevatorSparkPort = 8;
  public static int liftSparkPort = 9;
}
