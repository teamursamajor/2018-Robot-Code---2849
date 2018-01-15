package org.usfirst.frc.team2849.robot;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class DebugLog {
	
	private BufferedWriter log = null;
	private String file;
	//initialize the file destination and BufferedWriter in general
	public void debugLogInit(String fileDestination){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		
		try {
			file = fileDestination;
			FileWriter fw = new FileWriter(file);
			log = new BufferedWriter (fw);
			log.write("Debug log File on " + df.format(dateobj.getTime())  + " : ");
			log.newLine();
			}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void logWriteLn(String string) {
		try {
			log.write(string);
			log.newLine();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	

}
