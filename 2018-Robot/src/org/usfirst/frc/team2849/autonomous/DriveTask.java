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

	// double rightAdjust = 0.0628d;
	// double rightAdjust = 3 * -0.062d;
	double rightAdjust = 0;

	/** overshoots 3 in */
	public void run() {
		int count = 0;

		double leftPowerConstant = 0;
		double rightPowerConstant = 0;
		Drive.resetEncoders();
		Logger.log("Current Distance: " + distance, LogLevel.DEBUG);
		while (Math.abs(Drive.getLeftEncoder()) < Math.abs(distance)
				&& Math.abs(Drive.getRightEncoder()) < Math.abs(distance)) {
			leftPowerConstant = getPower(Drive.getLeftEncoder(), distance);
			rightPowerConstant = getPower(Drive.getRightEncoder(), distance);

			if (count % 10 == 0 || distance == -20) {
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
			cont.setPower((leftPowerConstant - rightAdjust * Math.signum(distance)) * -Math.signum(distance),
					(rightPowerConstant * -Math.signum(distance)));
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Logger.log("Drive loop ended", LogLevel.DEBUG);
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
