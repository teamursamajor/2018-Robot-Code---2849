package org.usfirst.frc.team2849.controls.drive;

public class AutoDrive implements DriveControl {

	private double leftPower;
	private double rightPower;

	public AutoDrive() {
		leftPower = 0;
		rightPower = 0;
	}

	@Override
	public double getLeftSpeed() {
		return leftPower;
	}

	@Override
	public double getRightSpeed() {
		return rightPower;

	}

	public void setSpeed(double leftPower, double rightPower) {
		this.leftPower = leftPower;
		this.rightPower = rightPower;
	}

}