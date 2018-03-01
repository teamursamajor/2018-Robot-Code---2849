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
		
		/*
		 * Check ColorsCheck
		 * 
		 * LEDs turn on before while statement
		 * 
		 * AutoLED functions not working
		 * 
		 * See if ColorsCheck is always returning false
		 */
		
		
       while (true){
			//Determining the color here
			if(ColorsCheck.getStopLED() == true) {
				//ColorsLED.setRed ();
				rLED.set(true);
				gLED.set(false);
				bLED.set(false);
			}
			else if(ColorsCheck.getIntakeOutLED() == true) {
				//ColorsLED.setBlue ();
				rLED.set(false);
				gLED.set(false);
				bLED.set(true);
			}
			else if(ColorsCheck.getLiftDownLED() == true) {
				//ColorsLED.setYellow ();
				rLED.set(true);
				gLED.set(true);
				bLED.set(false);
			}
			else if(ColorsCheck.getMaxHeightLED() == true) {
				//ColorsLED.setPurple ();
				rLED.set(true);
				gLED.set(false);
				bLED.set(true);
			}
			else if(ColorsCheck.getLiftUpLED() == true) {
				//ColorsLED.setYellow ();
				rLED.set(true);
				gLED.set(true);
				bLED.set(false);
			}
			else if(ColorsCheck.getHaveCubeLED() == true) {
				//ColorsLED.setGreen ();
				rLED.set(false);
				gLED.set(true);
				bLED.set(false);
			}
			else if(ColorsCheck.getIntakeInLED() == true) {
				//ColorsLED.setBlue ();
				rLED.set(false);
				gLED.set(false);
				bLED.set(true);
			}
			else if(ColorsCheck.getMovingLED() == true) {
				//ColorsLED.setWhite ();
				rLED.set(true);
				gLED.set(true);
				bLED.set(true);
			}
			else{
				System.out.println("ELSE STATEMENT RAN");
//				ColorsLED.setNullColor();
//				ColorsLED.setBlue();
			}
			//setting the LEDs here
			rLED.set(cont.getLED().getR());
            gLED.set(cont.getLED().getG());
			bLED.set(cont.getLED().getB());
			

			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	

	

}