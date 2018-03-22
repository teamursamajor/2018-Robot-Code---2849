 package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.robot.Lift;

public class LiftTask extends AutoTask {

	private double height;

	// max time the lift can run for in ms
	private long timeout = 3000;

	public enum LiftType {
		BOTTOM, VAULT, SWITCH, SCALE
	}

	public LiftTask(ControlLayout cont, double height) {
		super(cont);
		this.height = height;
	}
	
	public LiftTask(ControlLayout cont, double height, long timeout) {
		super(cont);
		this.height = height;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		Logger.log("Running lift task", LogLevel.INFO);

		cont.getLift().setDesiredHeight(height);
		long startTime = System.currentTimeMillis();
		while (!cont.getLift().getReached() && System.currentTimeMillis() - startTime < timeout) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		cont.getLift().setDesiredHeight(cont.getLift().getCurrentHeight());

	}

	public String toString() {
		return "LiftTask: " + cont.getLift().getDesiredHeight() + "\n";
	}

	public static double presetToHeight(LiftType liftPreset) {
		switch (liftPreset) {
		case BOTTOM:
			return 0;
		case VAULT:
			return 1.75;
		case SWITCH:
			return 20;
		case SCALE:
			return 75;
		default:
			return 0;
		}
	}
}