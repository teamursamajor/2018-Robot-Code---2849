package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class Lift extends Thread implements UrsaRobot {

	private static ControlLayout cont;
	private static Spark motor = new Spark(LIFT);
	Encoder liftEnc;

	public Lift(ControlLayout control) {
		cont = control;
		this.start();
		liftEnc = new Encoder(UrsaRobot.LIFT_ENCODER_CHANNEL_A, UrsaRobot.LIFT_ENCODER_CHANNEL_B);
	}

	public void run() {
		// TODO why does this exist
		double displacement = 0;
		double desiredHeight;
		double currentHeight;
		// acceptable error may need to be adjusted; change this value if so
		double acceptableRange = 2;
		while (true) {
			cont.getLift().setCurrentHeight(getLiftHeight());
			desiredHeight = cont.getLift().getDesiredHeight();
			currentHeight = cont.getLift().getCurrentHeight();
			// is this ever used for anything
			displacement = desiredHeight - currentHeight;

			/*
			 * why are we slower when rising than descending? arent we aided by
			 * gravity going down, and against it going up? We might want to
			 * look into adding an error/leeway here: ie if desiredHeight -
			 * currentHeight > .5 or something
			 */

			// if (desiredHeight - currentHeight < acceptableRange) {
			// motor.set(1);
			// } else if (desiredHeight - currentHeight < -1 * acceptableRange)
			// {
			// motor.set(-.3);
			// }
			if (desiredHeight == 1)
				motor.set(.75);
			else if (desiredHeight == -1)
				motor.set(-0.3);
			else if (desiredHeight == 0)
				motor.set(0);

			/*
			 * TODO I dont think this is correct. If we're at the bottom, we
			 * dont want the lift running Plus, I think for readability it
			 * should be currentHeight == desiredHeight
			 */

			// would desiredHeight ever actually equal currentHeight? we have to
			// consider error here
			// if (desiredHeight - currentHeight > acceptableRange &&
			// desiredHeight - currentHeight > -1 * acceptableRange)

			if (desiredHeight == currentHeight) {
				motor.set(.25);
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

	private double getLiftHeight() {
		// TODO return encoder or distance depending on current liftControl
		// liftEnc.getDistance();
		return 0;
	}
}