/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class SetIntakeMode extends InstantCommand {

  String mode;

  public SetIntakeMode(String mode) {
    this.mode = mode;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Executing SetIntakeMode Command");
    switch(mode){
      case "neutral":
        Robot.io.setPower(0);
        Robot.io.setPosition("up");
        break;
      case "in":
        Robot.io.setPower(.8);
        Robot.io.setPosition("down");
        break;
      case "out":
        Robot.io.setPower(-.8);
        Robot.io.setPosition("down");
    }
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
