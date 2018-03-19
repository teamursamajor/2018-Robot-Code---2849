package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;

public abstract class AutoTask extends Thread {
	
	protected ControlLayout cont;
	
	public AutoTask(ControlLayout cont) {
		this.cont = cont;
	}
	
	public abstract void run();
}