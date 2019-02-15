/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LiftVelocityMove;
import frc.robot.commands.OutakeCommand;
import frc.robot.commands.UpCommand;
import frc.robot.commands.Move;
import frc.robot.commands.LiftRobot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public JoystickButton buttonOne;
  public JoystickButton buttonThree;
  public JoystickButton buttonEleven;
  public JoystickButton buttonTen;
  public JoystickButton buttonNine;
  public Joystick right;
  public Joystick left;
  public AHRS gyro;
  public JoystickButton buttonTwo;

  public OI(){
    left = new Joystick(0);
    right = new Joystick(1);
    buttonOne = new JoystickButton(right,1);
    buttonThree = new JoystickButton(right,3);
//    buttonEleven = new JoystickButton(right,11);
    buttonTen = new JoystickButton(right,10); 
    buttonNine = new JoystickButton(right,9);

    buttonTwo = new JoystickButton(right, 2);

    buttonOne.whileHeld(new IntakeCommand());
    buttonThree.whileHeld(new OutakeCommand());
  //  buttonEleven.whenPressed(new Move(640000));
    buttonTen.whenPressed(new LiftVelocityMove(10 , 50));
    buttonNine.whenPressed(new UpCommand(50));
    buttonTwo.whileHeld(new LiftRobot());

    gyro = new AHRS(Port.kMXP);
  }

}
