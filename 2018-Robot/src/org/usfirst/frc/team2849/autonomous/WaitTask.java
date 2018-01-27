package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.AutoControl;

public class WaitTask extends AutoTask {
	
	private long waitTime = 0;
	public WaitTask(AutoControl cont, long time) {
		super(cont);
		waitTime = time;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "WaitTask: " + waitTime + "\n";
	}
}
