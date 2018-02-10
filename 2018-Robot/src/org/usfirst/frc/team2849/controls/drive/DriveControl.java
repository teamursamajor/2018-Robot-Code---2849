package org.usfirst.frc.team2849.controls.drive;

public interface DriveControl {
	
	public double getLeftSpeed();
	public double getRightSpeed();

	public void setSpeed(double leftPower, double rightPower);

}
