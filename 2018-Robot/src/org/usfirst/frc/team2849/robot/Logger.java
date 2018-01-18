package org.usfirst.frc.team2849.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import java.io.*;


public class Logger {
	public Logger(){
		
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
	
	void write(String filename, String aString){
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

	
	
	
}
