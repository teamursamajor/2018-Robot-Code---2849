package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;

import edu.wpi.first.wpilibj.Spark;

public class Lift extends Thread implements UrsaRobot, Subsystem {

	private static ControlLayout cont;
	private static Spark motor = new Spark(LIFT);

	public Lift(ControlLayout control) {
		cont = control;
		this.start();
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
					motor.set(.2);
				}
				if (desiredHeight < currentHeight){
					motor.set(-.5);
				}
			}

			if (desiredHeight == currentHeight) {
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
	
	/**
	 * Takes date and info and assembles it into a log output string for Lift subsystem
	 * @param date
	 * Used in Logger.run() method where it is substituted for Logger.getDate()
	 */
	public String getLogData(String date) {
		//TODO add any relevant information here
		return date + " [" + Logger.LogLevel.INFO + "] Lift: " + "...";
	}

}