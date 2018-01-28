package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeTask extends AutoTask {
	
	public enum IntakeType{IN, OUT, RUN, STOP, UNTIL, DEPLOY};
	
	private IntakeType intake;
	public IntakeTask(ControlLayout cont, IntakeType intakeVal) {
		super(cont);
		intake = intakeVal;
	}
	public void run() {
		DigitalInput intakeBeam = new DigitalInput(0);
		DigitalInput shootBeam = new DigitalInput(1);
		switch(intake){
		case IN:
			cont.setIntakeValue(0.5);
			break;
		case OUT:
			cont.setIntakeValue(-0.5);
		    break;
		case STOP:
			cont.setIntakeValue(0);
			break;
		case RUN:
			while (intakeBeam.equals(0)){
				cont.setIntakeValue(0.5);
			}
			break;
		
		}
			
	}
	
	public String toString() {
		return "IntakeTask: " + intake.name() + "\n";
	}
}
