package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;

import edu.wpi.first.wpilibj.Spark;

public class Intake extends Thread {
	
	private static IntakeControl intake;
	private static Spark left;
	private static Spark right;
	
	public Intake(int channelLeft, int channelRight, ControlLayout cont) {
		left = new Spark(channelLeft);
		right = new Spark(channelRight);
		intake = cont.getIntake(left, right);
		this.start();
	}
	
	public void run() {
		
		while (true) {
			intake.runIntake();
			try {
				Thread.sleep(20); //because we all need breaks
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setControlScheme(ControlLayout cont) {
		intake = cont.getIntake(left, right);
	}
	
	public interface IntakeControl {
		
		public void runIntake();
		
	}

}
