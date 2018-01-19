package org.usfirst.frc.team2849.autonomous;;

import org.usfirst.frc.team2849.autonomous.AutoTask;

public class LiftTask implements AutoTask {
	
	private double liftHeight = 0;
	public LiftTask(double height) {
		liftHeight = height;
	}

	@Override
	public void runTask() {
		//TODO write this code
	}
	
	public String toString() {
		return "LiftTask: " + liftHeight + "\n";
	}
}
