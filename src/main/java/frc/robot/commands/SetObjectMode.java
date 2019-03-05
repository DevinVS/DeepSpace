/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class SetObjectMode extends InstantCommand {
  /**
   * Add your docs here.
   */
    String mode;
    public static int toggle = 1;

  public SetObjectMode(String mode) {
    this.mode = mode;
    requires(Robot.io);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    System.out.println("Executing SetObjectMode Command ");
    switch(mode){
      case "Ball":
        Robot.io.Place("put");
        Robot.io.Toggle(0);
        break;
      case "Hatch":
        Robot.io.Place("take");
        Robot.io.Toggle(1);
        break;
    }
  }

}
