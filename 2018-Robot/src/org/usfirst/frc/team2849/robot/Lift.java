package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class Lift extends Thread implements UrsaRobot {

	private static ControlLayout cont;
	private static Spark motor = new Spark(LIFT);
	Encoder liftEnc;
	
	public Lift(ControlLayout control) {
		cont = control;
		this.start();
		liftEnc  = new Encoder(UrsaRobot.LIFT_ENCODER_CHANNEL_A, UrsaRobot.LIFT_ENCODER_CHANNEL_B);
	}

	public void run() {
		double displacement = 0;
		double desiredHeight;
		double currentHeight;
		while (true) {
			cont.getLift().setCurrentHeight(getLiftHeight());
			desiredHeight = cont.getLift().getDesiredHeight();
			currentHeight = cont.getLift().getCurrentHeight();
			displacement = desiredHeight - currentHeight;
			if (desiredHeight != currentHeight) {
				if (desiredHeight > currentHeight) {
					motor.set(1);
				}
				if (desiredHeight < currentHeight) {
					motor.set(-.3);
				}
			}

			if (desiredHeight == currentHeight) {
				motor.set(.25);
			}
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setControlScheme(ControlLayout cont) {
		this.cont = cont;
	}

	private double getLiftHeight() {
		// TODO add encoder
		// put encoder code in a comment, needs to account for cumulative
		return 0;
	}

}