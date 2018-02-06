package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

public class TestControl extends XboxController implements ControlLayout {

	private IntakeType intakeType;
	private boolean hasBox;

	public TestControl(int port) {
		super(port);
	}

	@Override
	public double getLeftSpeed() {
		return super.getAxis(AXIS_LEFTSTICK_Y);
	}

	@Override
	public double getRightSpeed() {
		return super.getAxis(AXIS_RIGHTSTICK_Y);
	}

	@Override
	public void setSpeed(double leftPower, double rightPower) {

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDesiredHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCurrentHeight(double liftHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getCurrentHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getR() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getG() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getB() {
		// TODO Auto-generated method stub
		return false;
	}

}
