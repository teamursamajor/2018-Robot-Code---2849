package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;

public class IntakeTask extends AutoTask {
	
	public enum IntakeType{IN, OUT, RUN, STOP, UNTIL, DEPLOY};
	
	private IntakeType intake;
	public IntakeTask(ControlLayout cont, IntakeType intakeVal) {
		super(cont);
		intake = intakeVal;
	}
	
	public void run() {
		
	}
	
	public String toString() {
		return "IntakeTask: " + intake.name() + "\n";
	}
}
