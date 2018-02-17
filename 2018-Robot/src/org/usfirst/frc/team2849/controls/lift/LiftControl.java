package org.usfirst.frc.team2849.controls.lift;

public interface LiftControl {
	
    public void setDesiredHeight(double liftHeight);
    
    public double getDesiredHeight();
    
    public void setCurrentHeight(double liftHeight);
    
    public double getCurrentHeight();

    public void setReached(boolean hasReached);
    
    public boolean getReached();
}
