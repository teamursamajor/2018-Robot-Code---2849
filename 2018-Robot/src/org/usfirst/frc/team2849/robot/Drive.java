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

	private static double leftSpeed;
	private static double rightSpeed;
	private static boolean square;
	private static AHRS ahrs;

	private static Boolean running = new Boolean(false);

	private static Encoder encL;
	private static Encoder encR;
	
	private static ControlLayout cont;
	
	private static final double INCHES_PER_TICK = 0.011505d;


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

		cont = contScheme;

		ahrs = new AHRS(SPI.Port.kMXP);

		encL = new Encoder(LEFT_ENCODER_CHANNEL_A, LEFT_ENCODER_CHANNEL_B);
		encR = new Encoder(RIGHT_ENCODER_CHANNEL_A, RIGHT_ENCODER_CHANNEL_B);

		encL.setDistancePerPulse(INCHES_PER_TICK);
		encR.setDistancePerPulse(INCHES_PER_TICK);
		encL.setReverseDirection(true);

		encL.reset();
		encR.reset();
		
		startDrive();
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
	//TODO PID
	public void run() {
		while (running) {
			mFrontLeft.set(cont.getLeftSpeed());
			mFrontRight.set(-cont.getRightSpeed());
			mRearLeft.set(cont.getLeftSpeed());
			mRearRight.set(-cont.getRightSpeed());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//TODO make all methods non static
	/**
	 * Kill method for driveThread
	 */
	public static void kill() {
		running = false;
	}

	/**
	 * Takes the angle given by the AHRS and turns it into a heading which is
	 * always between 0 and 360 (AHRS can return negative values and values
	 * above 360)
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
	
	public void setControlScheme(ControlLayout layout) {
		cont = layout;
	}

}
