package org.usfirst.frc.team2849.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

import java.io.*;
import java.text.*;
import java.util.*;
/*
 * DESIGN:
 * 	private String path;
 * 	1. LOGGER:
 * 			LOGGER(string path): takes in path and writes to that file
 * 
 *  2. void log(string info, Level level) -- MM/DD/YYYY HH:MM:SS [LEVEL] - <info>
 *  	- logs a new line of information with the above format by appending to the log file specified
 *  	  in the class
 *  	-   example: 01/19/2018 18:55:03 [ERROR] - the robot has crashed into a wall :( 
 *  
 *  3. void initLogFile()
 *  3b. void initLogFile(string logStartStuff)
 */

public class Logger {
	
	private static String path; //the path in which the logger writes to
	private BufferedWriter bw;
	private FileWriter fileWrite;
	
	public Logger(String p){
		path = p; //sets the path variable to the parameter
		try {
			fileWrite = new FileWriter(path);
			bw = new BufferedWriter(fileWrite);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Logger() {
		
	}
	
	//-- MM/DD/YYYY HH:MM:SS [LEVEL]- <info>
	static void log(String info, Level l) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String level = "";
		
		for(Level check : Level.values()) {
		}
		if(l == Level.DEBUG) {
			level = "DEBUG";
		}else if (l == Level.ERROR) {
			level = "ERROR";
		}else if (l == Level.INFO) {
			level = "INFO";
		}
		String prelog = dateFormat.format(date)+" ["+level+"]";
		
		String output = prelog + " " + info;
		write(path,output);
		
	}
	
	void initLogFile() {
		
	}
	
	void initLogFile(String start) {
		
	}
	
	
	public void trial(){
		PowerDistributionPanel pdp = new PowerDistributionPanel();
		for (int i=0;i<16;i++){
			System.out.println("Channel"+i+" "+pdp.getCurrent(i));
		}
		System.out.println("Temperature "+pdp.getTemperature());
		System.out.println("Total current "+pdp.getTotalCurrent());
		System.out.println("Total Energy "+pdp.getTotalEnergy());
		System.out.println("Total Power "+pdp.getTotalPower());
		System.out.println("Voltage "+pdp.getVoltage());
	}
	
	enum Level{
		DEBUG,
		INFO,
		ERROR
	}
	
	static void write(String filename, String aString){
		BufferedWriter bw = null;
		FileWriter fileWrite = null;
		try {

			fileWrite= new FileWriter(filename);
			bw = new BufferedWriter(fileWrite);
			bw.write(aString);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(bw!= null)
					bw.close();
				if(fileWrite!=null) {
					fileWrite.close();
				}
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	
	public static void main(String[] args) {
	}
	
}
