package org.usfirst.frc.team2849.controls.drive;

public class NullDrive implements DriveControl {

	@Override
	public double getLeftSpeed() {
		return 0;
	}

	@Override
	public double getRightSpeed() {
		return 0;
	}

	@Override
	public void setSpeed(double leftPower, double rightPower) {
		
	}

}
