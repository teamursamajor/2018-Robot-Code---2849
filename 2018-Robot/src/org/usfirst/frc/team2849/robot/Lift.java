package org.usfirst.frc.team2849.robot;

public class Lift extends Thread {

	public Lift() {
		this.start();
	}

	public void run() {
		
	}
	
	public interface LiftControl {
		
		public void runLift();
		
	}
	
}
