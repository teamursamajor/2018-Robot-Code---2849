package org.usfirst.frc.team2849.robot;

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
//			cont.setHasBox(hasBox());
			//Run just keeps running, In/Out use the sensor
//			System.out.println(cont.getIntakeType());
//			System.out.println(cont.hasBox());
//			System.out.println("----");
			switch (cont.getIntakeType()) {
			case RUN:
				setIntakePower(.5);
				break;
			case OUT:
				if (cont.hasBox()) {
					setIntakePower(-0.5);
				} else {
					setIntakePower(0);
				}
				break;
			case STOP:
				setIntakePower(0);
				break;
			case HOLD:
				setIntakePower(.25);
			//TODO update to more complex intake (in, lift up, in again, lift down)
			//puts it on the front frame to keep the cube off the ground
			case IN:
				if(!cont.hasBox()){
					setIntakePower(0.5);
				} else {
					setIntakePower(0);
				}
				break;
			case RUN_IN:
				setIntakePower(0.5);
				break;
			case RUN_OUT:
				setIntakePower(-0.5);
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
		right.set(powerLevel);
	}
	//TODO add sensor
	public boolean hasBox(){
		return true;
	}
}
