/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class IntakeSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
public static WPI_TalonSRX leftIntakeTalon;
public static WPI_TalonSRX rightIntakeTalon;

private static final float INPUT_INTAKE_RATIO = .3f;
private static final float INPUT_OUTAKE_RATIO = .5f;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public IntakeSubsystem(){
    leftIntakeTalon = RobotMap.leftIntakeTalon;
    leftIntakeTalon.setNeutralMode(NeutralMode.Brake);

    rightIntakeTalon = RobotMap.rightIntakeTalon;
    rightIntakeTalon.setNeutralMode(NeutralMode.Brake);
  }

  public void IntakeBall(){
      
    leftIntakeTalon.set(ControlMode.PercentOutput, INPUT_INTAKE_RATIO);
    rightIntakeTalon.set(ControlMode.PercentOutput, -INPUT_INTAKE_RATIO);

  }

  public void EndIntake(){
    leftIntakeTalon.set(ControlMode.PercentOutput, 0);
    rightIntakeTalon.set(ControlMode.PercentOutput, 0);
  }

  public void OutakeBall(){
    leftIntakeTalon.set(ControlMode.PercentOutput, -INPUT_OUTAKE_RATIO);
    rightIntakeTalon.set(ControlMode.PercentOutput, INPUT_OUTAKE_RATIO);
  }

  
}

