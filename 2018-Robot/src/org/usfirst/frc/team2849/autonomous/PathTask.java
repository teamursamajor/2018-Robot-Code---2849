package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.path.Path;
import org.usfirst.frc.team2849.path.Pathfollower;
import org.usfirst.frc.team2849.robot.Drive;

import edu.wpi.first.wpilibj.DriverStation;

public class PathTask extends AutoTask {

	private Path leftPath;
	private Path rightPath;
	private Pathfollower follower;
	
	public PathTask(ControlLayout cont, Path[] paths) {
		super(cont);
		leftPath = paths[0];
		rightPath = paths[1];
		follower = new Pathfollower(0, 0, 0, 1.0/leftPath.getMaxVel(), 0, 0);
	}

	@Override
	public void run() {
		double leftPower;
		double rightPower;
		double steer;
		Drive.resetEncoders();
		long startTime = System.currentTimeMillis();
		long relTime = 0;
		while (!leftPath.isFinished() && !rightPath.isFinished() && !DriverStation.getInstance().isDisabled()) {
			relTime = System.currentTimeMillis() - startTime;
			Logger.log("In TaskLoop: " + relTime, LogLevel.DEBUG);
			Logger.log("Left: ", LogLevel.DEBUG);
			leftPower = follower.getCorrection(leftPath, Drive.getLeftEncoder(), relTime / 1000.0);
			Logger.log("Right: ", LogLevel.DEBUG);
			rightPower = follower.getCorrection(rightPath, Drive.getRightEncoder(), relTime / 1000.0);
			Logger.log("L: " + leftPower, LogLevel.DEBUG);
			Logger.log("R: " + rightPower, LogLevel.DEBUG);
			steer = follower.getSteering(leftPath.findNextPoint(relTime / 1000.0), Drive.getHeading());
			Logger.log("Steer: " + steer, LogLevel.DEBUG);
			cont.setPower(-leftPower + steer, -rightPower - steer);
			try {
				Thread.sleep((long) (leftPath.getDt() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Logger.log("End", LogLevel.DEBUG);
		Logger.log("Left Finished: " + leftPath.isFinished(), LogLevel.DEBUG);
		Logger.log("Right Finished: " + rightPath.isFinished(), LogLevel.DEBUG);
		cont.setPower(0, 0);
	}

}
