package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

public class WaitTask extends AutoTask {
	
	private long waitTime = 0;
	public WaitTask(ControlLayout cont, long time) {
		super(cont);
		waitTime = time;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Logger.log("WaitTask run method Thread.sleep call, printStackTrace", LogLevel.ERROR);
		}
	}
	
	public String toString() {
		return "WaitTask: " + waitTime + "\n";
	}
}
