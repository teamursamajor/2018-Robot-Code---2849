package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;

public class SerialTask extends GroupTask {
	public SerialTask(ControlLayout cont) {
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
