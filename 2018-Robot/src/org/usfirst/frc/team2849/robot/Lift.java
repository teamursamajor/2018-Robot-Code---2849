package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;

import edu.wpi.first.wpilibj.Spark;

public class Lift extends Thread implements UrsaRobot {

	private static ControlLayout cont;
	private static Spark motor = new Spark(LIFT);

	public Lift(ControlLayout control) {
		cont = control;
		this.start();
	}

	public void run() {
		double displacement = 0;
		while (true) {
			cont.setCurrentHeight(getLiftHeight());
			displacement = cont.getDesiredHeight() - cont.getCurrentHeight();
			if (cont.getDesiredHeight() != cont.getCurrentHeight()) {
				if (cont.getDesiredHeight() > cont.getCurrentHeight()) {
					motor.set(.2);
				}
				if (cont.getDesiredHeight() < cont.getCurrentHeight()){
					motor.set(-.5);
				}
			}

			if (cont.getDesiredHeight() == cont.getCurrentHeight()) {
				motor.set(0);
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
		return 0;
	}

}