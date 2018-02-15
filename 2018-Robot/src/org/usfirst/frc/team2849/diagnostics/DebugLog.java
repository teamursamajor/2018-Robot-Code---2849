package org.usfirst.frc.team2849.diagnostics;

import java.util.Date;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DebugLog {
	private DebugSelector debugSelect = new DebugSelector();
	private BufferedWriter log = null;
	private String file;

	// initialize the file destination and BufferedWriter in general
	public void debugLogInit(String fileDestination) {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		//Only runs this if the current Logger level is debug
		if (debugSelect.isDebug()) {
			try {
				file = fileDestination;
				FileWriter fw = new FileWriter(file);
				log = new BufferedWriter(fw);
				log.write("Debug log file on " + df.format(dateobj.getTime()) + " : ");
				log.newLine();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				Logger.log("DebugLog debugLogInit printStackTrace", LogLevel.ERROR);
			}
		}
	}

	public void logWriteLn(String string) {
		try {
			log.write(string);
			log.newLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			Logger.log("DebugLog logWriteLn printStackTrace", LogLevel.ERROR);
		}

	}

}
