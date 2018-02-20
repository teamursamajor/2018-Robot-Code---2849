package org.usfirst.frc.team2849.controls.lift;

public class AutoLift implements LiftControl {

	private double currentLiftHeight;
	private double desiredLiftHeight;
	private boolean hasReached;
	
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

	@Override
	public void setReached(boolean hasReached) {
		this.hasReached = hasReached;
	}

	@Override
	public boolean getReached() {
		return hasReached;
	}

}
