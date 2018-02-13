package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.path.Path;
import org.usfirst.frc.team2849.path.PointonPath;
import org.usfirst.frc.team2849.robot.Drive;

public class DriveTask extends AutoTask {
	private int distance;
	private Drive drive;
	private Path straightPath;
	
	public DriveTask(ControlLayout cont, int distance, Drive drive) {
		super(cont);
		this.distance = distance;
		this.drive = drive;
		this.straightPath = new Path("drivePath");
		straightPath.add(new PointonPath(0.0, drive.getRawHeading(), 0.0, 0.0, 0.0, 0.0, 0.0));
		straightPath.add(new PointonPath(distance, drive.getRawHeading(), 0, 0, 0, 0, 0));
		straightPath.createVelProfile();
		straightPath.mapVelocity();
	}

	public void run() {
		new PathTask(cont, new Path[] {straightPath, straightPath}, drive).start();		
		
		/*
		int count = 0;

		double leftPowerConstant = 0;
		double rightPowerConstant = 0;
		drive.resetEncoders();
		double rightAdjust = 0.0628d;
		Logger.log("Current Distance: " + distance, LogLevel.DEBUG);
		while (Math.abs(drive.getLeftEncoder()) < Math.abs(distance)
				|| Math.abs(drive.getRightEncoder()) < Math.abs(distance)) {
			
			leftPowerConstant = getPower(drive.getLeftEncoder(), distance);
			rightPowerConstant = getPower(drive.getRightEncoder(), distance);

			//Prints twice every second
			if (count % 25 == 0) {
				Logger.log("Left Power Constant: " + leftPowerConstant + "\tLeft Encoder: " + drive.getLeftEncoder(),
						LogLevel.DEBUG);
				Logger.log(
						"Right Power Constant: " + rightPowerConstant + "\tRight Encoder: " + drive.getRightEncoder(),
						LogLevel.DEBUG);
			}
			if (Math.abs(drive.getLeftEncoder()) > Math.abs(distance)) {
				leftPowerConstant = 0;
			}
			if (Math.abs(drive.getRightEncoder()) > Math.abs(distance)) {
				rightPowerConstant = 0;
			}
			//TODO hot fix change
			cont.getDrive().setSpeed(leftPowerConstant * -Math.signum(distance),
					((rightPowerConstant - rightAdjust) * -Math.signum(distance)));
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		Logger.log("Drive loop ended", LogLevel.DEBUG);
		cont.getDrive().setSpeed(0, 0); */
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
