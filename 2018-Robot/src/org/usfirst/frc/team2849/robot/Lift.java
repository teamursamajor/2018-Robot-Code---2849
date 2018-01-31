package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;

import edu.wpi.first.wpilibj.Spark;

public class Lift extends Thread {

	private static Spark motor1;
	private static Spark motor2;
	public Lift(int channel1, int channel2, ControlLayout control){
		motor1 = new Spark(channel1);
		motor2 = new Spark(channel2);
		this.start();
	}

	public void run() {		
	}
	
	public interface LiftControl {
		
		public void runLift();
		
	}
	
}
