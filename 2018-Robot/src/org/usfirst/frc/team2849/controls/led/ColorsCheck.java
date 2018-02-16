package org.usfirst.frc.team2849.controls.led;

import org.usfirst.frc.team2849.robot.Drive;

public class ColorsCheck {

	private static boolean stopCheck = false;
	private static boolean intakeInCheck = false;
	private static boolean intakeOutCheck = false;
	private static boolean liftUpCheck = false;
	private static boolean liftDownCheck = false;
	private static boolean haveCubeCheck = false;
	private static boolean maxHeightCheck = false;
	private static boolean movingCheck = false;

	public static boolean getStopLED() {
		if(!Drive.getRunning()){
			stopCheck = true;
		}
		else{
			stopCheck = false;
		}
		return stopCheck;
	}

	public static boolean getIntakeInLED() {
		
	}

	public static boolean getIntakeOutLED() {

	}

	public static boolean getLiftUpLED() {

	}

	public static boolean getLiftDownLED() {

	}

	public static boolean getHaveCubeLED() {

	}

	public static boolean getMaxHeightLED() {

	}

	public static boolean getMovingLED() {
		if (cont.getDrive().getRightSpeed() > 0.1 || cont.getDrive().getRightSpeed() < -.1
				|| cont.getDrive().getLeftSpeed() > 0.1 || cont.getDrive().getLeftSpeed() < -.1	) {
			movingCheck = true;
		} else {
			movingCheck = false;
		}
		return movingCheck;
	}

}
