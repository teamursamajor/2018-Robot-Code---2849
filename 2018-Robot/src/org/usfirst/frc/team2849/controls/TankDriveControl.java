package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask;
import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.robot.Drive;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;
import org.usfirst.frc.team2849.robot.Intake.IntakeControl;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TankDriveControl extends XboxController implements ControlLayout {
	
	private double intakeValue;
	private double liftHeight;
	private double liftPower;

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
	public void runIntake() {
		if (super.getButton(BUTTON_A)) new IntakeTask(this, IntakeType.IN);
		else if (super.getButton(BUTTON_B)) new IntakeTask(this, IntakeType.OUT);
		else new IntakeTask(this, IntakeType.STOP);
	}

	@Override
	public void setPower(double leftPower, double rightPower) {
	}

	@Override
	public void setIntakeValue(double intakeValue) {
		this.intakeValue = intakeValue;
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
	public IntakeControl getIntake(Spark left, Spark right) {
		left.setInverted(true);
		return () -> { left.set(getIntakeValue()); right.set(getIntakeValue()); }; 
	}

	@Override
	public double getIntakeValue() {
		return intakeValue;
	}

}
