package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.robot.Drive;

public class DriveTask implements AutoTask {
	private int distance;

	public DriveTask(int distance) {
		this.distance = -distance;
	}

	/** overshoots 3 in */
	public void runTask() {
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
			Drive.drive(leftPowerConstant * Math.signum(distance), rightPowerConstant * Math.signum(distance), false);
		}
		Drive.drive(0, 0, false);
		Drive.stop();
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
