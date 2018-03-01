package org.usfirst.frc.team2849.controls.led;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.controls.ControlLayout;
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

	private static ControlLayout cont;

	public ColorsCheck(ControlLayout cont) {
		this.cont = cont;
	}

	public static boolean getStopLED() {
		//TODO broken
		if (!Drive.getRunning()) {
			stopCheck = true;
		} else {
			stopCheck = false;
		}
		return stopCheck;
	}

	public static boolean getIntakeInLED() {
		// TODO check if this is inwards or outwards
		if (cont.getIntake().getIntakeType().equals(IntakeType.IN)
				|| cont.getIntake().getIntakeType().equals(IntakeType.RUN_IN)) {
			intakeInCheck = true;
		} else {
			intakeInCheck = false;
		}
		return intakeInCheck;
	}

	public static boolean getIntakeOutLED() {
		// TODO check if this is inwards or outwards
		if ((cont.getIntake().getIntakeType().equals(IntakeType.OUT)
				|| (cont.getIntake().getIntakeType().equals(IntakeType.RUN_OUT)))) {
			intakeOutCheck = true;
		} else {
			intakeOutCheck = false;
		}
		return intakeOutCheck;
	}

	public static boolean getLiftUpLED() {
		if (cont.getLift().getDesiredHeight() > cont.getLift().getCurrentHeight()) {
			liftUpCheck = true;
			return liftUpCheck;
		}
		else {
			liftUpCheck = false;
			return liftUpCheck;
		}
	}

	public static boolean getLiftDownLED() {
		if (cont.getLift().getDesiredHeight() < cont.getLift().getCurrentHeight()) {
			liftDownCheck = true;
			return liftDownCheck;
		}
		else {
			liftDownCheck = false;
			return liftDownCheck;
		}
			
		
	}

	public static boolean getHaveCubeLED() {

		// TODO Please add some sensor eventually -sorry we wont

		return false;

	}

	public static boolean getMaxHeightLED() {
		// TODO include sensor for this

		return false;

	}

	public static boolean getMovingLED() {
		if (cont.getDrive().getRightSpeed() > 0.1 || cont.getDrive().getRightSpeed() < -.1
				|| cont.getDrive().getLeftSpeed() > 0.1 || cont.getDrive().getLeftSpeed() < -.1) {
			movingCheck = true;
		} else {
			movingCheck = false;
		}
		movingCheck = true;
		return movingCheck;
	}

}
