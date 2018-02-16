package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

public class ParallelTask extends GroupTask {
	public ParallelTask(ControlLayout cont) {
		super(cont);
	}
	
	@Override
	public void run() {
		for(AutoTask t:tasks) {
			t.start();
		}
		for(AutoTask t:tasks) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Logger.log("ParallelTask run method join thread printStackTrace", LogLevel.ERROR);
			}
		}
	}
	
	public String toString() {
		return "--ParallelTask:\n" + super.toString();
	}
}
