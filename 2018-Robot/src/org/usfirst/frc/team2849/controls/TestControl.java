package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask;
import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;
import org.usfirst.frc.team2849.robot.Intake.IntakeControl;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TestControl extends XboxController implements ControlLayout {

	private double intakeValue;
	
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
	public void runIntake() {
		if (super.getButton(BUTTON_A)) new IntakeTask(this, IntakeType.IN);
		else if (super.getButton(BUTTON_B)) new IntakeTask(this, IntakeType.OUT);
		else new IntakeTask(this, IntakeType.STOP);
	}

	@Override
	public void setIntakeValue(double intakeValue) {
		this.intakeValue = intakeValue;
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
	public IntakeControl getIntake(Spark left, Spark right) {
		left.setInverted(true);
		return () -> { left.set(getIntakeValue()); right.set(getIntakeValue()); }; 
	}

	@Override
	public double getIntakeValue() {
		return intakeValue;		
	}

}
