/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ControlType;

import frc.robot.Constants;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {

  private static WPI_TalonSRX liftDrive;

  private static CANSparkMax liftSpark;
  private static CANPIDController liftPID;
  private static CANEncoder liftEncoder;

  private static CANSparkMax elevatorSpark;
  private static CANPIDController elevatorPID;
  private static CANEncoder elevatorEncoder;

  public Lift(){
    liftDrive = new WPI_TalonSRX(RobotMap.backDriveTalon);

    liftSpark = new CANSparkMax(RobotMap.liftSparkPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    liftSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);
    liftSpark.setInverted(true);
    liftEncoder = liftSpark.getEncoder();
    liftPID = liftSpark.getPIDController();
    liftEncoder = liftSpark.getEncoder();

    liftPID.setFF(Constants.elevatorkF);
    liftPID.setP(Constants.elevatorkP);
    liftPID.setI(Constants.elevatorkI);
    liftPID.setD(Constants.elevatorkD);

    liftPID.setSmartMotionAllowedClosedLoopError(0, 0);
    liftPID.setSmartMotionMaxAccel(Constants.elevatorkMaxAccel, 0);
    liftPID.setSmartMotionMaxVelocity(Constants.elevatorkMaxVelocity, 0);
    liftPID.setSmartMotionMinOutputVelocity(0, 0);

    elevatorSpark = new CANSparkMax(RobotMap.elevatorSparkPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    elevatorSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);
    elevatorSpark.setInverted(true);
    elevatorEncoder = elevatorSpark.getEncoder();
    elevatorPID = elevatorSpark.getPIDController();

    elevatorPID.setSmartMotionMaxAccel(Constants.elevatorkMaxAccel, 0);
    elevatorPID.setSmartMotionMaxVelocity(Constants.elevatorkMaxVelocity, 0);
    elevatorPID.setSmartMotionAllowedClosedLoopError(0, 0);
    elevatorPID.setSmartMotionMinOutputVelocity(0 , 0);

    elevatorPID.setFF(Constants.elevatorkF);
    elevatorPID.setP(Constants.elevatorkP);
    elevatorPID.setI(Constants.elevatorkI);
    elevatorPID.setD(Constants.elevatorkD);

    SmartDashboard.putNumber("MyValue", 0);
    liftEncoder.setPosition(0);


  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setElevator(double targetPos){
    elevatorPID.setReference(targetPos, ControlType.kSmartMotion);
  }

  public void setLift(double targetPos){
    liftPID.setReference(targetPos, ControlType.kSmartMotion);
  }

  public void setDrive(double magnitude){
    liftDrive.set(magnitude);
  }
  
  public double[] getAmps(){
    double[] nums= {elevatorSpark.getOutputCurrent(), liftSpark.getOutputCurrent()};
    return nums;
  }

  public double getElevatorPosition(){return elevatorEncoder.getPosition();}
  public double getLiftPosition(){return liftEncoder.getPosition();}

  public void zero(){
    elevatorPID.setReference(0, ControlType.kCurrent);
  }


  public void liftDrive(double power){
    liftDrive.set(ControlMode.PercentOutput, power);
  }

  public void up(double targetPos){
    elevatorPID.setReference(targetPos*1.5, ControlType.kSmartMotion);
    liftPID.setReference(targetPos, ControlType.kSmartMotion);
  }

}
