/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.LinkedList;

import org.graalvm.compiler.graph.Position;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetEndEffector extends Command {

  private float liftDistance;

  public SetEndEffector(float liftDistance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.lift);

    this.liftDistance = liftDistance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //Robot.lift.elevatorSpark.set(Robot.m_oi.liftStick.getY());

    Robot.lift.elevatorSpark.set(liftDistance);

    LinkedList<Float> liftEncoderValues = new LinkedList<Float>();
    //liftEncoderValues.add(Robot.lift.elevatorSpark.getEncoder());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
