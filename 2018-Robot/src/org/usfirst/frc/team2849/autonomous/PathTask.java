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
//		follower = new Pathfollower(1/50.0, 0, 1/200.0, 1.0/MAX_VELOCITY, 1/(2.5 * MAX_ACCELERATION), 1/100.0);
		follower = new Pathfollower(1/10.0, 0, 0, 1.0/MAX_VELOCITY, 1/(2.5 * MAX_ACCELERATION), 1/20.0);
		this.drive = drive;
	}

	@Override
	public void run() {
		double leftPower;
		double rightPower;
		double power;
		double steer;
		drive.resetNavx();
		drive.resetEncoders();
		long startTime = System.currentTimeMillis();
		long relTime = 0;
		long prevTime = 0;
		double averageDist = 0;
		while (!leftPath.isFinished() && !rightPath.isFinished() && !DriverStation.getInstance().isDisabled() && (System.currentTimeMillis() - startTime) / 1000.0 < leftPath.get(leftPath.numPoints() - 1).getTime() * 5) {
			relTime = System.currentTimeMillis() - startTime;
			Logger.log("In TaskLoop: " + relTime, LogLevel.DEBUG);
			Logger.log("Time since last loop: " + (relTime - prevTime) / 1000.0, LogLevel.DEBUG);
//			Logger.log("Left: ", LogLevel.DEBUG);
			prevTime = relTime;
			averageDist = (drive.getLeftEncoder() + drive.getRightEncoder()) / 2;
//			
//			System.out.println("Left: " + (relTime / 1000.0));
//			leftPower = follower.getCorrection(leftPath, drive.getLeftEncoder(), relTime / 1000.0);
//			Logger.log("Right: ", LogLevel.DEBUG);
//			System.out.println("Right: " + (relTime / 1000.0));
//			rightPower = follower.getCorrection(rightPath, drive.getRightEncoder(), relTime / 1000.0);
			power = follower.getCorrection(leftPath, averageDist, relTime / 1000.0);
			Logger.log("Power: " + power, LogLevel.DEBUG);
//			Logger.log("L: " + leftPower, LogLevel.DEBUG);
//			Logger.log("R: " + rightPower, LogLevel.DEBUG);
//			steer = follower.getSteering(leftPath.findNextPoint(relTime / 1000.0), drive.getRawHeading());
//			steer = follower.getDoubleSteering(leftPath.findNextPointTime(relTime / 1000.0), leftPath.get(leftPath.getIndex() + 1), drive.getRawHeading());
			steer = follower.getDoubleSteering(leftPath.findNextPointDist(averageDist), leftPath.get(leftPath.getIndex() + 1), drive.getRawHeading());
			Logger.log("Steer: " + steer, LogLevel.DEBUG);
			rightPath.findNextPointDist(averageDist);
			rightPath.findNextPointDist(averageDist);
//			rightPath.findNextPoint(relTime / 1000.0);
//			Logger.log("Steer: " + steer, LogLevel.DEBUG);
			cont.getDrive().setSpeed(-(power + steer), -(power - steer));
			try {
				Thread.sleep((long) (1000 * leftPath.getDt()));
			} catch (InterruptedException e) {
				e.printStackTrace();
				Logger.log("PathTask run method Thread.sleep call, printStackTrace", LogLevel.ERROR);
			}
		}
		System.out.println("end");
		System.out.println("Left end: " + leftPath.isFinished());
		System.out.println("Right end: " + rightPath.isFinished());
		Logger.log("End", LogLevel.DEBUG);
		Logger.log("Left Finished: " + leftPath.isFinished(), LogLevel.DEBUG);
		Logger.log("Right Finished: " + rightPath.isFinished(), LogLevel.DEBUG);
		cont.getDrive().setSpeed(0, 0);
		cont.getDrive().setSpeed(-.1, -.1);
		cont.getDrive().setSpeed(0, 0);
		drive.stop();
		startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < 3000) {}
		System.out.println("leftDist: " + drive.getLeftEncoder() );
		System.out.println("rightDist: " + drive.getRightEncoder());
	}

}
