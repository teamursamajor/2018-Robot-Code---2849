package org.usfirst.frc.team2849.controls.led;

public class ColorsCheck {
	
	private static boolean stopCheck = false;
	private static boolean intakeInCheck = false;
	private static boolean intakeOutCheck = false;
	private static boolean liftUpCheck = false;
	private static boolean liftDownCheck = false;
	private static boolean haveCubeCheck = false;
	private static boolean maxHeightCheck = false;
	private static boolean movingCheck = false;
	
	public static void setStopLED(boolean bool) {
		stopCheck = bool;
	}
	public static void setIntakeInLED(boolean bool) {
		intakeInCheck = bool;
	}
	public static void setIntakeOutLED(boolean bool) {
		intakeOutCheck = bool;
	}
	public static void setLiftUpLED(boolean bool) {
		liftUpCheck = bool;
	}
	public static void setLiftDownLED(boolean bool) {
		liftDownCheck = bool;
	}
	public static void setHaveCubeLED(boolean bool) {
		haveCubeCheck = bool;
	}
	public static void setMaxHeightLED(boolean bool) {
		maxHeightCheck = bool;
	}
	public static void setMovingLED(boolean bool) {
		movingCheck = bool;
	}
	
}
