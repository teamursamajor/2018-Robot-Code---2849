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
	
	public Logger(String p){
		path = "/home/lvuser/" + p; //sets the path variable to the parameter
	}
	public Logger() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
		Date date = new Date();
		path = "/home/lvuser" +dateFormat.format(date);
		write("");
	}
	
	//-- MM/DD/YYYY HH:MM:SS [LEVEL]- <info>
	 void log(String info, Level l) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String level = "";
		if(l == Level.DEBUG) {
			level = "DEBUG";
		}else if (l == Level.ERROR) {
			level = "ERROR";
		}else if (l == Level.INFO) {
			level = "INFO";
		}
		String prelog = dateFormat.format(date)+" ["+level+"]";
		
		String output = prelog + " " + info;
		write(output);
	}
	//
	void initLogFile() {
		
	}
	
	void initLogFile(String start) {
		
	}
	
	
	public void trial(){
		PowerDistributionPanel pdp = new PowerDistributionPanel();
		for (int i=0;i<16;i++){
			log("Channel "+i+" "+pdp.getCurrent(i) + ":", Level.INFO);
		}
		log("Temperature : "+pdp.getTemperature(),Level.INFO);
		log("Total current : "+pdp.getTotalCurrent(), Level.INFO);
		log("Total Energy : "+pdp.getTotalEnergy(), Level.INFO);
		log("Total Power : "+pdp.getTotalPower(), Level.INFO);
		log("Voltage : "+pdp.getVoltage(), Level.INFO);
	}
	
	enum Level{
		DEBUG,
		INFO,
		ERROR
	}
	

	void write(String data) {
		File file = new File(path);
		FileWriter fileWrite = null;
		BufferedWriter buffWrite = null;
		
		try {
			fileWrite = new FileWriter(file,true);
			buffWrite = new BufferedWriter(fileWrite);
			buffWrite.write(data);
			buffWrite.append('\n');
		}catch(IOException e) {
			e.printStackTrace();
			
		} finally {
			try {				
				buffWrite.close();
				fileWrite.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	


	
	public static void main(String[] args) {
	}
	
}
