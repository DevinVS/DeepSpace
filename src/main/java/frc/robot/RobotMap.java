/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static int leftMasterTalonPort = 2;
  public static int leftSlaveTalonPort = 1;
  public static int rightMasterTalonPort = 4;
  public static int rightSlaveTalonPort = 3;
  public static int leftTalonPort = 5;
  public static int rightTalonPort = 6;
  
  public static Spark winchMotor;
  public static CANSparkMax liftBrushLessMaster;
  public static CANSparkMax liftBrushLessSlave;

  public static CANEncoder liftEncoder1;

//Change this id to the port we are going to use
  private static int liftMotorIDMaster = 10;
  private static int liftMotorIDSlave = 11;

  public static CANPIDController liftPid;
  public static CANPIDController liftVelocityPid;
  

  public static WPI_TalonSRX leftMasterTalon;
  public static WPI_TalonSRX leftSlaveTalon;
  public static WPI_TalonSRX rightMasterTalon;
  public static WPI_TalonSRX rightSlaveTalon;
  public static WPI_TalonSRX leftIntakeTalon;
  public static WPI_TalonSRX rightIntakeTalon;

  public static int timeoutMs = 10;  
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  public RobotMap(){
    init();
  }
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
    rightMasterTalon.setInverted(true);

    rightSlaveTalon = new WPI_TalonSRX(rightSlaveTalonPort);
    rightSlaveTalon.set(ControlMode.PercentOutput, 0);
    rightSlaveTalon.follow(rightMasterTalon);
    rightSlaveTalon.setInverted(InvertType.FollowMaster);

    leftIntakeTalon = new WPI_TalonSRX(leftTalonPort);
    leftIntakeTalon.set(ControlMode.PercentOutput, 0);
   
    rightIntakeTalon = new WPI_TalonSRX(rightTalonPort);
    rightIntakeTalon.set(ControlMode.PercentOutput, 0);

    winchMotor = new Spark(9);

    leftMasterTalon.config_kF(0, 0.085, timeoutMs); // calculated 0.08497, raised to 0.09
    leftMasterTalon.config_kP(0, 0, timeoutMs); // 0.005
    leftMasterTalon.config_kI(0, 0, timeoutMs); // 0.001
    leftMasterTalon.config_kD(0, 0, timeoutMs);
    leftMasterTalon.config_IntegralZone(0, 1000, timeoutMs);
    leftMasterTalon.configMotionCruiseVelocity(11000, timeoutMs); 
    leftMasterTalon.configMotionAcceleration(5500, timeoutMs);
    leftMasterTalon.configClosedloopRamp(0.25, timeoutMs);
    leftMasterTalon.configAllowableClosedloopError(0, 475, timeoutMs);

    leftMasterTalon.configNominalOutputForward(0, timeoutMs);
		leftMasterTalon.configNominalOutputReverse(0, timeoutMs);
		leftMasterTalon.configPeakOutputForward(1, timeoutMs);
    leftMasterTalon.configPeakOutputReverse(-1, timeoutMs);

    leftMasterTalon.setSelectedSensorPosition(0, 0, timeoutMs);

    rightMasterTalon.config_kF(0, 0.085, timeoutMs); // calculated was 0.08757 raised to 0.09 for tuning
    rightMasterTalon.config_kP(0, 0.065, timeoutMs); // 0.005
    rightMasterTalon.config_kI(0, 0, timeoutMs);
    rightMasterTalon.config_kD(0, 0, timeoutMs);
    rightMasterTalon.config_IntegralZone(0, 1000, timeoutMs);
    rightMasterTalon.configMotionCruiseVelocity(11000, timeoutMs); 
    rightMasterTalon.configMotionAcceleration(5500, timeoutMs);
    rightMasterTalon.configClosedloopRamp(0.25, timeoutMs);
    rightMasterTalon.configAllowableClosedloopError(0, 475, timeoutMs);

    rightMasterTalon.configNominalOutputForward(0, timeoutMs);
	  rightMasterTalon.configNominalOutputReverse(0, timeoutMs);
	  rightMasterTalon.configPeakOutputForward(1, timeoutMs);
    rightMasterTalon.configPeakOutputReverse(-1, timeoutMs);

    rightMasterTalon.setSelectedSensorPosition(0, 0, timeoutMs);

    //This is the lift motor
    liftBrushLessMaster = new CANSparkMax(liftMotorIDMaster, MotorType.kBrushless);
    liftBrushLessSlave = new CANSparkMax(liftMotorIDSlave, MotorType.kBrushless);

    liftBrushLessSlave.follow(liftBrushLessMaster);
    //encoder for testBrushLess
    liftEncoder1 = liftBrushLessMaster.getEncoder();

    liftPid = liftBrushLessMaster.getPIDController();
    liftVelocityPid = liftBrushLessMaster.getPIDController();

    liftPid.setP(0.1);
    liftPid.setI(1e-4);
    liftPid.setD(1);
    liftPid.setIZone(0);
    liftPid.setFF(0);
    liftPid.setOutputRange(-1, 1);

    liftVelocityPid.setP(0.1);
    liftVelocityPid.setI(1e-4);
    liftVelocityPid.setD(1);
    liftVelocityPid.setIZone(0);
    liftVelocityPid.setFF(0);
    liftVelocityPid.setOutputRange(-1, 1);

    
    
  }
}
