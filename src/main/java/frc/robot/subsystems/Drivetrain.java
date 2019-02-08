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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.Constants;
import frc.robot.commands.JoystickDrive;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX leftMasterTalon;
  private WPI_TalonSRX leftSlaveTalon;
  public WPI_TalonSRX rightMasterTalon;
  private WPI_TalonSRX rightSlaveTalon;
  public DifferentialDrive drive;
  private double quickStopAccumulator;

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

    leftMasterTalon.configClosedloopRamp(.5, Constants.kTimeoutMs);
    rightMasterTalon.configClosedloopRamp(.5, Constants.kTimeoutMs);
    //drive = new DifferentialDrive(leftMasterTalon, rightMasterTalon);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new JoystickDrive());
  }

  public void zero(){
    leftMasterTalon.setSelectedSensorPosition(0);
    rightMasterTalon.setSelectedSensorPosition(0);
  }

  public void set(ControlMode type, double magnitude){
    switch(type){
      case PercentOutput:
        leftMasterTalon.set(ControlMode.PercentOutput, magnitude);
        rightMasterTalon.set(ControlMode.PercentOutput, magnitude);
        break;
      case Velocity:
        leftMasterTalon.set(ControlMode.Velocity, magnitude);
        rightMasterTalon.set(ControlMode.Velocity, magnitude);
        break;
      case MotionMagic:
        leftMasterTalon.set(ControlMode.MotionMagic, magnitude);
        rightMasterTalon.set(ControlMode.MotionMagic, magnitude);
        break;
    }
  }

  public void print(){
    System.out.println(leftMasterTalon.getSelectedSensorVelocity());
  }

  public int[] getPosition(){
    int[] positions = {leftMasterTalon.getSelectedSensorPosition(), rightMasterTalon.getSelectedSensorPosition()};
    return positions;
    
  }

  public int[] getVelocity(){
    int[] velocities = {leftMasterTalon.getSelectedSensorPosition(), rightMasterTalon.getSelectedSensorPosition()};
    return velocities;
  }

  public void arcadeDrive(double xSpeed, double zRotation, boolean squareInputs) {

    double m_deadband = 0.02;
    xSpeed = limit(xSpeed);
    xSpeed = applyDeadband(xSpeed, m_deadband);

    zRotation = limit(zRotation);
    zRotation = applyDeadband(zRotation, m_deadband);

    // Square the inputs (while preserving the sign) to increase fine control
    // while permitting full power.
    if (squareInputs) {
      xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
      zRotation = Math.copySign(zRotation * zRotation, zRotation);
    }

    double leftMotorOutput;
    double rightMotorOutput;

    double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

    if (xSpeed >= 0.0) {
      // First quadrant, else second quadrant
      if (zRotation >= 0.0) {
        leftMotorOutput = maxInput;
        rightMotorOutput = xSpeed - zRotation;
      } else {
        leftMotorOutput = xSpeed + zRotation;
        rightMotorOutput = maxInput;
      }
    } else {
      // Third quadrant, else fourth quadrant
      if (zRotation >= 0.0) {
        leftMotorOutput = xSpeed + zRotation;
        rightMotorOutput = maxInput;
      } else {
        leftMotorOutput = maxInput;
        rightMotorOutput = xSpeed - zRotation;
      }
    }

    leftMasterTalon.set(ControlMode.Velocity, limit(leftMotorOutput) * Constants.kMaxVelocity);
    rightMasterTalon.set(ControlMode.Velocity, limit(rightMotorOutput) * -Constants.kMaxVelocity);

  }

  protected double limit(double value) {
    if (value > 1.0) {
      return 1.0;
    }
    if (value < -1.0) {
      return -1.0;
    }
    return value;
  }

  public void setPIDSlot(int slot){
    leftMasterTalon.selectProfileSlot(slot, 0);
    rightMasterTalon.selectProfileSlot(slot, 0);
  }

  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    xSpeed = limit(xSpeed);
    

    zRotation = limit(zRotation);

    double angularPower;
    boolean overPower;

    if (isQuickTurn) {
      if (Math.abs(xSpeed) < 0.2) {
        quickStopAccumulator = (1 - .1) * quickStopAccumulator
            + .1 * limit(zRotation) * 2;
      }
      overPower = true;
      angularPower = zRotation;
    } else {
      overPower = false;
      angularPower = Math.abs(xSpeed) * zRotation - quickStopAccumulator;

      if (quickStopAccumulator > 1) {
        quickStopAccumulator -= 1;
      } else if (quickStopAccumulator < -1) {
        quickStopAccumulator += 1;
      } else {
        quickStopAccumulator = 0.0;
      }
    }

    double leftMotorOutput = xSpeed + angularPower;
    double rightMotorOutput = xSpeed - angularPower;

    // If rotation is overpowered, reduce both outputs to within acceptable range
    if (overPower) {
      if (leftMotorOutput > 1.0) {
        rightMotorOutput -= leftMotorOutput - 1.0;
        leftMotorOutput = 1.0;
      } else if (rightMotorOutput > 1.0) {
        leftMotorOutput -= rightMotorOutput - 1.0;
        rightMotorOutput = 1.0;
      } else if (leftMotorOutput < -1.0) {
        rightMotorOutput -= leftMotorOutput + 1.0;
        leftMotorOutput = -1.0;
      } else if (rightMotorOutput < -1.0) {
        leftMotorOutput -= rightMotorOutput + 1.0;
        rightMotorOutput = -1.0;
      }
    }

    // Normalize the wheel speeds
    double maxMagnitude = Math.max(Math.abs(leftMotorOutput), Math.abs(rightMotorOutput));
    if (maxMagnitude > 1.0) {
      leftMotorOutput /= maxMagnitude;
      rightMotorOutput /= maxMagnitude;
    }

    leftMasterTalon.set(ControlMode.Velocity, leftMotorOutput * Constants.kMaxVelocity);
    rightMasterTalon.set(ControlMode.Velocity, rightMotorOutput * Constants.kMaxVelocity * -1);

  }

  protected double applyDeadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }
}