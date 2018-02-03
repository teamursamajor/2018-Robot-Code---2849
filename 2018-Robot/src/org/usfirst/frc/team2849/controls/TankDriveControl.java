package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;
import org.usfirst.frc.team2849.robot.Lift.LiftControl;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TankDriveControl extends XboxController implements ControlLayout {

	private double liftHeight;
	private double liftPower;
	private boolean hasBox;

	public TankDriveControl(int port) {
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
	public DriveControl getDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight) {
		SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeft, rearLeft);
		SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRight, rearRight);
		DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
		return () -> {
			drive.tankDrive(getLeftPower(), getRightPower(), true);
		};
	}

	@Override
	public void setPower(double leftPower, double rightPower) {
	}

	@Override
	public void setLiftHeight(double liftHeight) {
		this.liftHeight = liftHeight;
	}

	@Override
	public double getLiftHeight() {
		return liftHeight;
	}

	@Override
	public void setLiftPower(double liftPower) {
		this.liftPower = liftPower;
	}

	@Override
	public double getLiftPower() {
		return liftPower;
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
	}

	@Override
	public IntakeType getIntakeType() {
		if (super.getButton(BUTTON_A)) {
			return IntakeType.IN;
		} else if (super.getButton(BUTTON_B)) {
			return IntakeType.OUT;
		}
		return IntakeType.STOP;
	}

	@Override
	public void setHasBox(boolean hasBox) {
		this.hasBox = hasBox;
	}

	@Override
	public boolean hasBox() {
		if (super.getButton(BUTTON_A)) {
			return false;
		} else if (super.getButton(BUTTON_B)) {
			return true;
		}
		return false;
	}

}
