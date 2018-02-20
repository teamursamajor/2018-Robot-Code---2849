package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;

public class Intake extends Thread {

	private ControlLayout cont;
	private Spark left;
	private Spark right;
	private DigitalInput limitSwitch;

	//To hold the power of Intake for debugging purposes
	private static double intakePower = 0.0;
	
	public Intake(int channelLeft, int channelRight, ControlLayout cont) {
		left = new Spark(channelLeft);
		right = new Spark(channelRight);
		this.cont = cont;
		this.start();
	}

	public void run() {
		while (true) {
			switch (cont.getIntake().getIntakeType()) {
			case RUN:
				setIntakePower(1);
				break;
			case OUT:
				setIntakePower(-1);
				break;
			case STOP:
				setIntakePower(0);
				break;
			case HOLD:
				setIntakePower(.25);
				// TODO update to more complex intake (in, lift up, in again,
				// lift down)
				// puts it on the front frame to keep the cube off the ground
			case IN:
				setIntakePower(0.5);
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
				Thread.sleep(20); // because we all need breaks
			} catch (InterruptedException e) {
				e.printStackTrace();
				Logger.log("Intake run method Thread.sleep call, printStackTrace", LogLevel.ERROR);
			}
		}
	}

	public void setControlScheme(ControlLayout controller) {
		cont = controller;
	}

	public void setIntakePower(double powerLevel) {
		// positive configuration
		left.set(powerLevel);
		right.set(powerLevel);
		
		//Hold powerLevel value to debug
		intakePower = powerLevel;
	}
	public static double getIntakePower() {
		return intakePower;
	}

}
