package org.daredevils2512.powerup.commands;

import org.daredevils2512.powerup.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorManualRun extends Command {

    public ElevatorManualRun() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//unless we're using the joystick, just apply a constant 10% power
    	//Doing this to correct backdriving of lift motor
    	if (Robot.m_oi.getLiftControl() == 0 && Robot.m_elevator.getLiftHeight() >= 0.0) { //3.1 feet
    		Robot.m_elevator.setSpeed(0.08); //0.1

    	//if lift is at the top don't let it run up any farther
    	} else if ((Robot.m_elevator.getLiftHeight() >= 7.2) && (Robot.m_oi.getLiftControl() < 0)) {
    		Robot.m_elevator.setSpeed(Robot.m_oi.getLiftControl());

    	//if we are trying to run down faster than 3/4 speed slow it down to max of 3/4
    	} else if ((Robot.m_elevator.getLiftHeight() >= 7.2)) {
    		Robot.m_elevator.setSpeed(0.0);

    	} else if (Robot.m_elevator.getLimitSwitchValue()) {
    		if (Robot.m_oi.getLiftControl() > 0) {
    			Robot.m_elevator.setSpeed(Robot.m_oi.getLiftControl());
    	} else {
    		Robot.m_elevator.setSpeed(0.0);
    	}

    	} else if (Robot.m_oi.getLiftControl() < -0.8) {
    		Robot.m_elevator.setSpeed(Robot.m_oi.getLiftControl() * 0.8); //0.5

    	//run lift off of joystick
    	} else if (Robot.m_oi.getLiftControl() != 0) {
    		Robot.m_elevator.setSpeed(Robot.m_oi.getLiftControl());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
