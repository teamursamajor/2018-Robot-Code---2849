package org.usfirst.frc.team2849.controls.intake;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.controls.XboxController;

public class BumperTriggerIntake implements IntakeControl {

	private XboxController xbox;
	
	public BumperTriggerIntake(XboxController xbox) {
		this.xbox = xbox;
	}
	
	@Override
	public void setIntakeType(IntakeType type) {}

	@Override
	public IntakeType getIntakeType() {
		if (xbox.getButton(XboxController.BUTTON_LEFTBUMPER)) {
			return IntakeType.IN;
		} else if (xbox.getButton(XboxController.BUTTON_RIGHTBUMPER)) {
			return IntakeType.OUT;
		} else if (xbox.getAxisGreaterThan(XboxController.AXIS_LEFTTRIGGER, .2)) {
			return IntakeType.RUN_IN;
		} else if (xbox.getAxisGreaterThan(XboxController.AXIS_RIGHTTRIGGER, .2)){
			return IntakeType.RUN_OUT;
		}
		return IntakeType.STOP;
	}

}
