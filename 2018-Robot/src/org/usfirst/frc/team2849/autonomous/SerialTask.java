package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.AutoControl;

public class SerialTask extends GroupTask {
	public SerialTask(AutoControl cont) {
		super(cont);
	}

	@Override
	public void run() {
		for (AutoTask t : tasks) {
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String toString() {
		return "--SerialTask:\n" + super.toString();
	}
}
