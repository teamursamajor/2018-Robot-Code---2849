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
	public void setPower(double leftPower, double rightPower) {}

	@Override
	public double runIntake() {
		return 0;
	}

	@Override
	public void setIntakeValue(double intakeValue) {
		// TODO Auto-generated method stub
		
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
	public void setLiftPower(double liftPower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLiftPower() {
		// TODO Auto-generated method stub
		return 0;
	}

}