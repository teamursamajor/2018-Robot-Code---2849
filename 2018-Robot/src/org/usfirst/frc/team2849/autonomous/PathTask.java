package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.path.Path;

public class PathTask extends AutoTask {

	private Path leftPath;
	private Path rightPath;
	
	public PathTask(ControlLayout cont, Path[] paths) {
		super(cont);
		leftPath = paths[0];
		rightPath = paths[1];
	}

	@Override
	public void run() {
		while (!leftPath.isFinished() && !rightPath.isFinished()) {
			
		}
	}

}
