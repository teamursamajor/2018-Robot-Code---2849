package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;

import edu.wpi.first.wpilibj.Spark;

public class NullControl implements ControlLayout {

	@Override
	public double getLeftPower() {
		return 0;
	}

	@Override
	public double getRightPower() {
		return 0;
	}

	@Override
	public DriveControl getDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight) {
		return () -> {};
	}

	@Override
	public void setPower(double leftPower, double rightPower) {}

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
