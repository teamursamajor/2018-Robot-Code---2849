package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.AutoControl;

public class LiftTask extends AutoTask {
	
	private double liftHeight = 0;
	public LiftTask(AutoControl cont, double height) {
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
