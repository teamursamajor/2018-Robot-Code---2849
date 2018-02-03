package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

public class AutoControl implements ControlLayout {
	
	private double leftPower;
	private double rightPower;
	private IntakeType intakeType = IntakeType.STOP;
	
	//TODO hotfix
	private boolean hasBox = true;
	
	private double currentLiftHeight;
	private double desiredLiftHeight;
	
	public AutoControl() {
		leftPower = 0;
		rightPower = 0;
	}

	@Override
	public double getLeftSpeed() {
		return leftPower;
	}
	
	public void setSpeed(double leftPower, double rightPower) {
		this.leftPower = leftPower;
		this.rightPower = rightPower;
	}

	@Override
	public double getRightSpeed() {
		return rightPower;
	}

	@Override
	public void setIntakeType(IntakeType type) {
		intakeType = type;
	}

	@Override
	public IntakeType getIntakeType() {
		return intakeType;
	}

	@Override
	public void setHasBox(boolean hasBox) {
		this.hasBox = hasBox;
	}

	@Override
	public boolean hasBox() {
		return hasBox;
	}

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
