package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.AutoControl;

public abstract class AutoTask extends Thread {
	
	protected AutoControl cont;
	
	public AutoTask(AutoControl cont) {
		this.cont = cont;
	}
	
	public abstract void run();
}
