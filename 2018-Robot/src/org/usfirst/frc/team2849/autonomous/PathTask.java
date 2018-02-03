package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
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
			System.out.println("In TaskLoop: " + relTime);
			System.out.println("Left: ");
			leftPower = follower.getCorrection(leftPath, Drive.getLeftEncoder(), relTime / 1000.0);
			System.out.println("Right: ");
			rightPower = follower.getCorrection(rightPath, Drive.getRightEncoder(), relTime / 1000.0);
			System.out.println("L: " + leftPower);
			System.out.println("R: " + rightPower);
			steer = follower.getSteering(leftPath.findNextPoint(relTime / 1000.0), Drive.getHeading());
			System.out.println("Steer: " + steer);
			cont.setPower(-leftPower + steer, -rightPower - steer);
			try {
				Thread.sleep((long) (leftPath.getDt() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("End");
		System.out.println("Left Finished: " + leftPath.isFinished());
		System.out.println("Right Finished: " + rightPath.isFinished());
		cont.setPower(0, 0);
	}

}
