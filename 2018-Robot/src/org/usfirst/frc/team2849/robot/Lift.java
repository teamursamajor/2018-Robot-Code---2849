package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class Lift extends Thread implements UrsaRobot {

	private ControlLayout cont;
	private static Spark motor = new Spark(LIFT);
	Encoder liftEnc;

	private double desiredHeight;
	private double currentHeight;
	// acceptable error may need to be adjusted; if so change this value
	private double acceptableRange = 2;

	public Lift(ControlLayout control) {
		cont = control;
		this.start();
		liftEnc = new Encoder(UrsaRobot.LIFT_ENCODER_CHANNEL_A, UrsaRobot.LIFT_ENCODER_CHANNEL_B);
	}

	public void run() {
		while (true) {
			cont.getLift().setCurrentHeight(getLiftHeight());
			desiredHeight = cont.getLift().getDesiredHeight();
			currentHeight = cont.getLift().getCurrentHeight();
			if (checkReached()) {
				motor.set(.25);
			} else if (desiredHeight > currentHeight) {
				motor.set(.75);
			} else if (desiredHeight < currentHeight) {
				motor.set(-0.3);
			} else {
				motor.set(0);
			}

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
				Logger.log("Lift.java thread.sleep call, printStackTrace", LogLevel.ERROR);
			}
		}
	}

	public void setControlScheme(ControlLayout cont) {
		this.cont = cont;
	}

	public double getLiftHeight() {
//		return liftEnc.getDistance();
		return 0;
	}

	/**
	 * Tells cont if we are within some amount of our desiredHeight and returns
	 * true or false.
	 * 
	 * @return Returns true if we are within the acceptableRange, returns false
	 *         otherwise
	 */
	public boolean checkReached() {
		boolean hasReached = desiredHeight - currentHeight < acceptableRange
				|| currentHeight - desiredHeight < acceptableRange;
		cont.getLift().setReached(hasReached);
		return hasReached;

	}
}