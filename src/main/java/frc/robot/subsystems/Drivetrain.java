/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Constants;
import frc.robot.Robot;
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

  private static DoubleSolenoid shiftSolenoid;

  private double lkP, lkI, lkD, rkP, rkI, rkD;

  public Drivetrain(){
    leftMasterTalon = new WPI_TalonSRX(RobotMap.leftMasterTalonPort);
    leftSlaveTalon = new WPI_TalonSRX(RobotMap.leftSlaveTalonPort);
    rightMasterTalon = new WPI_TalonSRX(RobotMap.rightMasterTalonPort);
    rightSlaveTalon = new WPI_TalonSRX(RobotMap.rightSlaveTalonPort);

    // leftMasterTalon.configFactoryDefault();
    // leftSlaveTalon.configFactoryDefault();
    // rightMasterTalon.configFactoryDefault();
    // rightSlaveTalon.configFactoryDefault();

    leftSlaveTalon.follow(leftMasterTalon);
    rightSlaveTalon.follow(rightMasterTalon);

    leftMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kTimeoutMs);
    rightMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kTimeoutMs);

    leftMasterTalon.setInverted(true);
    leftSlaveTalon.setInverted(true);

    shiftSolenoid = new DoubleSolenoid(RobotMap.shiftSolenoidInPort, RobotMap.shiftSolenoidOutPort);

    lkP = Constants.lVelocity_kP;
    lkI = Constants.lVelocity_kI;
    lkD = Constants.lVelocity_kD;

    rkP = Constants.lVelocity_kP;
    rkI = Constants.lVelocity_kI;
    rkD = Constants.lVelocity_kD;

    //Slot 0 = Distance PID
    //Slot 1 = Velocity PID
    leftMasterTalon.config_kF(0, Constants.lDistance_kF, Constants.kTimeoutMs);
    leftMasterTalon.config_kP(0, Constants.lDistance_kP, Constants.kTimeoutMs);
    leftMasterTalon.config_kI(0, Constants.lDistance_kI, Constants.kTimeoutMs);
    leftMasterTalon.config_kD(0, Constants.lDistance_kD, Constants.kTimeoutMs);

    leftMasterTalon.config_kF(1, Constants.lVelocity_kF, Constants.kTimeoutMs);
    leftMasterTalon.config_kP(1, lkP, Constants.kTimeoutMs);
    leftMasterTalon.config_kI(1, lkI, Constants.kTimeoutMs);
    leftMasterTalon.config_kD(1, lkD, Constants.kTimeoutMs);

    rightMasterTalon.config_kF(0, Constants.rDistance_kF, Constants.kTimeoutMs);
    rightMasterTalon.config_kP(0, Constants.rDistance_kP, Constants.kTimeoutMs);
    rightMasterTalon.config_kI(0, Constants.rDistance_kI, Constants.kTimeoutMs);
    rightMasterTalon.config_kD(0, Constants.rDistance_kD, Constants.kTimeoutMs);


    rightMasterTalon.config_kF(1, Constants.lVelocity_kF, Constants.kTimeoutMs);
    rightMasterTalon.config_kP(1, rkP, Constants.kTimeoutMs);
    rightMasterTalon.config_kI(1, rkI, Constants.kTimeoutMs);
    rightMasterTalon.config_kD(1, rkD, Constants.kTimeoutMs);



    leftMasterTalon.configMotionAcceleration(6385);
    leftMasterTalon.configMotionCruiseVelocity(6385);

    leftMasterTalon.configMotionAcceleration(6835);
    leftMasterTalon.configMotionCruiseVelocity(6835);

    // leftMasterTalon.setNeutralMode(NeutralMode.Coast);
    // rightMasterTalon.setNeutralMode(NeutralMode.Coast);
    // leftSlaveTalon.setNeutralMode(NeutralMode.Coast);
    // rightSlaveTalon.setNeutralMode(NeutralMode.Coast);

    leftMasterTalon.configClosedloopRamp(0.33, Constants.kTimeoutMs);
    rightMasterTalon.configClosedloopRamp(0.33, Constants.kTimeoutMs);

    // if(Robot.debug){
    //   SmartDashboard.putNumber("lP", lkP);
    //   SmartDashboard.putNumber("lI", lkI);
    //   SmartDashboard.putNumber("lD", lkD);

    //   SmartDashboard.putNumber("rP", rkP);
    //   SmartDashboard.putNumber("rI", rkI);
    //   SmartDashboard.putNumber("rD", rkD);
    // }

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

  public void set(ControlMode type, double leftMagnitude, double rightMagnitude){
    switch(type){
      case PercentOutput:
        leftMasterTalon.set(ControlMode.PercentOutput, leftMagnitude);
        rightMasterTalon.set(ControlMode.PercentOutput, rightMagnitude);
        break;
      case Velocity:
        leftMasterTalon.set(ControlMode.Velocity, leftMagnitude);
        rightMasterTalon.set(ControlMode.Velocity, rightMagnitude);
        break;
      case MotionMagic:
        leftMasterTalon.set(ControlMode.MotionMagic, leftMagnitude);
        rightMasterTalon.set(ControlMode.MotionMagic, rightMagnitude);
        break;
      default:
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
    int[] velocities = {leftMasterTalon.getSelectedSensorVelocity(), rightMasterTalon.getSelectedSensorVelocity()};
    return velocities;
  }

  public double[] getCurrent(){
    double[] currents = {leftMasterTalon.getOutputCurrent(), rightMasterTalon.getOutputCurrent()};
    return currents;
  }

  public void arcadeDrive(double xSpeed, double zRotation, boolean squareInputs) {

    double m_deadband = 0.1;
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

    // if(Math.signum(leftMotorOutput)  == -Math.signum(rightMotorOutput)){
    //   leftMasterTalon.set(ControlMode.Velocity, limit(leftMotorOutput) * Constants.kMaxVelocity);
    //   rightMasterTalon.set(ControlMode.Velocity, limit(rightMotorOutput) * -Constants.kMaxVelocity);
    // }else{
    //   if()
    //   leftMasterTalon.set(ControlMode.PercentOutput, limit(leftMotorOutput) * 0.55);
    //   rightMasterTalon.set(ControlMode.PercentOutput, limit(-rightMotorOutput) * 0.55);
    // }
    if(!Robot.m_oi.button11.get()) {
      if(Math.abs(xSpeed) > Math.abs(zRotation)){
        leftMasterTalon.set(ControlMode.PercentOutput, limit(leftMotorOutput) * 0.5);
        rightMasterTalon.set(ControlMode.PercentOutput, limit(rightMotorOutput) * -0.5);
      }else{
        leftMasterTalon.set(ControlMode.Velocity, limit(leftMotorOutput) * Constants.kMaxVelocity);
        rightMasterTalon.set(ControlMode.Velocity, limit(rightMotorOutput) * -Constants.kMaxVelocity);
      }
    }else{
      if(Math.abs(xSpeed) > Math.abs(zRotation)){
        leftMasterTalon.set(ControlMode.PercentOutput, limit(leftMotorOutput) * 0.5);
        rightMasterTalon.set(ControlMode.PercentOutput, limit(rightMotorOutput) * -0.5);
      }else{
        leftMasterTalon.set(ControlMode.Velocity, limit(leftMotorOutput) * -Constants.kMaxVelocity);
        rightMasterTalon.set(ControlMode.Velocity, limit(rightMotorOutput) * Constants.kMaxVelocity);
      }
    }


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

  public void getConstants(){
    if(Robot.debug){
      double lP,lI,lD, rP, rI, rD;

      lP = SmartDashboard.getNumber("lp", 0);
      lI = SmartDashboard.getNumber("li", 0);
      lD = SmartDashboard.getNumber("ld", 0);

      rP = SmartDashboard.getNumber("rp", 0);
      rI = SmartDashboard.getNumber("ri", 0);
      rD = SmartDashboard.getNumber("rd", 0);

      if(lP != lkP){lkP = lP; leftMasterTalon.config_kP(1, lkP);}
      if(lI != lkI){lkI = lI; leftMasterTalon.config_kI(1, lkI);}
      if(lD != lkD){lkD = lD; leftMasterTalon.config_kD(1, lkD);}

      if(rP != rkP){rkP = rP; rightMasterTalon.config_kP(1, rkP);}
      if(rI != rkI){rkI = rI; rightMasterTalon.config_kI(1, rkI);}
      if(rD != rkD){rkD = rD; rightMasterTalon.config_kD(1, rkD);}

    }
  }

public void turn(double angle) {
}
}