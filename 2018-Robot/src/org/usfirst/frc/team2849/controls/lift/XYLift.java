package org.usfirst.frc.team2849.controls.lift;

import org.usfirst.frc.team2849.controls.XboxController;

public class XYLift implements LiftControl {

	private XboxController xbox;
	private double currentLiftHeight;
	
	public XYLift(XboxController xbox) {
		this.xbox = xbox;
	}
	
	@Override
	public void setDesiredHeight(double liftHeight) {
	}

	@Override
	public double getDesiredHeight() {
		if (xbox.getButton(XboxController.BUTTON_X)) {
			return -1;
		}
		if (xbox.getButton(XboxController.BUTTON_Y)) {
			return 1;
		}
		return 0;
	}

	@Override
	public void setCurrentHeight(double liftHeight) {
		currentLiftHeight = liftHeight;
	}

	@Override
	public double getCurrentHeight() {
		return currentLiftHeight;
	}

}
