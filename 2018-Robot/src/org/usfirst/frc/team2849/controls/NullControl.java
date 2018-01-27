package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.robot.Drive;
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
	public boolean runningIntakeIn() {
		return false;
	}

	@Override
	public boolean runningIntakeOut() {
		return false;
	}

	@Override
	public boolean runningElevatorUp() {
		return false;
	}

	@Override
	public boolean runningElevatorDown() {
		return false;
	}

}
