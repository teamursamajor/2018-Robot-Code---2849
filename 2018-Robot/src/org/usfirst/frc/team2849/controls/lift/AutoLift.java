package org.usfirst.frc.team2849.controls.lift;

public class AutoLift implements LiftControl {

	private double currentLiftHeight;
	private double desiredLiftHeight;
	
	@Override
	public void setDesiredHeight(double liftHeight) {
		this.desiredLiftHeight = liftHeight;
	}

	@Override
	public double getDesiredHeight() {
		return desiredLiftHeight;
	}

	@Override
	public void setCurrentHeight(double liftHeight) {
		this.currentLiftHeight = liftHeight;
	}

	@Override
	public double getCurrentHeight() {
		return currentLiftHeight;
	}

}
