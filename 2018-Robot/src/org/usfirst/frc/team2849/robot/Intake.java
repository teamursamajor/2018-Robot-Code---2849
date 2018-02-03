package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.autonomous.LiftTask;
import org.usfirst.frc.team2849.autonomous.LiftTask.LiftType;
import org.usfirst.frc.team2849.controls.ControlLayout;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;

public class Intake extends Thread {
	
	private ControlLayout cont;
	private Spark left;
	private Spark right;
	private DigitalInput limitSwitch;
	
	public Intake(int channelLeft, int channelRight, ControlLayout cont) {
		left = new Spark(channelLeft);
		right = new Spark(channelRight);
		this.cont = cont;
		this.start();
	}
	
	public void run() {
		while (true) {
			cont.setHasBox(hasBox());
			//Run just keeps running, In/Out use the sensor
			switch (cont.getIntakeType()) {
			case RUN:
				setIntakePower(.5);
				break;
			case OUT:
				if (hasBox()) {
					setIntakePower(-0.5);
				} else {
					setIntakePower(0);
				}
				break;
			case STOP:
				setIntakePower(0);
				break;
			case IN:
				if(!hasBox()){
					setIntakePower(0.5);
				} else {
					setIntakePower(0);
				}
				break;
			default:
				setIntakePower(0);
				break;

			}
			try {
				Thread.sleep(20); //because we all need breaks
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setControlScheme(ControlLayout controller) {
		cont = controller;
	}

	public void setIntakePower(double powerLevel){ 
		//positive configuration
		left.set(powerLevel);
		right.set(-powerLevel);
	}
	//TODO add sensor
	public boolean hasBox(){
		return true;
	}
}
