package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;

import edu.wpi.first.wpilibj.Solenoid;

public class LED extends Thread {

	private ControlLayout cont;
	private Solenoid  rLED;
	private Solenoid  gLED;
	private Solenoid  bLED;
	
	
	public LED(ControlLayout cont) {
		this.cont = cont;
		rLED = new Solenoid(0);
		gLED = new Solenoid(1);
		bLED = new Solenoid(2);
		this.start();
	}
	
	public void run() {
		while (true) {
			rLED.set(cont.getR());
			gLED.set(cont.getG());
			bLED.set(cont.getB());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setRGB(31,18,31);
		}
	}
	public void setRGB(int rPercent, int gPercent, int bPercent) {
		for(int n = 1; n<100;n++) {
			if(n == rPercent) {
				rLED.set(true);
			}
			if(n != rPercent) {
				rLED.set(false);
			}
			if(n == gPercent) {
				gLED.set(true);
			}
			if(n != gPercent) {
				gLED.set(false);
			}
			if(n == bPercent) {
				bLED.set(true);
			}
			if(n != bPercent) {
				bLED.set(false);
			}
		}
		
	}
	
	
	
	
}
