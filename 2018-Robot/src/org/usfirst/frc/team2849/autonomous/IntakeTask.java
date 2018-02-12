package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

public class IntakeTask extends AutoTask {

	private long timeout = 2000;
	private long startTime;
	public enum IntakeType {
		IN, OUT, RUN, STOP, DEPLOY, HOLD, RUN_IN, RUN_OUT
	}

	private IntakeType intake;

	public IntakeTask(ControlLayout cont, IntakeType intakeVal) {
		super(cont);
		intake = intakeVal;
	}

	public void run() {
		cont.getIntake().setIntakeType(intake);
		//Run just keeps running, In/Out use the sensor
		switch (intake) {
		case IN:
		    startTime = System.currentTimeMillis();
			while(!cont.getIntake().hasBox() && System.currentTimeMillis() - startTime < timeout){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			cont.getIntake().setIntakeType(IntakeType.STOP);
			break;
		case OUT:
			startTime = System.currentTimeMillis();
			while(cont.getIntake().hasBox() && System.currentTimeMillis() - startTime < timeout){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			cont.getIntake().setIntakeType(IntakeType.STOP);
			break;
		default:
			Logger.log("Intake in " + intake.name() + " case :^)", LogLevel.DEBUG);
			break;
		}

	}

	public String toString() {
		return "IntakeTask: " + intake.name() + "\n";
	}
}
