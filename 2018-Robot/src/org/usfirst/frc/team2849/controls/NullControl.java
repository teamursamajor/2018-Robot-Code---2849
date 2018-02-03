package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

public class NullControl implements ControlLayout {

	@Override
	public double getLeftSpeed() {
		return 0;
	}

	@Override
	public double getRightSpeed() {
		return 0;
	}

	@Override
	public void setSpeed(double leftPower, double rightPower) {}

	@Override
	public void setIntakeType(IntakeType type) {}

	@Override
	public IntakeType getIntakeType() {
		return IntakeType.STOP;
	}

	@Override
	public void setHasBox(boolean hasBox) {}

	@Override
	public boolean hasBox() {
		return false;
	}

	@Override
	public void setDesiredHeight(double liftHeight) {}

	@Override
	public double getDesiredHeight() {
		return 0;
	}

	@Override
	public void setCurrentHeight(double liftHeight) {}

	@Override
	public double getCurrentHeight() {
		return 0;
	}

}
