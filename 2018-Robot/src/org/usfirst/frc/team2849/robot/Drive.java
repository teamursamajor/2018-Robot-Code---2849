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
	
	/**
	 * Constructor for Drive class. Only one Drive object should be instantiated at any time.
	 * 
	 * @param frontLeft
	 * 			Channel number for front left motor
	 * @param frontRight
	 * 			Channel number for front right motor
	 * @param rearLeft
	 * 			Channel number for rear left motor
	 * @param rearRight
	 * 			Channel number for rear right motor
	 */
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
	
	/**
	 * External method for controlling motor speed. Checks and corrects speed inputs to be -1 <= speed <= 1.
	 * 
	 * @param leftSpeed
	 * 			Speed for left side motor controllers. Should be -1 <= leftSpeed <= 1.
	 * @param rightSpeed
	 * 			Speed for right side motor controllers. Should be -1 <= rightSpeed <= 1.
	 */
	public void drive(double leftSpeed, double rightSpeed) {
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		normalizeSpeed();
	}
	
	/**
	 * Changes the both leftSpeed and rightSpeed by some value.
	 * Does NOT set speed, only changes speed relative to the current value.
	 * Probably necessary for Pathfinder.
	 * 
	 * @param speed
	 * 		Amount to change both speeds.
	 */
	public void changeSpeed(double speed) {
		this.leftSpeed += speed;
		this.rightSpeed += speed;
		normalizeSpeed();
	}
	
	/**
	 * Method to check that both leftSpeed and rightSpeed are -1 < speed < 1, and sets them accordingly.
	 */
	public void normalizeSpeed() {
		if (Math.abs(leftSpeed) > 1) leftSpeed = Math.signum(leftSpeed) * 1;
		if (Math.abs(rightSpeed) > 1) rightSpeed = Math.signum(rightSpeed) * 1;
	}
	
	/**
	 * Starts driveThread. Made so that only one driveThread can exist at one time.
	 */
	private void startDrive() {
		synchronized (running) {
			if (running) return;
			running = true;
		}
		new Thread(this, "driveThread").start();
	}
	
	

	/**
	 * Run method for driveThread
	 */
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
	
	/**
	 * Kill method for driveThread
	 */
	public void kill() {
		running = false;
	}
    
}
