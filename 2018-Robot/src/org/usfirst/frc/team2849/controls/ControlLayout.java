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
	
    public void setIntakeValue( double intakeValue);
    
    public void setLiftPower(double liftPower);
    
    public double getLiftPower();
    
    public void setLiftHeight(double liftHeight);
    
    /**
     * 
     * @return lift height in inches
     */
    public double getLiftHeight();
}