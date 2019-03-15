/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbG extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbG() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    


    // addSequential(new MoveElevator(0));
    // addParallel(new LiftDrive(power));
    // addSequential(new Move(distanceOne));
    // addSequential(new MoveLift(0));
    // addSequential(new Move(distanceTwo));

 
    // addSequential(new Zero());
    // addSequential(new Wait(.5));
    addSequential(new Raise(1));
    addSequential(new Wait(3));
    addSequential(new Raise(2));
    addSequential(new Wait(3));
    addSequential(new LiftDrive(4, -1));
    addSequential(new MoveElevator(7));
    addSequential(new ActivateArms("down"));
    addParallel(new LiftDrive(1.5, -1));
    addSequential(new Move(1.5, .25));
    addSequential(new Wait(1));
    addParallel(new Move(1, .1));
    addSequential(new MoveLift(2.5));
    addSequential(new Wait(1));
    // addParallel(new Move(.4, .25));
    // addSequential(new ActivateArms("up"));
  }
}
