/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static int leftMasterTalonPort = 1;
  public static int leftSlaveTalonPort = 2;
  public static int rightMasterTalonPort = 3;
  public static int rightSlaveTalonPort = 4;
  public static int intakeTalonPort = 5;
  public static int outtakeTalonPort = 6;

  public static WPI_TalonSRX leftMasterTalon;
  public static WPI_TalonSRX leftSlaveTalon;
  public static WPI_TalonSRX rightMasterTalon;
  public static WPI_TalonSRX rightSlaveTalon;
  public static WPI_TalonSRX intakeTalon;
  public static WPI_TalonSRX outtakeTalon;

  private static int timeoutMs = 10;  
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public void init(){
    leftMasterTalon = new WPI_TalonSRX(leftMasterTalonPort);
    leftMasterTalon.set(ControlMode.PercentOutput, 0);
    leftMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeoutMs);

    leftSlaveTalon = new WPI_TalonSRX(leftSlaveTalonPort);
    leftSlaveTalon.set(ControlMode.PercentOutput, 0);
    leftSlaveTalon.follow(leftMasterTalon);

    rightMasterTalon = new WPI_TalonSRX(rightMasterTalonPort);
    rightMasterTalon.set(ControlMode.PercentOutput, 0);
    rightMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeoutMs);

    rightSlaveTalon = new WPI_TalonSRX(rightSlaveTalonPort);
    rightSlaveTalon.set(ControlMode.PercentOutput, 0);
    rightSlaveTalon.follow(rightMasterTalon);

    intakeTalon = new WPI_TalonSRX(intakeTalonPort);
    intakeTalon.set(ControlMode.PercentOutput, 0);
    
    outtakeTalon = new WPI_TalonSRX(outtakeTalonPort);
    outtakeTalon.set(ControlMode.PercentOutput, 0);

    leftMasterTalon.config_kF(0, 0.08497, timeoutMs); // calculated 0.08497, raised to 
    leftMasterTalon.config_kP(0, 0.005, timeoutMs); // 0.005
    leftMasterTalon.config_kI(0, 0, timeoutMs); // 0.001
    leftMasterTalon.config_kD(0, 0, timeoutMs);
    leftMasterTalon.config_IntegralZone(0, 1000, timeoutMs);

    leftMasterTalon.configClosedloopRamp(0.25, timeoutMs);

    rightMasterTalon.config_kF(0, 0.09, timeoutMs); // calculated was 0.08757 raised to 0.09 for tuning
    rightMasterTalon.config_kP(0, 0.005, timeoutMs); // 0.005
    rightMasterTalon.config_kI(0, 0, timeoutMs);
    rightMasterTalon.config_kD(0, 0, timeoutMs);
    rightMasterTalon.config_IntegralZone(0, 1000, timeoutMs);

    rightMasterTalon.configClosedloopRamp(0.25, timeoutMs);
  }
}
