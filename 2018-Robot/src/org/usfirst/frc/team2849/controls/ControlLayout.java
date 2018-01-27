package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.robot.Drive;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;

import edu.wpi.first.wpilibj.Spark;

public interface ControlLayout {
	
	public double getLeftPower();
	public double getRightPower();
	
	public void setPower(double leftPower, double rightPower);
	
	public DriveControl getDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight);
	
	public double runIntake();
	
	public boolean runningElevatorUp();
	public boolean runningElevatorDown();
    public void setIntakeValue( double intakeValue);
}