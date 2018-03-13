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
			case IN:
				setIntakePower(0.5);
				break;
			case RUN_IN:
				setIntakePower(0.5);
				break;
			case RUN_OUT:
				setIntakePower(-0.5);
				break;
			case DEPLOY:
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

	public void setIntakePower(double powerLevel) {
		// positive configuration
		left.set(-powerLevel);
		right.set(powerLevel);
	}

}
