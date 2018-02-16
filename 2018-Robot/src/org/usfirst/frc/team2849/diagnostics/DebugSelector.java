package org.usfirst.frc.team2849.diagnostics;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DebugSelector {

	private SendableChooser<Logger.LogLevel> debugSelect = new SendableChooser<Logger.LogLevel>();

	public DebugSelector() {
		// TODO change default to debug disabled for comp
		debugSelect.addDefault("Enabled", Logger.LogLevel.DEBUG);
		debugSelect.addObject("Disabled", Logger.LogLevel.INFO);

		SmartDashboard.putData("Debug Logging", debugSelect);
	}

	public LogLevel getLevel() {
		return debugSelect.getSelected();
	}

	public boolean isDebug() {
		if (getLevel().equals(LogLevel.DEBUG)) {
			return true;
		} else {
			return false;
		}
	}
}