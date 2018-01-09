package org.usfirst.frc.team2849.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive implements Runnable {
	
	private Spark mFrontLeft;
	private Spark mFrontRight;
	private Spark mRearLeft;
	private Spark mRearRight;
	
	private SpeedControllerGroup leftSide;
	private SpeedControllerGroup rightSide;
	
	private DifferentialDrive diffDrive;
	
	private double leftSpeed;
	private double rightSpeed;
	
	private static Boolean running = new Boolean(false);
	
	public Drive(int frontLeft, int frontRight, int rearLeft, int rearRight) {
		mFrontLeft = new Spark(frontLeft);
		mFrontRight = new Spark(frontRight);
		mRearLeft = new Spark(rearLeft);
		mRearRight = new Spark(rearRight);
		
		leftSide = new SpeedControllerGroup(mFrontLeft, mRearLeft);
		rightSide = new SpeedControllerGroup(mFrontRight, mRearRight);
		
		diffDrive = new DifferentialDrive(leftSide, rightSide);
		
		startDrive();
	}
	
	public void drive(double leftSpeed, double rightSpeed) {
		if (Math.abs(leftSpeed) > 1) leftSpeed = 1;
		if (Math.abs(rightSpeed) > 1) rightSpeed = 1;
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
	}
	
	private void startDrive() {
		synchronized (running) {
			if (running) return;
			running = true;
		}
		new Thread(this, "driveThread").start();
	}

	@Override
	public void run() {
		while (running) {
			diffDrive.tankDrive(leftSpeed, rightSpeed, true);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill() {
		running = false;
	}
    
}
