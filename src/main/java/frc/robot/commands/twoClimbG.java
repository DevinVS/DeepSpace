/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class twoClimbG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public twoClimbG() {


    addSequential(new Raise(-27, -19.5), 3);
    // addSequential(new Wait(3));
    // addSequential(new Raise(2));
    // addSequential(new Wait(3));
    addSequential(new LiftDrive(1.75, 1));
    addSequential(new MoveElevator(7));
    addSequential(new ActivateArms("down"));
    addParallel(new LiftDrive(1, 1));
    addSequential(new Move(1, .35));
    addSequential(new Wait(1));
    addParallel(new Move(1, .1));
    addParallel(new MoveLift(2.5));
    // addSequential(new LiftDrive(1, 1));
    // addSequential(new Wait(1));







  }
}
