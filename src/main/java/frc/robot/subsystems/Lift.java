/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import frc.robot.Constants;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
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

    // liftPID = liftSpark.getPIDController();

    // liftPID.setFF(Constants.lVelocity_kF);
    // liftPID.setP(Constants.lVelocity_kP);
    // liftPID.setI(Constants.lVelocity_kI);
    // liftPID.setD(Constants.lVelocity_kD);

    elevatorSpark = new CANSparkMax(RobotMap.elevatorSparkPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    elevatorSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);
    // elevatorPID = elevatorSpark.getPIDController();

    elevatorPID.setFF(0.00001);
    elevatorPID.setP(0);
    elevatorPID.setI(0);
    elevatorPID.setD(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setElevator(double magnitude){
    elevatorSpark.set(magnitude);
  }

  public void setLift(double magnitude){
    liftSpark.set(magnitude);
  }

  public void setDrive(double magnitude){
    liftDrive.set(magnitude);
  }
}
