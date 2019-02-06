/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Constants;
import frc.robot.commands.JoystickDrive;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX leftMasterTalon;
  private WPI_TalonSRX leftSlaveTalon;
  private WPI_TalonSRX rightMasterTalon;
  private WPI_TalonSRX rightSlaveTalon;

  public Drivetrain(){
    leftMasterTalon = new WPI_TalonSRX(RobotMap.leftMasterTalonPort);
    leftSlaveTalon = new WPI_TalonSRX(RobotMap.leftSlaveTalonPort);
    rightMasterTalon = new WPI_TalonSRX(RobotMap.rightMasterTalonPort);
    rightSlaveTalon = new WPI_TalonSRX(RobotMap.rightSlaveTalonPort);

    leftSlaveTalon.follow(leftMasterTalon);
    rightSlaveTalon.follow(rightMasterTalon);

    leftMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kTimeoutMs);
    rightMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kTimeoutMs);

    rightMasterTalon.setInverted(true);
    rightSlaveTalon.setInverted(true);

    //Slot 0 = Distance PID
    //Slot 1 = Velocity PID
    leftMasterTalon.config_kF(0, Constants.lDistance_kF, Constants.kTimeoutMs);
    leftMasterTalon.config_kP(0, Constants.lDistance_kP, Constants.kTimeoutMs);
    leftMasterTalon.config_kI(0, Constants.lDistance_kI, Constants.kTimeoutMs);
    leftMasterTalon.config_kD(0, Constants.lDistance_kD, Constants.kTimeoutMs);

    leftMasterTalon.config_kF(1, Constants.lVelocity_kF, Constants.kTimeoutMs);
    leftMasterTalon.config_kP(1, Constants.lVelocity_kP, Constants.kTimeoutMs);
    leftMasterTalon.config_kI(1, Constants.lVelocity_kI, Constants.kTimeoutMs);
    leftMasterTalon.config_kD(1, Constants.lVelocity_kD, Constants.kTimeoutMs);

    rightMasterTalon.config_kF(0, Constants.lDistance_kF, Constants.kTimeoutMs);
    rightMasterTalon.config_kP(0, Constants.lDistance_kP, Constants.kTimeoutMs);
    rightMasterTalon.config_kI(0, Constants.lDistance_kI, Constants.kTimeoutMs);
    rightMasterTalon.config_kD(0, Constants.lDistance_kD, Constants.kTimeoutMs);

    rightMasterTalon.config_kF(1, Constants.lVelocity_kF, Constants.kTimeoutMs);
    rightMasterTalon.config_kP(1, Constants.lVelocity_kP, Constants.kTimeoutMs);
    rightMasterTalon.config_kI(1, Constants.lVelocity_kI, Constants.kTimeoutMs);
    rightMasterTalon.config_kD(1, Constants.lVelocity_kD, Constants.kTimeoutMs);
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new JoystickDrive());
  }

  public void move(String type, double magnitude){
    switch(type){
      case "pow":
        leftMasterTalon.set(ControlMode.PercentOutput, magnitude);
        rightMasterTalon.set(ControlMode.PercentOutput, magnitude);
        break;
      case "vel":
        leftMasterTalon.set(ControlMode.Velocity, magnitude);
        rightMasterTalon.set(ControlMode.Velocity, magnitude);
        break;
      case "pos":
        leftMasterTalon.set(ControlMode.MotionMagic, magnitude);
        rightMasterTalon.set(ControlMode.MotionMagic, magnitude);
        break;
    }
  }

  public void turn(String type, double magnitude){
    switch(type){
      case "pow":
        leftMasterTalon.set(ControlMode.PercentOutput, magnitude);
        rightMasterTalon.set(ControlMode.PercentOutput, -magnitude);
        break;
      case "vel":
        leftMasterTalon.set(ControlMode.Velocity, magnitude);
        rightMasterTalon.set(ControlMode.Velocity, -magnitude);
        break;
      case "pos":
        leftMasterTalon.set(ControlMode.MotionMagic, magnitude);
        rightMasterTalon.set(ControlMode.MotionMagic, -magnitude);
        break;
    }
  }
  public void print(){
    System.out.println(leftMasterTalon.getSelectedSensorPosition());
  }
}
