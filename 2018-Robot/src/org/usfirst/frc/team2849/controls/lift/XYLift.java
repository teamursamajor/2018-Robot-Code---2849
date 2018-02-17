package org.usfirst.frc.team2849.controls.lift;

import org.usfirst.frc.team2849.controls.XboxController;

public class XYLift implements LiftControl {

	private XboxController xbox;
	private boolean hasReached;
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
			return Integer.MIN_VALUE;
		}
		if (xbox.getButton(XboxController.BUTTON_Y)) {
			return Integer.MAX_VALUE;
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

	@Override
	public void setReached(boolean hasReached) {
		this.hasReached = hasReached;
	}

	@Override
	public boolean getReached() {
		return hasReached;
	}

}
