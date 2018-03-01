package org.usfirst.frc.team2849.diagnostics;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class PDP extends Thread {
	
	PowerDistributionPanel pdp;
	
	public PDP() {
		pdp = new PowerDistributionPanel();
		start();
	}
	
	public void run() {
		while (!DriverStation.getInstance().isDisabled()) {
			for (int i = 0; i < 16; i++) {
				Logger.log("Channel " + i + " " + pdp.getCurrent(i) + ":", LogLevel.INFO);
			}
			Logger.log("Temperature : " + pdp.getTemperature(), LogLevel.INFO);
			Logger.log("Total current : " + pdp.getTotalCurrent(), LogLevel.INFO);
			Logger.log("Total Energy : " + pdp.getTotalEnergy(), LogLevel.INFO);
			Logger.log("Total Power : " + pdp.getTotalPower(), LogLevel.INFO);
			Logger.log("Voltage : " + pdp.getVoltage(), LogLevel.INFO);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Logger.log("PDP run method Thread.sleep call, printStackTrace", LogLevel.ERROR);
			}
		}
	}
}
