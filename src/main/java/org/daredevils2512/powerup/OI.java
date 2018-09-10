/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.daredevils2512.powerup;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

import org.daredevils2512.powerup.TriggerButton;
import org.daredevils2512.powerup.commands.*;

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
	
	//Declaring joysticks and buttons
	public static Joystick driver = new Joystick(0);
	public static Joystick coDriver = new Joystick(1);
	public static Joystick buttonBox = new Joystick(2);
	
	public static JoystickButton DRC_aButton = new JoystickButton(driver, 1);
	public static JoystickButton DRC_bButton = new JoystickButton(driver, 2);
	public static JoystickButton DRC_xButton = new JoystickButton(driver, 3);
	public static JoystickButton DRC_yButton = new JoystickButton(driver, 4);
	public static JoystickButton DRC_leftBumper = new JoystickButton(driver, 5);
	public static JoystickButton DRC_rightBumper = new JoystickButton(driver, 6);
	public static TriggerButton DRC_leftTrigger = new TriggerButton(driver, 2);
	public static TriggerButton DRC_rightTrigger = new TriggerButton(driver, 3);
	
	public static JoystickButton CDR_trigger = new JoystickButton(coDriver, 1);
	public static JoystickButton CDR_sideButton = new JoystickButton(coDriver, 2);
	public static JoystickButton CDR_stickBottomLeft = new JoystickButton(coDriver, 3);
	public static JoystickButton CDR_stickBottomRight = new JoystickButton(coDriver, 4);
	public static JoystickButton CDR_stickTopLeft = new JoystickButton(coDriver, 5);
	public static JoystickButton CDR_stickTopRight = new JoystickButton(coDriver, 6);
	public static JoystickButton CDR_baseTopLeft = new JoystickButton(coDriver, 7);
	public static JoystickButton CDR_baseTopRight = new JoystickButton(coDriver, 8);
	public static JoystickButton CDR_baseMiddleLeft = new JoystickButton(coDriver, 9);
	public static JoystickButton CDR_baseMiddleRight = new JoystickButton(coDriver, 10);
	public static JoystickButton CDR_baseBottomLeft = new JoystickButton(coDriver, 11);
	public static JoystickButton CDR_baseBottomRight = new JoystickButton(coDriver, 12);
	
	public static JoystickButton CDB_topWhite = new JoystickButton(buttonBox, 2);
	public static JoystickButton CDB_topRed = new JoystickButton(buttonBox, 6);
	public static JoystickButton CDB_middleWhite = new JoystickButton(buttonBox, 8);
	public static JoystickButton CDB_middleRed = new JoystickButton(buttonBox, 4);
	public static JoystickButton CDB_bottomWhite = new JoystickButton(buttonBox, 5);
	public static JoystickButton CDB_bottomRed = new JoystickButton(buttonBox, 16);
	public static JoystickButton CDB_green = new JoystickButton(buttonBox, 7);
	public static JoystickButton CDB_yellow = new JoystickButton(buttonBox, 15);
	public static JoystickButton CDB_bigWhite = new JoystickButton(buttonBox, 3);
	public static JoystickButton CDB_bigRed = new JoystickButton(buttonBox, 14);
	
	//Creating some generic commands
	IntakeRun intakeStop = new IntakeRun(0.0);
	DoubleSolenoid.Value solenoidForward = DoubleSolenoid.Value.kForward;
	DoubleSolenoid.Value solenoidReverse = DoubleSolenoid.Value.kReverse;
	
	//Attaching buttons to commands
	public OI() {
		//driver commands
		DRC_leftBumper.whenPressed(new IntakeActuateArms(solenoidForward));
		DRC_rightBumper.whenPressed(new IntakeActuateArms(solenoidReverse));
		DRC_leftTrigger.whileHeld(new IntakeRun(1.0)); //intaking
		DRC_leftTrigger.whenReleased(intakeStop);
		DRC_xButton.whileHeld(new IntakeRun(-1.0)); //extaking
		DRC_xButton.whenReleased(intakeStop);
		DRC_rightTrigger.whenPressed(new DrivetrainShift(solenoidForward));
		DRC_rightTrigger.whenReleased(new DrivetrainShift(solenoidReverse));
		
		//coDriver joystick buttons
//		CDR_trigger.whileHeld(new CMG_ExtakeCube()); //normal send out
		CDR_trigger.whenReleased(intakeStop);
		CDR_sideButton.whileHeld(new IntakeRun(0.55)); //medium send out
		CDR_sideButton.whenReleased(intakeStop);
		CDR_stickTopLeft.whileHeld (new IntakeRun(1.0)); //full send out
		CDR_stickTopLeft.whenReleased (intakeStop); //stop intake
		CDR_stickBottomLeft.whileHeld(new IntakeRun(0.4)); //soft send out
		CDR_stickBottomLeft.whenReleased(intakeStop); //stop intake
		CDR_stickTopRight.whenPressed(new IntakeActuateArms(solenoidForward)); //actuate intake arms in
		CDR_stickBottomRight.whenPressed(new IntakeActuateArms(solenoidReverse)); //actuate intake arms out
		
		CDR_baseTopLeft.whenPressed(new IntakeActuateDeploy(solenoidForward)); //deploy intake
		CDR_baseTopRight.whenPressed(new IntakeActuateDeploy(solenoidReverse)); //back
		CDR_baseMiddleLeft.whileHeld(new IntakeRun(-1.0)); //alt run cube in
		CDR_baseMiddleLeft.whenReleased(intakeStop); //stop intake
		CDR_baseMiddleRight.whileHeld(new IntakeRun(1.0)); //alt run cube out
		CDR_baseMiddleRight.whenReleased(intakeStop); //stop intake));
		
		//coDriver button box buttons
		CDB_green.whenPressed(new ElevatorResetEncoder());
	}
	
	//All the usefull functions
	public double desenitize(double val) {
		if (Math.abs(val) < 0.15) {
			val = 0;
		}
		return val;
	}
	
	public double getMove() {
		return desenitize(driver.getRawAxis(1));
	}
	
	public double getTurn() {
		return -desenitize(driver.getRawAxis(4));
	}
	
	public double getLiftControl() {
		return desenitize(coDriver.getRawAxis(1));
	}
}
