package org.usfirst.frc.team2849.controls.lift;

public class NullLift implements LiftControl {

	@Override
	public void setDesiredHeight(double liftHeight) {

	}

	@Override
	public double getDesiredHeight() {
		return 0;
	}

	@Override
	public void setCurrentHeight(double liftHeight) {

	}

	@Override
	public double getCurrentHeight() {
		return 0;
	}

}
