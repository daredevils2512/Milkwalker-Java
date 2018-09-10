/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.daredevils2512.powerup;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(1);
	public static WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(3);
	public static WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(2);
	public static WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(4);
	
	public static SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeftMotor, rearLeftMotor);
	public static SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRightMotor, rearRightMotor);
	public static DifferentialDrive chassis = new DifferentialDrive(leftSide, rightSide);
	
	public static Encoder leftEncoder = new Encoder(0, 1, false, CounterBase.EncodingType.k4X);
	public static Encoder rightEncoder = new Encoder(2, 3, true, CounterBase.EncodingType.k4X);
	public static double encoderDistancePerPulse = 0.0236065636;
	
	public static DoubleSolenoid shifter = new DoubleSolenoid(4, 5);
	
	public static WPI_TalonSRX intakeLeftMotor = new WPI_TalonSRX(6);
	public static WPI_TalonSRX intakeRightMotor = new WPI_TalonSRX(7);
	public static DoubleSolenoid intakeArmsSolenoid = new DoubleSolenoid(6, 7);
	public static DoubleSolenoid intakeDeploySolenoid = new DoubleSolenoid(0, 1);
	public static DigitalInput intakeLimitSwitch = new DigitalInput(5);
	
	public static WPI_TalonSRX frontElevator = new WPI_TalonSRX(5);
	public static WPI_TalonSRX rearElevator = new WPI_TalonSRX(8);
		
	public static DigitalInput elevatorLimitSwitch = new DigitalInput(4);
	
	public static AHRS navX = new AHRS(SPI.Port.kMXP);
	
	public static void Init() {
		frontElevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		leftEncoder.setDistancePerPulse(encoderDistancePerPulse);
		rightEncoder.setDistancePerPulse(encoderDistancePerPulse);
		
		rearElevator.setInverted(true);
	}
}
