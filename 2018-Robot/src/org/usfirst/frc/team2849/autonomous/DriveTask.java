package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.AutoControl;
import org.usfirst.frc.team2849.robot.Drive;

public class DriveTask extends AutoTask {
	private int distance;
	

	public DriveTask(AutoControl cont, int distance) {
		super(cont);
		this.distance = -distance;
	}

	/** overshoots 3 in */
	public void run() {
		double leftPowerConstant = 0;
		double rightPowerConstant = 0;
		Drive.resetEncoders();
		while (Math.abs(Drive.getLeftEncoder()) < Math.abs(distance)
				&& Math.abs(Drive.getRightEncoder()) < Math.abs(distance)) {
			leftPowerConstant = getPower(Drive.getLeftEncoder(), distance);
			rightPowerConstant = getPower(Drive.getRightEncoder(), distance);
			if (Math.abs(Drive.getLeftEncoder()) > Math.abs(distance)) {
				leftPowerConstant = 0;
			}
			if (Math.abs(Drive.getRightEncoder()) > Math.abs(distance)) {
				rightPowerConstant = 0;
			}
			cont.setPower(leftPowerConstant * Math.signum(distance), rightPowerConstant * Math.signum(distance));
		}
		cont.setPower(0, 0);
		// Drive.stop();
	}

	public String toString() {
		return "DriveDistance: " + distance + "\n";
	}

	public double getPower(double currentDistance, double totalDistance) {
		currentDistance = Math.abs(currentDistance);
		totalDistance = Math.abs(totalDistance);
		if (currentDistance > totalDistance / 2)
			currentDistance = (totalDistance - currentDistance) - (totalDistance / 5);
		return (0.6 / (1 + Math.exp(5 - 0.18 * currentDistance))) + 0.3;

	}
}
