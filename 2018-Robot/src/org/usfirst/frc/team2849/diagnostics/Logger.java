package org.usfirst.frc.team2849.diagnostics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	/**
	 * Level of severity of the log information. Used to specify severity for
	 * Logger.log method and getLogData method in subsystems
	 */
	public enum LogLevel {
		ERROR, INFO, DEBUG
	}

	private static LogLevel level;
	private static File file;
	private static FileWriter fileWrite;
	private static BufferedWriter buffWrite;
	private static boolean writersOpened = false;

	/**
	 * @return Date in the format MM/DD/YYYY HH:MM:SS (example: 01/19/2018
	 *         18:55:03)
	 */
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM_dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Takes date, level of severity, and info and assembles it into a log
	 * output string
	 * 
	 * @param info
	 *            Info to be written in the log after the date and level
	 * @param lev
	 *            Level of severity; can be LogLevel.DEBUG, LogLevel.ERROR, or
	 *            LogLevel.INFO
	 * @return Log output in the format HH:MM:SS [LEVEL] <info>
	 */
	public static void log(String info, LogLevel lev, boolean writePrefix) {
		if (level.compareTo(lev) >= 0) {
			//TODO this toString might print incorrectly; in that case use ifs to set to a string
			if (writePrefix) {
				write(getTime() + " [" + lev.toString() + "] " + info, file);
			} else {
				write(info, file);
			}
		}
	}

	public static void log(String info, LogLevel lev) {
		log(info, lev, true);
	}

	/**
	 * Appends log output to log file specified in the class
	 * 
	 * @param data
	 *            Log output from Logger.log method or getLogData method in
	 *            subsystems
	 */
	public static synchronized void write(String data, File file) {
		if (!writersOpened) {
			openWriters();
		}
		try {
			buffWrite.write(data + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static LogLevel getLevel() {
		return level;
	}

	public static void setLevel(LogLevel lev) {
		level = lev;
		Logger.log("Logger Level: " + level, LogLevel.INFO);
	}

	public static void openWriters() {
		try {
			file = new File("/home/lvuser/" + getDate());
			fileWrite = new FileWriter(file, true);
			buffWrite = new BufferedWriter(fileWrite);
			writersOpened = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void closeWriters() {
		try {
			buffWrite.close();
			fileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writersOpened = false;
	}
}