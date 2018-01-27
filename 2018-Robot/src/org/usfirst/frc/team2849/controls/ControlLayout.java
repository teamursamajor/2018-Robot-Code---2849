package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.robot.Drive;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;

import edu.wpi.first.wpilibj.Spark;

public interface ControlLayout {
	
	public double getLeftPower();
	public double getRightPower();
	
	public DriveControl getDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight);
	
	public boolean runningIntakeIn();
	public boolean runningIntakeOut();
	
	public boolean runningElevatorUp();
	public boolean runningElevatorDown();

}