package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;

public class IntakeTask extends AutoTask {

	private long timeout = 2;
	private long startTime;
	public enum IntakeType {
		IN, OUT, RUN, STOP, DEPLOY
	}

	private IntakeType intake;

	public IntakeTask(ControlLayout cont, IntakeType intakeVal) {
		super(cont);
		intake = intakeVal;
	}

	public void run() {
		cont.setIntakeType(intake);
		//Run just keeps running, In/Out use the sensor
		switch (intake) {
		case IN:
		    startTime = System.currentTimeMillis();
			while(!cont.hasBox() || System.currentTimeMillis() - startTime < timeout){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			cont.setIntakeType(IntakeType.STOP);
			break;
		case OUT:
			startTime = System.currentTimeMillis();
			while(cont.hasBox() || System.currentTimeMillis() - startTime < timeout){
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			cont.setIntakeType(IntakeType.STOP);
			break;
		default:
			System.out.println("Not In/Out Case :^)");
			break;
		}

	}

	public String toString() {
		return "IntakeTask: " + intake.name() + "\n";
	}
}
