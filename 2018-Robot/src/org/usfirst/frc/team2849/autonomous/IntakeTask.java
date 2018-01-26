package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.autonomous.AutoTask;

public class IntakeTask implements AutoTask {
	
	public enum IntakeType{IN, OUT, RUN, STOP, UNTIL, DEPLOY};
	
	private IntakeType intake;
	public IntakeTask(IntakeType intakeVal) {
		intake = intakeVal;
	}

	@Override
	public void runTask() {
		//TODO write this code
	}
	
	public String toString() {
		return "IntakeTask: " + intake.name() + "\n";
	}
}
