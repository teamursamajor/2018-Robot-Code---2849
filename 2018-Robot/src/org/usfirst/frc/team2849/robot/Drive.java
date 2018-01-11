package org.usfirst.frc.team2849.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {
	
	private static Spark mFrontLeft;
	private static Spark mFrontRight;
	private static Spark mRearLeft;
	private static Spark mRearRight;
	private static SpeedControllerGroup leftSide;
	private static SpeedControllerGroup rightSide;
	private static DifferentialDrive diffDrive;
	
	public static void init() {
		mFrontLeft = new Spark(0);
		mFrontRight = new Spark(1);
		mRearLeft = new Spark(2);
		mRearRight = new Spark(3);
		
		leftSide = new SpeedControllerGroup(mFrontLeft, mRearLeft);
		rightSide = new SpeedControllerGroup(mFrontRight, mRearRight);
		
		diffDrive = new DifferentialDrive(leftSide, rightSide);
	}
	
	public static void drive(double leftSpeed, double rightSpeed) {
		diffDrive.tankDrive(leftSpeed, rightSpeed, true);
	}
    
}
