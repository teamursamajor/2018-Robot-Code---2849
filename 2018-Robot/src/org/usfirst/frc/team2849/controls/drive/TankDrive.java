package org.usfirst.frc.team2849.controls.drive;

import org.usfirst.frc.team2849.controls.XboxController;

public class TankDrive implements DriveControl {

	private XboxController xbox;
	
	public TankDrive(XboxController xbox) {
		this.xbox = xbox;
	}

	@Override
	public double getLeftSpeed() {
		double val = xbox.getAxis(XboxController.AXIS_LEFTSTICK_Y);
		return val * Math.abs(val);
	}

	@Override
	public double getRightSpeed() {
		double val = xbox.getAxis(XboxController.AXIS_RIGHTSTICK_Y);
		return val * Math.abs(val);
	}

	@Override
	public void setSpeed(double leftPower, double rightPower) {}

}
