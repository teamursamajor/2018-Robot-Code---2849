package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.controls.led.ColorsCheck;
import org.usfirst.frc.team2849.controls.led.ColorsLED;

import edu.wpi.first.wpilibj.DriverStation;
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
		boolean red = false;
		while (true) {
			//Determining the color here
			if(ColorsCheck.getStopLED()) {
				ColorsLED.setRed ();
			}
//			else if(ColorsCheck.getIntakeOutLED()) {
//				ColorsLED.setBlue ();
//			}
//			else if(ColorsCheck.getLiftDownLED()) {
//				ColorsLED.setYellow ();
//			}
//			else if(ColorsCheck.getMaxHeightLED()) {
//				ColorsLED.setPurple ();
//			}
//			else if(ColorsCheck.getLiftUpLED()) {
//				ColorsLED.setYellow ();
//			}
//			else if(ColorsCheck.getHaveCubeLED()) {
//				ColorsLED.setGreen ();
//			}
//			else if(ColorsCheck.getIntakeInLED()) {
//				ColorsLED.setBlue ();
//			}
//			else if(ColorsCheck.getMovingLED()) {
//				ColorsLED.setWhite ();
//			}
			else{
				ColorsLED.setNullColor();
			}
			
			
			//setting the LEDs here
			rLED.set(cont.getLED().getR());
			gLED.set(cont.getLED().getG());
			bLED.set(cont.getLED().getB());
		}
	}
	

	
}
