package org.daredevils2512.powerup.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

import org.daredevils2512.powerup.Robot;
import org.daredevils2512.powerup.RobotMap;
import org.daredevils2512.powerup.commands.Drive;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput {
	PIDController controller;
	double pidOutput;
	
    public Drivetrain() {
    	controller = new PIDController(0, 0, 0, Robot.m_navX, this);
	}

	// Put methods for controlling this subsystem


	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    
    public void driveRobotTank(double leftSpeed, double rightSpeed) {
    	RobotMap.chassis.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void driveRobotPID(double speed) {
    	RobotMap.chassis.tankDrive(speed + (pidOutput / 4d), speed + (pidOutput / 4d));
    }
    
    public void turnRobotPID(double speed) {
    	RobotMap.chassis.tankDrive(pidOutput * speed, pidOutput * speed);
    }
    
    public void driveRobotArcade(double move, double turn) {
    	RobotMap.chassis.arcadeDrive(move, turn);
    }
    
    public int getLeftEncoderValue() {
    	return RobotMap.leftEncoder.get();
    }
    
    public int getRightEncoderValue() {
    	return RobotMap.rightEncoder.get();
    }
    
    public void resetEncoders() {
    	RobotMap.leftEncoder.reset();
    	RobotMap.rightEncoder.reset();
    }
    
    public DoubleSolenoid.Value getShifterPos() {
    	return RobotMap.shifter.get();
    }
    
    public void shift(DoubleSolenoid.Value shiftPos) {
    	RobotMap.shifter.set(shiftPos);
    }

	@Override
	public void pidWrite(double output) {
		pidOutput = output;
	}
    
}

