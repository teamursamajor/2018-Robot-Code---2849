package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.autonomous.LiftTask.LiftType;
import org.usfirst.frc.team2849.autonomous.LiftTask.LiftType;
import org.usfirst.frc.team2849.controls.ControlLayout;
import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeTask extends AutoTask {

	public enum IntakeType {IN, OUT, RUN, STOP, DEPLOY}

	private IntakeType intake;

	public IntakeTask(ControlLayout cont, IntakeType intakeVal) {
		super(cont);
		intake = intakeVal;
	}

	public void run() {
		DigitalInput intakeBeam = new DigitalInput(0);
		switch (intake) {
		// Does not use Break Beam Sensor
		case RUN:
			cont.setIntakeValue(0.5);
			break;
		// Ejects box
		case OUT:
			while (!intakeBeam.get()) {
				cont.setIntakeValue(-0.5);
			}
			cont.setIntakeValue(0);
			break;
		// Stops all functions
		case STOP:
			cont.setIntakeValue(0);
			break;
		// Uses Break Beam
		case IN:
			while (intakeBeam.get()) {
				cont.setIntakeValue(0.5);
			}
			cont.setIntakeValue(0);
			break;
		case DEPLOY:
			LiftTask lift = new LiftTask(cont, 4.0, LiftType.VAULT);
			break;
		default:
			cont.setIntakeValue(0);
			break;

		}

	}

	public String toString() {
		return "IntakeTask: " + intake.name() + "\n";
	}
}
