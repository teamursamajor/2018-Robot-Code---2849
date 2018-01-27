package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.AutoControl;

public class IntakeTask extends AutoTask {
	
	public enum IntakeType{IN, OUT, RUN, STOP, UNTIL, DEPLOY};
	
	private IntakeType intake;
	public IntakeTask(AutoControl cont, IntakeType intakeVal) {
		super(cont);
		intake = intakeVal;
	}
	
	public void run() {
		
	}
	
	public String toString() {
		return "IntakeTask: " + intake.name() + "\n";
	}
}
