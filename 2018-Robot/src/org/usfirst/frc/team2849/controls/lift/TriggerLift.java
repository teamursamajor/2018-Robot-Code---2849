/**
 * 
 */
package org.usfirst.frc.team2849.controls.lift;

import org.usfirst.frc.team2849.autonomous.LiftTask.*;
/**
 * @author kingeinstein
 *
 */
import org.usfirst.frc.team2849.controls.XboxController;

public class TriggerLift implements LiftControl {

	private XboxController xbox;
	private double currentHeight;
	private boolean hasReached;
	LiftType liftType;

	public TriggerLift(XboxController xbox, LiftType liftType) {
		this.xbox = xbox;
		this.liftType = liftType;
	}

	public int getTriggerValue() {
		if (xbox.getButton(XboxController.AXIS_LEFTTRIGGER)) {
			return Integer.MIN_VALUE;
		}
		if (xbox.getButton(XboxController.AXIS_RIGHTTRIGGER)) {
			return Integer.MAX_VALUE;
		} else {
			return 0;
		}
	}

	@Override
	public void setDesiredHeight(double liftHeight) {

	}

	@Override
	public double getDesiredHeight() {
		double desiredHeight;
		switch (liftType) {
		// ground level
		case BOTTOM:
			desiredHeight = 0;
			break;
		// vault height
		case VAULT:
			desiredHeight = 1.75;
			break;
		// switch height
		case SWITCH:
			desiredHeight = 20;
			break;
		// scale height
		case SCALE:
			desiredHeight = 75;
			break;
		// stays at same height
		default:
			desiredHeight = getCurrentHeight();
			break;
		}
		return desiredHeight;
	}

	@Override
	public void setCurrentHeight(double liftHeight) {
		currentHeight = liftHeight;
	}

	@Override
	public double getCurrentHeight() {
		return currentHeight;
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
