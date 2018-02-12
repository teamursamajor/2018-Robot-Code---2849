package org.usfirst.frc.team2849.controls.intake;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.controls.XboxController;

public class BumperTriggerIntake implements IntakeControl {

	private XboxController xbox;
	@SuppressWarnings("unused")
	private boolean hasBox;
	
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

	@Override
	public void setHasBox(boolean hasBox) {
		this.hasBox = hasBox;
	}

	//TODO update to use a sensor
	@Override
	public boolean hasBox() {
		if (xbox.getButton(XboxController.BUTTON_LEFTBUMPER)) {
			return false;
		} else if (xbox.getButton(XboxController.BUTTON_RIGHTBUMPER)) {
			return true;
		}
		return false;
	}

}
