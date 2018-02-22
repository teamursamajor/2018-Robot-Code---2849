package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

public class SerialTask extends GroupTask {
	public SerialTask(ControlLayout cont) {
		super(cont);
	}

	@Override
	public void run() {
		Logger.log("Running serial task", LogLevel.INFO);

		for (AutoTask t : tasks) {
			t.run();
		}
	}

	public String toString() {
		return "--SerialTask:\n" + super.toString();
	}
}
