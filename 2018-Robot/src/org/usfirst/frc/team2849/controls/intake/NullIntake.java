package org.usfirst.frc.team2849.controls.intake;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

public class NullIntake implements IntakeControl {

	@Override
	public void setIntakeType(IntakeType type) {

	}

	@Override
	public IntakeType getIntakeType() {
		return IntakeType.STOP;
	}

}
