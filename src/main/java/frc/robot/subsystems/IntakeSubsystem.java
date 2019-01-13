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
public static WPI_TalonSRX intakeTalon;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public IntakeSubsystem(){
    intakeTalon = new WPI_TalonSRX(RobotMap.intakeTalon);
    intakeTalon.setNeutralMode(NeutralMode.Brake);
  }

  public void IntakeBall(){
      
    intakeTalon.set(ControlMode.PercentOutput, -.3);

  }

  public void EndIntake(){
    intakeTalon.set(ControlMode.PercentOutput, 0);
  }

  public void OutakeBall(){
    intakeTalon.set(ControlMode.PercentOutput, 1);
  }

  
}

