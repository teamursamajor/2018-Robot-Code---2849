<<<<<<< HEAD
 package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.robot.Lift;

public class LiftTask extends AutoTask {

	private double height;

	// max time the lift can run for in ms
	private static long timeout = 3000;

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
			timeout = 0;
			return 0;
		case VAULT:
			timeout = 0;
			return 0;
		case SWITCH:
			timeout = 2000;
			return 20;
		case SCALE:
			timeout = 4000;
			return 75;
		default:
			return 0;
		}
	}
=======
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

	public static long presetToTimeout(LiftType liftPreset) {
		switch (liftPreset) {
		case BOTTOM:
			return 0;
		case VAULT:
			return 0;
		case SWITCH:
			return 2000;
		case SCALE:
			return 3400;
		default:
			return 0;
		}
	}
	
	public static double presetToHeight(LiftType liftPreset) {
		switch (liftPreset) {
		case BOTTOM:
			return 0;
		case VAULT:
			return 0;
		case SWITCH:
			return 20;
		case SCALE:
			return 75;
		default:
			return 0;
		}
	}
>>>>>>> 280f53619d2dde0b02e8f1d856dd7c771ab711e9
}