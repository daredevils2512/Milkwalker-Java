package org.daredevils2512.powerup;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class TriggerButton extends Button {
	Joystick m_joystick;
	int m_axis;
	
	public TriggerButton(Joystick joystick, int axis) {
		m_joystick = joystick;
		m_axis = axis;
	}
	
	public boolean get() {
		return (m_joystick.getRawAxis(m_axis) > 0);
	}
}
