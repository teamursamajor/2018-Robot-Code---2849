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

	// TODO set this value
	private double inchesPerTick = 1.0d;

	// acceptable error may need to be adjusted; if so change this value
	private double acceptableRange = 2;

	public Lift(ControlLayout control) {
		cont = control;
		this.start();
		//liftEnc = new Encoder(LIFT_ENCODER_CHANNEL_A,LIFT_ENCODER_CHANNEL_B);
		//liftEnc.setDistancePerPulse(inchesPerTick);
	}

	public void run() {
		while (true) {
			cont.getLift().setCurrentHeight(getLiftHeight());
			desiredHeight = cont.getLift().getDesiredHeight();
			currentHeight = cont.getLift().getCurrentHeight();
			if (desiredHeight > currentHeight) {
				//UP
				motor.set(-1.0);
			} else if (desiredHeight < currentHeight) {
				//DOWN
				motor.set(0.75);
			} else {
				motor.set(0);
			}

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
				Logger.log("Lift run method Thread.sleep call, printStackTrace", LogLevel.ERROR);
			}
		}
	}

	public double getLiftHeight() {
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
		boolean hasReached = Math.abs(desiredHeight - currentHeight) < acceptableRange;
		cont.getLift().setReached(hasReached);
		return hasReached;
	}
}