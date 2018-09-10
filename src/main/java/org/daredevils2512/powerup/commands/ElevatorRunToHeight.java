package org.daredevils2512.powerup.commands;

import org.daredevils2512.powerup.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorRunToHeight extends Command {
	double m_height, m_speed;
	
    public ElevatorRunToHeight(double height, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_elevator);
    	m_height = height;
    	m_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Seeing if we neeed to blast off
    	if (m_height > Robot.m_elevator.getLiftHeight()) {
    		Robot.m_elevator.setSpeed(m_speed);
    	//or fall down
    	} else if (m_height < Robot.m_elevator.getLiftHeight()) {
    		Robot.m_elevator.setSpeed(m_speed);
    	//or just sit there cause someone goofed
    	} else {
    		Robot.m_elevator.setSpeed(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.m_elevator.doubleInTolerance(m_height, Robot.m_elevator.getLiftHeight(), 4.0));
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
