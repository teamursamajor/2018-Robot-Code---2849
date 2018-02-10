package org.usfirst.frc.team2849.diagnostics;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

import java.io.*;
import java.text.*;
import java.util.*;

import org.usfirst.frc.team2849.robot.Drive;
import org.usfirst.frc.team2849.robot.Subsystem;
/*
 * DESIGN:
 * 	private String path;
 * 	1. LOGGER:
 * 			LOGGER(string path): takes in path and writes to that file
 * 
 *  2. void log(string info, Level level) -- MM/DD/YYYY HH:MM:SS [LEVEL] <info>
 *  	- logs a new line of information with the above format by appending to the log file specified
 *  	  in the class
 *  	-   example: 01/19/2018 18:55:03 [ERROR] the robot has crashed into a wall :( 
 *  
 *  3. void initLogFile()
 *  3b. void initLogFile(string logStartStuff)
 */
//TODO open bufferedWriter on robotInit, global variable for log level
public class Logger extends Thread {

	private static String path; // the path in which the logger writes to
	boolean loop = true; //TODO find a use for this; it determines whether to loop the log or not
	int logPerSec = 1; //TODO change to whatever necessary; specifies how often to loop
	
	static Collection<Subsystem> subsystems;
	public Logger(Collection<Subsystem> c) {
		subsystems = c;
		start();
	}
	
	/**
	 * If the log is intended to loop, logs subsystem info logPerSec times every second.
	 * If the log is not intended to loop, logs subsystem info only once.
	 */
	public void run() {
		while (loop) {
			try {
				Thread.sleep(1/logPerSec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Subsystem s: subsystems) {			
				System.out.println(s.getLogData(getDate()));
				//write(s.getLogData(getDate()));
			}
			
		}
		//TODO find a use for this; intended to log subsystem info only once if loop boolean is set to false
		if (!loop) {
			for (Subsystem s: subsystems) {			
				write(s.getLogData(getDate()));
			}
		}
	}
	
	public static void initLogger(String p) {
		path = "/home/lvuser/" + p; // sets the path variable to the parameter
	}

	public static void initLogger() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
		Date date = new Date();
		path = "/home/lvuser/" + dateFormat.format(date);
		write("");
	}
	
	/**
	 * @return Date in the format MM/DD/YYYY HH:MM:SS (example: 01/19/2018 18:55:03)
	 */
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * Takes date, level of severity, and info and assembles it into a log output string
	 * @param info
	 * Info to be written in the log after the date and level
	 * @param lev
	 * Level of severity; can be LogLevel.DEBUG, LogLevel.ERROR, or LogLevel.INFO
	 * @return Log output in the format MM/DD/YYYY HH:MM:SS [LEVEL] <info>
	 */
	public static void log(String info, LogLevel lev) {
		String level = "";
		if (lev == LogLevel.DEBUG) {
			level = "DEBUG";
		} else if (lev == LogLevel.ERROR) {
			level = "ERROR";
		} else if (lev == LogLevel.INFO) {
			level = "INFO";
		}
		
		//TODO optimize? we probably don't need a separate prelog string
		//TODO maybe something like this: write(getDate() + " [" + level " "] " + info);
		String prelog = getDate() + " [" + level + "]";
		String output = prelog + " " + info;
		write(output);
	}
	
	public static void trial() {
		PowerDistributionPanel pdp = new PowerDistributionPanel();
		for (int i = 0; i < 16; i++) {
			log("Channel " + i + " " + pdp.getCurrent(i) + ":", LogLevel.INFO);
		}
		log("Temperature : " + pdp.getTemperature(), LogLevel.INFO);
		log("Total current : " + pdp.getTotalCurrent(), LogLevel.INFO);
		log("Total Energy : " + pdp.getTotalEnergy(), LogLevel.INFO);
		log("Total Power : " + pdp.getTotalPower(), LogLevel.INFO);
		log("Voltage : " + pdp.getVoltage(), LogLevel.INFO);
	}
	
	/**
	 * Level of severity of the log information.
	 * Used to specify severity for Logger.log method and getLogData method in subsystems
	 */
	public enum LogLevel {
		DEBUG, INFO, ERROR
	}
	
	/**
	 * Appends log output to log file specified in the class
	 * @param data
	 * Log output from Logger.log method or getLogData method in subsystems
	 */
	public static void write(String data) {
		File file = new File(path);
		FileWriter fileWrite = null;
		BufferedWriter buffWrite = null;

		try {
			fileWrite = new FileWriter(file, true);
			buffWrite = new BufferedWriter(fileWrite);
			buffWrite.write(data);
			buffWrite.append('\n');
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				buffWrite.close();
				fileWrite.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}