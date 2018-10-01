package org.daredevils2512.powerup.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavX extends Subsystem implements PIDSource {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private PIDSourceType sourceType = PIDSourceType.kDisplacement;
	public AHRS navx;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		sourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}

	@Override
	public double pidGet() {
		// TODO Attach to NavX
		return 0;
	}
}

