package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.robot.Drive.DriveControl;
import org.usfirst.frc.team2849.robot.Lift.LiftControl;

import edu.wpi.first.wpilibj.Spark;

public interface ControlLayout {
	
	public double getLeftPower();
	public double getRightPower();
	
	public void setPower(double leftPower, double rightPower);
	
	public DriveControl getDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight);
	
	//Intake methods	
    public void setIntakeType(IntakeType type);
    
    public IntakeType getIntakeType();

	public void setHasBox(boolean hasBox);
	
	public boolean hasBox();
    
    //Lift Methods   
    public LiftControl getLift(Spark left, Spark right);
    
    public void runLift();
    
    public void setLiftPower(double liftPower);
    
    public double getLiftPower();
    
    public void setLiftHeight(double liftHeight);
 
    public double getLiftHeight();
    
}