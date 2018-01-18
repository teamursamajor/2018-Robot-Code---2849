package org.usfirst.frc.team2849.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

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
}
