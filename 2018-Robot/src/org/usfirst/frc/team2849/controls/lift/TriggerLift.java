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
	private double desiredHeight;
	public int indexValue=0;
	double currentHeight = 0;
	LiftType heights[]={LiftType.BOTTOM,LiftType.VAULT,LiftType.SWITCH,LiftType.SCALE};
	public TriggerLift(XboxController xbox) {
		this.xbox = xbox;
	}
	
	public int getTriggerValue() {
		if (xbox.getButton(XboxController.AXIS_LEFTTRIGGER)) {
			return -1;
		}
		if (xbox.getButton(XboxController.AXIS_RIGHTTRIGGER)) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public void setDesiredHeight(double liftHeight) {
		// TODO Auto-generated method stub
		switch (heights[indexValue]) {
		//ground level
		case BOTTOM:
			desiredHeight =0;
			break;
		//vault height	
		case VAULT:
			desiredHeight = 1.75;
			break;
		//switch height
		case SWITCH:
			desiredHeight = 20;
			break;
		//scale height	
		case SCALE:
			desiredHeight = 75;
			break;
		//stays at same height	
		default:
			desiredHeight = getCurrentHeight();
			break;
		}
	}

	@Override
	public double getDesiredHeight() {
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

}
