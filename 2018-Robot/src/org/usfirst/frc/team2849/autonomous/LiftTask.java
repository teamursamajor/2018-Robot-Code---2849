package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;

public class LiftTask extends AutoTask {

	public enum LiftType {
		BOTTOM, VAULT, SWITCH, SCALE
	}

	private LiftType lift;

	public LiftTask(ControlLayout cont, double height, LiftType liftPreset) {
		super(cont);
	}

	@Override
	public void run() {
		
	}

	public String toString() {
		return "LiftTask: " + cont.getLift().getDesiredHeight() + "\n";
	}
}