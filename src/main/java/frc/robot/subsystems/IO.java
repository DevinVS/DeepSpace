/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class IO extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static DoubleSolenoid IntakeSolenoid;
  //private static DoubleSolenoid rightIntakeSolenoid;

  private static WPI_TalonSRX leftIntakeTalon;
  private static WPI_TalonSRX rightIntakeTalon;

  public static enum IOMode{ball, hatch}

  public IO(){
    leftIntakeTalon = new WPI_TalonSRX(RobotMap.leftIntakeTalonPort);
    rightIntakeTalon = new WPI_TalonSRX(RobotMap.rightIntakeTalonPort);

    IntakeSolenoid = new DoubleSolenoid(RobotMap.leftIntakeSolenoidInPort, RobotMap.leftIntakeSolenoidOutport);
    //rightIntakeSolenoid = new DoubleSolenoid(RobotMap.rightIntakeSolenoidInPort, RobotMap.rightIntakeSolenoidOutPort);

    leftIntakeTalon.configContinuousCurrentLimit(6, Constants.kTimeoutMs);
    rightIntakeTalon.configContinuousCurrentLimit(6, Constants.kTimeoutMs);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setPosition(String value){
    switch(value){
      case "down":
        IntakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        //rightIntakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        break;
      case "up":
        IntakeSolenoid.set(DoubleSolenoid.Value.kForward);
        //rightIntakeSolenoid.set(DoubleSolenoid.Value.kForward);
        break;
    }
  }

  public void setPower(double power){
    leftIntakeTalon.set(-power);
    rightIntakeTalon.set(power);
  }
}
