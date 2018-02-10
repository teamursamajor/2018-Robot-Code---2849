package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

public class TankDriveControl extends XboxController implements ControlLayout {

	private double currentLiftHeight = 0;
	private double desiredLiftHeight;
	private boolean hasBox;

	public TankDriveControl(int port) {
		super(port);
	}

	@Override
	public double getLeftSpeed() {
		double val = super.getAxis(AXIS_LEFTSTICK_Y);
		return val * Math.abs(val);
	}

	@Override
	public double getRightSpeed() {
		double val = super.getAxis(AXIS_RIGHTSTICK_Y);
		return val * Math.abs(val);
	}

	@Override
	public void setSpeed(double leftPower, double rightPower) {}

	@Override
	public void setIntakeType(IntakeType type) {}

	@Override
	public IntakeType getIntakeType() {
		if (super.getButton(BUTTON_LEFTBUMPER)) {
			return IntakeType.IN;
		} else if (super.getButton(BUTTON_RIGHTBUMPER)) {
			return IntakeType.OUT;
		} else if (super.getButton(AXIS_LEFTTRIGGER)) {
			return IntakeType.RUN_IN;
		} else if (super.getButton(AXIS_RIGHTTRIGGER)){
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
		if (super.getButton(BUTTON_A)) {
			return false;
		} else if (super.getButton(BUTTON_B)) {
			return true;
		}
		return false;
	}

	@Override
	public void setDesiredHeight(double liftHeight) {}

	@Override
	public double getDesiredHeight() {
		if (super.getButton(BUTTON_X)) {	
		return -1;
		}	
		if (super.getButton(BUTTON_Y)) {
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

	@Override
	public boolean getR() {
		return getRightSpeed() >= .2;
	}

	@Override
	public boolean getG() {
		return getLeftSpeed() >= .2;
	}

	@Override
	public boolean getB() {
		return hasBox();
	}

}
