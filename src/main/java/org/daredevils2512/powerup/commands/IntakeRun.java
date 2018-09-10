package org.daredevils2512.powerup.commands;

import org.daredevils2512.powerup.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeRun extends Command {
	private double m_speed = 0.0;
	
	//1.0 is in, -1.0 is out
	
    public IntakeRun(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_intake);
    	m_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_intake.setIntakeSpeed(m_speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_intake.setIntakeSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
