package org.usfirst.frc.team2849.diagnostics;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.buttons.NetworkButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DebugSelector {

//	private SendableChooser<LogLevel> debugSelect = new SendableChooser<LogLevel>();
	private NetworkButton debugSelect2;

	public DebugSelector() {
		// // TODO change default to debug disabled for comp
		// debugSelect.addDefault("Enabled", LogLevel.DEBUG);
		// debugSelect.addObject("Disabled", LogLevel.INFO);
		// SmartDashboard.putData("Debug Logging", debugSelect);
		debugSelect2 = new NetworkButton("SmartDashboard", "Debug");
		SmartDashboard.putData("Debug", debugSelect2);
	}

// 	public LogLevel getLevel() {
// 		if (debugSelect.getSelected().equals(LogLevel.DEBUG)) {
// 			System.out.println("Debug enabled");
// 			return LogLevel.DEBUG;
// 		} else {
// 			System.out.println("Debug disabled");
// 			return LogLevel.INFO;
// 		}
// 	}

	public LogLevel getLevel() {
//		System.out.println(debugSelect2.get());
//		System.out.println(testGet());
		if (testGet()) {
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
	
	public Boolean testGet() {
		NetworkTableEntry m_entry;
		m_entry = SmartDashboard.getEntry("Debug");
//		System.out.println("is connected: " + m_entry.getInstance().isConnected());
//		System.out.println("with default false, getBoolean is: " + m_entry.getBoolean(false));
//		System.out.println("with default true, getBoolean is: " + m_entry.getBoolean(true));
		return m_entry.getInstance().isConnected();
//		return m_entry.getBoolean(true);
//		return m_entry.getInstance().isConnected() && m_entry.getBoolean(false);
	}
}