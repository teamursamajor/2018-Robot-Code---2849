package org.usfirst.frc.team2849.diagnostics;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class DebugSelector {

	private SendableChooser<LogLevel> debugSelect = new SendableChooser<LogLevel>();
	
	public DebugSelector () {
		// TODO change default to debug disabled for comp
		debugSelect.addDefault("Enabled", LogLevel.DEBUG);
		debugSelect.addObject("Disabled", LogLevel.INFO);
	}
	
	public LogLevel getLevel() {
		if (debugSelect.getSelected().equals(LogLevel.DEBUG)) {
			System.out.println("Debug enabled");
			return LogLevel.DEBUG;
		} else {
			System.out.println("Debug disabled");
			return LogLevel.INFO;
		}
	}	
	
	public boolean isDebug() {
		if (getLevel().equals(LogLevel.DEBUG)) {
			return true;
		} else {
			return false;
		}
	}
	
/*	BROKEN CODE (needs to be worked on so we can have a toggle switch instead of dropdown in shuffleboard)
	------------------------------------------------------------------------------------------------------
	private NetworkButton debugSelect = new NetworkButton("Logging", "logging");
	
	private DebugCommand debugCommand = new DebugCommand();

	public DebugSelector() {
		System.out.println("constructed");
	//	debugCommand.initialize();
		debugCommand.setRunWhenDisabled(true);
		debugSelect.whenPressed(debugCommand);
		SmartDashboard.putData("Debug Logging", debugSelect);
	}

	public void initialize() {
		debugCommand.start();
		System.out.println("after command start " + debugCommand.isRunning());
	}
	public LogLevel getLevel() {

		return LogLevel.INFO;
		
	}

	public boolean isDebug() {
		if (getLevel().equals(LogLevel.DEBUG)) {
			return true;
		} else {
			return false;
		}
	}*/
}