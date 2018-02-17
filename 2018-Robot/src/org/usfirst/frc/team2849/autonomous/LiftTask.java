package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
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

	@Override
	public void run() {
		cont.getLift().setDesiredHeight(height);
		long startTime = System.currentTimeMillis();
		while (!cont.getLift().getReached() || System.currentTimeMillis() - startTime < timeout) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

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