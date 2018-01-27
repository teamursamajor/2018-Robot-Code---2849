package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;

public class LiftTask extends AutoTask {
	
	private double liftHeight = 0;
	public LiftTask(ControlLayout cont, double height) {
		super(cont);
		liftHeight = height;
	}

	@Override
	public void run() {
		//TODO write this code
	}
	
	public String toString() {
		return "LiftTask: " + liftHeight + "\n";
	}
}
