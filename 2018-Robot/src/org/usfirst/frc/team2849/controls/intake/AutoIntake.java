package org.usfirst.frc.team2849.controls.intake;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

public class AutoIntake implements IntakeControl {

	private IntakeType intakeType = IntakeType.STOP;
	
	//TODO hotfix
	private boolean hasBox = true;
	
	@Override
	public void setIntakeType(IntakeType type) {
		intakeType = type;
	}

	@Override
	public IntakeType getIntakeType() {
		return intakeType;
	}

	@Override
	public void setHasBox(boolean hasBox) {
		this.hasBox = hasBox;
	}

	@Override
	public boolean hasBox() {
		return hasBox;
	}

}
