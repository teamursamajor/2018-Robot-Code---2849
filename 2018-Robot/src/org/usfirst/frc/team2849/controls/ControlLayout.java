package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.controls.drive.DriveControl;
import org.usfirst.frc.team2849.controls.intake.IntakeControl;
import org.usfirst.frc.team2849.controls.led.LEDControl;
import org.usfirst.frc.team2849.controls.lift.LiftControl;

public class ControlLayout {
	
	private DriveControl drive;
	private IntakeControl intake;
	private LiftControl lift;
	private LEDControl led;
	
	public ControlLayout(DriveControl drive, IntakeControl intake, LiftControl lift, LEDControl led) {
		this.setDrive(drive);
		this.setIntake(intake);
		this.setLift(lift);
		this.setLed(led);
	}

	public DriveControl getDrive() {
		return drive;
	}

	public void setDrive(DriveControl drive) {
		this.drive = drive;
	}

	public IntakeControl getIntake() {
		return intake;
	}

	public void setIntake(IntakeControl intake) {
		this.intake = intake;
	}

	public LiftControl getLift() {
		return lift;
	}

	public void setLift(LiftControl lift) {
		this.lift = lift;
	}

	public LEDControl getLED() {
		return led;
	}

	public void setLed(LEDControl led) {
		this.led = led;
	}
	
	public void updateControlLayout(DriveControl drive, IntakeControl intake, LiftControl lift, LEDControl led) {
		this.setDrive(drive);
		this.setIntake(intake);
		this.setLift(lift);
		this.setLed(led);
	}
}