package org.usfirst.frc.team2849.controls.drive;

import org.usfirst.frc.team2849.controls.XboxController;

public class ArcadeDrive implements DriveControl {

	private XboxController xbox;
	private double leftSpeed;
	private double rightSpeed;
	private boolean squared;
	
	public ArcadeDrive(XboxController xbox, boolean squared) {
		this.xbox = xbox;
		this.squared = squared;
	}
	
	@Override
	public double getLeftSpeed() {
		updateSpeeds();
		return leftSpeed;
	}

	@Override
	public double getRightSpeed() {
		updateSpeeds();
		return rightSpeed;
	}

	@Override
	public void setSpeed(double leftPower, double rightPower) {}

	private void updateSpeeds() {
		double leftStickY = squared ? xbox.getSquaredAxis(XboxController.AXIS_LEFTSTICK_Y) : -xbox.getAxis(XboxController.AXIS_LEFTSTICK_Y);
		double rightStickX = squared ? -xbox.getSquaredAxis(XboxController.AXIS_RIGHTSTICK_X) : -xbox.getAxis(XboxController.AXIS_RIGHTSTICK_X);
		leftSpeed = leftStickY + rightStickX;
		rightSpeed = leftStickY - rightStickX;
		double max = Math.max(leftSpeed, rightSpeed);
		double min = Math.min(leftSpeed, rightSpeed);
		if (max > 1) {
			leftSpeed /= max;
			rightSpeed /= max;
		} else if (min < -1) {
			leftSpeed /= -min;
			rightSpeed /= -min;
		}
	}
	
}
