package org.usfirst.frc.team2849.diagnostics;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LoggerSelector {
	
	private SendableChooser<Logger.LogLevel> selLogLevel = new SendableChooser<Logger.LogLevel>();
	
	public LoggerSelector() {
		selLogLevel.addDefault("DEBUG", Logger.LogLevel.DEBUG);
		selLogLevel.addObject("INFO", Logger.LogLevel.INFO);
		selLogLevel.addObject("ERROR", Logger.LogLevel.ERROR);
		
		SmartDashboard.putData("Logging level", selLogLevel);
	}
	
	public LogLevel getLevel() {
		return selLogLevel.getSelected();
	}
}