package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;

import edu.wpi.first.wpilibj.Spark;

public class Lift extends Thread implements UrsaRobot {

	private static ControlLayout liftcont;
	private static Spark motor = new Spark(LIFT);

	public Lift(ControlLayout control) {
		liftcont = control;
		this.start();
	}

	public void run() {
		while (true) {
			//put code here
			liftcont.setCurrentHeight(getLiftHeight());
			if (liftcont.getDesiredHeight() != liftcont.getCurrentHeight()) {
				if (liftcont.getDesiredHeight() > liftcont.getCurrentHeight()) {
					motor.set(1);

				}
				if (liftcont.getDesiredHeight() < liftcont.getCurrentHeight())

				{
					motor.set(-1);

				}

			}

			if (liftcont.getDesiredHeight() == liftcont.getCurrentHeight()) {
				motor.set(0);
			}
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void setControlScheme(ControlLayout cont) {
		liftcont = cont;
	}

	private double getLiftHeight() {
		//TODO add encoder
		return 0;
	}

}