package org.usfirst.frc.team2849.controls.intake;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

public interface IntakeControl {
	
    public void setIntakeType(IntakeType type);
    
    public IntakeType getIntakeType();

	public void setHasBox(boolean hasBox);
	
	public boolean hasBox();

}
