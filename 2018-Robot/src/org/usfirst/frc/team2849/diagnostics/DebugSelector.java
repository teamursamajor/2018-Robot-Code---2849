package org.usfirst.frc.team2849.diagnostics;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.buttons.NetworkButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DebugSelector {

/*  FALLBACK CODE (since currently DebugSelector is a complete mess)
*   ----------------------------------------------------------------------------------------------
* 	private SendableChooser<Logger.LogLevel> debugSelect = new SendableChooser<Logger.LogLevel>();
* 
* 	public DebugSelector () {
* 		// TODO change default to debug disabled for comp
*   	debugSelect.addDefault("Enabled", Logger.LogLevel.DEBUG);
*		debugSelect.addObject("Disabled", Logger.LogLevel.INFO);
*	}
*	
*	public LogLevel getLevel() {
*		if (debugSelect.get()) {
*			System.out.println("Debug enabled");
*			return LogLevel.DEBUG;
*		} else {
*			System.out.println("Debug disabled");
*			return LogLevel.INFO;
*		}
*	}	
*	
*	public boolean isDebug() {
*		if (getLevel().equals(LogLevel.DEBUG)) {
*			return true;
*		} else {
*			return false;
*		}
*	}
*/
	private NetworkButton debugSelect = new NetworkButton("Logging", "logging");
	
	private DebugCommand debugCommand = new DebugCommand();

	public DebugSelector() {
		System.out.println("constructed");
//		debugCommand.initialize();
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
	}
}