package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.path.Path;
import org.usfirst.frc.team2849.path.Pathfollower;
import org.usfirst.frc.team2849.robot.Drive;
import org.usfirst.frc.team2849.robot.UrsaRobot;

import edu.wpi.first.wpilibj.DriverStation;

public class PathTask extends AutoTask implements UrsaRobot {

	private Path leftPath;
	private Path rightPath;
	private Pathfollower follower;
	private Drive drive;
	
	public PathTask(ControlLayout cont, Path[] paths, Drive drive) {
		super(cont);
		leftPath = paths[0];
		rightPath = paths[1];
		follower = new Pathfollower(1/100.0, 0, 0, 1.0/MAX_VELOCITY, 1/100.0, 0);
		this.drive = drive;
	}

	@Override
	public void run() {
		double leftPower;
		double rightPower;
		double steer;
		drive.resetEncoders();
		long startTime = System.currentTimeMillis();
		long relTime = 0;
		while (!leftPath.isFinished() && !rightPath.isFinished() && !DriverStation.getInstance().isDisabled()) {
			relTime = System.currentTimeMillis() - startTime;
//			Logger.log("In TaskLoop: " + relTime, LogLevel.DEBUG);
//			Logger.log("Left: ", LogLevel.DEBUG);
			System.out.println("Left: " + (relTime / 1000.0));
			leftPower = follower.getCorrection(leftPath, drive.getLeftEncoder(), relTime / 1000.0);
//			Logger.log("Right: ", LogLevel.DEBUG);
			System.out.println("Right: " + (relTime / 1000.0));
			rightPower = follower.getCorrection(rightPath, drive.getRightEncoder(), relTime / 1000.0);
//			Logger.log("L: " + leftPower, LogLevel.DEBUG);
//			Logger.log("R: " + rightPower, LogLevel.DEBUG);
			steer = follower.getSteering(leftPath.findNextPoint(relTime / 1000.0), drive.getHeading());
			rightPath.findNextPoint(relTime / 1000.0);
//			Logger.log("Steer: " + steer, LogLevel.DEBUG);
			cont.setSpeed(-leftPower + steer, -rightPower - steer);
			try {
				Thread.sleep((long) (leftPath.getDt() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("end");
		System.out.println("Left end: " + leftPath.isFinished());
		System.out.println("Right end: " + rightPath.isFinished());
		Logger.log("End", LogLevel.DEBUG);
		Logger.log("Left Finished: " + leftPath.isFinished(), LogLevel.DEBUG);
		Logger.log("Right Finished: " + rightPath.isFinished(), LogLevel.DEBUG);
		cont.setSpeed(0, 0);
		drive.stop();
	}

}
