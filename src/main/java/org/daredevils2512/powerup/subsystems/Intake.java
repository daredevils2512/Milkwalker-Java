package org.daredevils2512.powerup.subsystems;

import org.daredevils2512.powerup.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean getLimitSwitchValue() {
    	return RobotMap.intakeLimitSwitch.get();
    }
    
    public void actuateArms(DoubleSolenoid.Value actuateDir) {
    	RobotMap.intakeArmsSolenoid.set(actuateDir);
    }
    
    public void setIntakeSpeed(double speed) {
    	RobotMap.intakeLeftMotor.set(speed);
    	RobotMap.intakeRightMotor.set(-speed);
    }
    
    public void actuateDeploy(DoubleSolenoid.Value actuateDir) {
    	RobotMap.intakeDeploySolenoid.set(actuateDir);
    }
}

