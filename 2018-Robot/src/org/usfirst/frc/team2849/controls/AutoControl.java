package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;
import org.usfirst.frc.team2849.robot.Lift.LiftControl;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class AutoControl implements ControlLayout {
	
	private double leftPower;
	private double rightPower;
	private IntakeType intakeType;
	private boolean hasBox;
	private double liftHeight;
	private double liftPower;
	
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
