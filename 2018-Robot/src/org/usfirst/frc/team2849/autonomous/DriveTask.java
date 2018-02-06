package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.robot.Drive;

public class DriveTask extends AutoTask {
	private int distance;

	public DriveTask(ControlLayout cont, int distance) {
		super(cont);
		this.distance = distance;
	}

	public void run() {
		int count = 0;

		double leftPowerConstant = 0;
		double rightPowerConstant = 0;
		Drive.resetEncoders();
		double rightAdjust = 0.0628d;
		Logger.log("Current Distance: " + distance, LogLevel.DEBUG);
		while (Math.abs(Drive.getLeftEncoder()) < Math.abs(distance)
				|| Math.abs(Drive.getRightEncoder()) < Math.abs(distance)) {
			
			leftPowerConstant = getPower(Drive.getLeftEncoder(), distance);
			rightPowerConstant = getPower(Drive.getRightEncoder(), distance);

			//Prints twice every second
			if (count % 25 == 0) {
				Logger.log("Left Power Constant: " + leftPowerConstant + "\tLeft Encoder: " + Drive.getLeftEncoder(),
						LogLevel.DEBUG);
				Logger.log(
						"Right Power Constant: " + rightPowerConstant + "\tRight Encoder: " + Drive.getRightEncoder(),
						LogLevel.DEBUG);
			}
			if (Math.abs(Drive.getLeftEncoder()) > Math.abs(distance)) {
				leftPowerConstant = 0;
			}
			if (Math.abs(Drive.getRightEncoder()) > Math.abs(distance)) {
				rightPowerConstant = 0;
			}
			//TODO hot fix change
			cont.setSpeed(leftPowerConstant * -Math.signum(distance),
					((rightPowerConstant - rightAdjust) * -Math.signum(distance)));
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Logger.log("Drive loop ended", LogLevel.DEBUG);
		cont.setSpeed(0, 0);
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
