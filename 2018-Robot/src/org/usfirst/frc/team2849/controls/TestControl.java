package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;
import org.usfirst.frc.team2849.robot.Lift.LiftControl;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TestControl extends XboxController implements ControlLayout {

	private IntakeType intakeType;
	private boolean hasBox;

	public TestControl(int port) {
		super(port);
	}

	@Override
	public double getLeftPower() {
		return super.getAxis(AXIS_LEFTSTICK_Y);
	}

	@Override
	public double getRightPower() {
		return super.getAxis(AXIS_RIGHTSTICK_Y);
	}

	@Override
	public void setPower(double leftPower, double rightPower) {

	}

	@Override
	public DriveControl getDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight) {
		SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeft, rearLeft);
		SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRight, rearRight);
		leftSide.setInverted(true);
		rightSide.setInverted(true);
		DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
		return () -> {
			drive.tankDrive(getLeftPower(), getRightPower(), true);
		};
	}

	@Override
	public void setLiftPower(double liftPower) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getLiftPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLiftHeight(double liftHeight) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getLiftHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void runLift() {
		// TODO Auto-generated method stub

	}

	@Override
	public LiftControl getLift(Spark left, Spark right) {
		// TODO Auto-generated method stub
		return null;
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

}
