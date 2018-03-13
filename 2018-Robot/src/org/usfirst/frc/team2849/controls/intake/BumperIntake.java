package org.usfirst.frc.team2849.controls.intake;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.controls.XboxController;

public class BumperIntake implements IntakeControl {

	private XboxController xbox;
	
	public BumperIntake(XboxController xbox) {
		this.xbox = xbox;
	}
	
	@Override
	public void setIntakeType(IntakeType type) {}

	@Override
	public IntakeType getIntakeType() {
		if (xbox.getButton(XboxController.BUTTON_RIGHTBUMPER)) {
			return IntakeType.IN;
		} else if (xbox.getButton(XboxController.BUTTON_LEFTBUMPER)) {
			return IntakeType.OUT;
		}
		return IntakeType.STOP;
	}

}
