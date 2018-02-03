package org.usfirst.frc.team2849.controls;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

public interface ControlLayout {
	
	public double getLeftSpeed();
	public double getRightSpeed();

	//not the drug
	public void setSpeed(double leftPower, double rightPower);

	//Intake methods
    public void setIntakeType(IntakeType type);
    
    public IntakeType getIntakeType();

	public void setHasBox(boolean hasBox);
	
	public boolean hasBox();
    
    //Lift Methods
    public void setDesiredHeight(double liftHeight);
 
    public double getDesiredHeight();
    
    public void setCurrentHeight(double liftHeight);
    
    public double getCurrentHeight();
    
    //LED Methods
    public boolean getR();
    public boolean getG();
    public boolean getB();
    
    
}