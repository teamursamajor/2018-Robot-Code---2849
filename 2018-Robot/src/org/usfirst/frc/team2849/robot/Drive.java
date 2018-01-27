package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

public class Drive implements Runnable, UrsaRobot{

	private static Spark mFrontLeft;
	private static Spark mFrontRight;
	private static Spark mRearLeft;
	private static Spark mRearRight;

	private static DriveControl drive;

	private static double leftSpeed;
	private static double rightSpeed;
	private static boolean square;
	private static AHRS ahrs;

	private static Boolean running = new Boolean(false);

	private static Encoder encL;
	private static Encoder encR;

	/**
	 * Constructor for Drive class. Only one Drive object should be instantiated
	 * at any time.
	 * 
	 * @param frontLeft
	 *            Channel number for front left motor
	 * @param frontRight
	 *            Channel number for front right motor
	 * @param rearLeft
	 *            Channel number for rear left motor
	 * @param rearRight
	 *            Channel number for rear right motor
	 */

	public Drive(int frontLeft, int frontRight, int rearLeft, int rearRight, ControlLayout contScheme) {
		mFrontLeft = new Spark(frontLeft);
		mFrontRight = new Spark(frontRight);
		mRearLeft = new Spark(rearLeft);
		mRearRight = new Spark(rearRight);

		drive = contScheme.getDrive(mFrontLeft, mFrontRight, mRearLeft, mRearRight);

		ahrs = new AHRS(SPI.Port.kMXP);

		encL = new Encoder(2, 3);
		encR = new Encoder(0, 1);

		double inchesPerTick = 0.011505d;
		encL.setDistancePerPulse(inchesPerTick);
		encR.setDistancePerPulse(inchesPerTick);

		encL.reset();
		encR.reset();

		startDrive();
	}

	/**
	 * External method for controlling motor speed. Checks and corrects speed
	 * inputs to be -1 <= speed <= 1.
	 * 
	 * @param leftSpeed
	 *            Speed for left side motor controllers. Should be -1 <=
	 *            leftSpeed <= 1.
	 * @param rightSpeed
	 *            Speed for right side motor controllers. Should be -1 <=
	 *            rightSpeed <= 1.
	 */
	public static void drive(double leftSpeed, double rightSpeed, boolean square) {
		Drive.leftSpeed = leftSpeed;
		Drive.rightSpeed = rightSpeed;
		Drive.square = square;
		normalizeSpeed();
	}

	/**
	 * Changes the both leftSpeed and rightSpeed by some value. Does NOT set
	 * speed, only changes speed relative to the current value. Probably
	 * necessary for Pathfinder.
	 * 
	 * @param speed
	 *            Amount to change both speeds.
	 */
	public static void changeSpeed(double speed) {
		leftSpeed += speed;
		rightSpeed += speed;
		normalizeSpeed();
	}

	/**
	 * Method to check that both leftSpeed and rightSpeed are -1 < speed < 1,
	 * and sets them accordingly.
	 */
	public static void normalizeSpeed() {
		if (Math.abs(leftSpeed) > 1)
			leftSpeed = Math.signum(leftSpeed) * 1;
		if (Math.abs(rightSpeed) > 1)
			rightSpeed = Math.signum(rightSpeed) * 1;
	}

	/**
	 * Starts driveThread. Made so that only one driveThread can exist at one
	 * time.
	 */
	private void startDrive() {
		synchronized (running) {
			if (running)
				return;
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
			drive.drive();
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
	public static void kill() {
		running = false;
	}

	/**
	 * Takes the angle given by the AHRS and turns it into a heading which is
	 * always between 0 and 360 (AHRS can return negative values and values
	 * above 360) TODO check for efficiency
	 */

	public static double getHeading() {
		double angle = ahrs.getAngle();
		angle = fixHeading(angle);
		return angle;
	}

	public static double fixHeading(double heading) {
		heading %= 360;
		if (heading < 0)
			heading += 360;
		return heading;
	}

	public static double getLeftEncoder() {
		return encL.getDistance();
	}

	public static double getRightEncoder() {
		return encR.getDistance();
	}

	public static void resetEncoders() {
		encL.reset();
		encR.reset();
	}

	public static void resetNavx() {
		ahrs.reset();
	}
	
	public static void stop(){
		mFrontLeft.stopMotor();
		mFrontRight.stopMotor();
		mRearLeft.stopMotor();
		mRearRight.stopMotor();
	}
	
	public static void setControlScheme(ControlLayout layout) {
		drive = layout.getDrive(mFrontLeft, mFrontRight, mRearLeft, mRearRight);
	}
	
	public interface DriveControl {
		
		public void drive();
		
	}
}
