/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Add your docs here.
 */
public class DistancePIDSubsystem extends PIDSubsystem {
  /**
   * Add your docs here.
   */

  WPI_TalonSRX leftMasterTalon;
  WPI_TalonSRX leftSlaveTalon;
  WPI_TalonSRX rightMasterTalon;
  WPI_TalonSRX rightSlaveTalon;

  private double kMaxVelocity;
  public DistancePIDSubsystem() {

    

    // Intert a subsystem name and PID values here
    super("DistancePIDSubsytem", 1, 2, 3);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.

    leftMasterTalon = new WPI_TalonSRX(1);
    leftSlaveTalon = new WPI_TalonSRX(2);
    rightMasterTalon = new WPI_TalonSRX(3);
    rightSlaveTalon = new WPI_TalonSRX(4);
  
    leftSlaveTalon.follow(leftMasterTalon);
    rightSlaveTalon.follow(rightMasterTalon);

    rightMasterTalon.setInverted(true);
    rightSlaveTalon.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    
    return leftMasterTalon.getActiveTrajectoryVelocity() / kMaxVelocity;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    leftMasterTalon.set(ControlMode.Velocity, output);

  }
}
