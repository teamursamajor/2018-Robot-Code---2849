package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.robot.Drive;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class AutoControl implements ControlLayout {
	
	private double leftPower;
	private double rightPower;
	
	public AutoControl() {
		leftPower = 0;
		rightPower = 0;
	}

	@Override
	public double getLeftPower() {
		return leftPower;
	}
	
	public void setPower(double leftPower, double rightPower) {
		this.leftPower = leftPower;
		this.rightPower = rightPower;
	}

	@Override
	public double getRightPower() {
		return rightPower;
	}

	@Override
	public DriveControl getDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight) {
		SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeft, rearLeft);
		SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRight, rearRight);
		DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);
		return () -> { drive.tankDrive(getLeftPower(), getRightPower(), false); };
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
