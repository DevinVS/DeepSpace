/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;


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

  public static Joystick stick = new Joystick(0);
  public static Joystick gamepad = new Joystick(1);

  public static JoystickButton pad1 = new JoystickButton(gamepad, 1);
  public static JoystickButton pad2 = new JoystickButton(gamepad, 2);
  public static JoystickButton pad3 = new JoystickButton(gamepad, 3);
  public static JoystickButton pad4 = new JoystickButton(gamepad, 4);
  public static JoystickButton pad5 = new JoystickButton(gamepad, 5);
  public static JoystickButton pad6 = new JoystickButton(gamepad, 6);
  public static JoystickButton pad7 = new JoystickButton(gamepad, 7);
  public static JoystickButton pad8 = new JoystickButton(gamepad, 8);
  public static JoystickButton pad9 = new JoystickButton(gamepad, 9);


  public static JoystickButton trigger = new JoystickButton(stick, 1);
  public static JoystickButton button3 = new JoystickButton(stick, 3);
  public static JoystickButton button2 = new JoystickButton(stick, 2);
  public static JoystickButton button5 = new JoystickButton(stick, 5);
  public static JoystickButton button4 = new JoystickButton(stick, 4);
  public static JoystickButton button6 = new JoystickButton(stick, 6);
  public static JoystickButton button7 = new JoystickButton(stick, 7);
  public static JoystickButton button8 = new JoystickButton(stick, 8);
  public static JoystickButton button9 = new JoystickButton(stick, 9);
  public static JoystickButton button10 = new JoystickButton(stick, 10);
  public static JoystickButton button11 = new JoystickButton(stick, 11);

  public static ComboButton climb2 = new ComboButton(pad6, button6);
  public static ComboButton climb3 = new ComboButton(pad5, button6);

  public static ComboButton test = new ComboButton(pad2, button6);

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



  public OI(){
    
    
    trigger.whenPressed(new Intake());
    trigger.whenReleased(new NeutralIntake() );
    
    button2.whileHeld(new Align());

    button8.whenPressed(new InstantCommand(){
        @Override
        protected void initialize() {
          Robot.lift.resetEncoders();
        }
    });

    pad1.whenPressed(new SetObjectMode("hatch"));
    pad1.whenReleased(new SetObjectMode("ball"));
    test.whenPressed(new TopConditional());
    pad3.whenPressed(new MiddleConditional());
    pad4.whenPressed(new LowConditional());
    climb3.whenPressed(new ClimbG());
    climb2.whenPressed(new twoClimbG());
    pad7.whenPressed(new MoveElevator(1));
    pad8.whenPressed(new HighCenter());
    // pad9.whenPressed(new Zero());

  }
}
