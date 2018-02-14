/**
 * 
 */
package org.usfirst.frc.team2849.controls.lift;

/**
 * @author kingeinstein
 *
 */
import org.usfirst.frc.team2849.controls.XboxController;
import org.usfirst.frc.team2849.robot.UrsaRobot;

import edu.wpi.first.wpilibj.Encoder;

public class TriggerLift implements LiftControl {

	private XboxController xbox;
	private double desiredHeight;
	double currentHeight = 0;

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
		int height = 1;
		height += getTriggerValue();
		switch (height) {
		case 1:
			desiredHeight = 0;
			break;
		case 2:
			desiredHeight = 1.75;
			break;
		case 3:
			desiredHeight = 20;
			break;
		case 4:
			desiredHeight = 75;
			break;
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
