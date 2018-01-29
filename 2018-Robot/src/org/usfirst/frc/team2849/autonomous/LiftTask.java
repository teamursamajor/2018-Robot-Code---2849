package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class LiftTask extends AutoTask {

	private double liftHeight = 0;
	private Encoder enMotor;
	private DigitalInput limitSwitch = new DigitalInput(1);
	
	//TODO change this value
	double inchesPerTick = 0.011505d;

	public LiftTask(ControlLayout cont, double height) {
		super(cont);
		liftHeight = height;
		enMotor = new Encoder(5, 4);
		enMotor.setDistancePerPulse(inchesPerTick);
	}

	@Override
	public void run() {
		int tempHeight = 0;
		double error = 0.1;
		while (tempHeight + error > calcHeight() && tempHeight - error < calcHeight()) {
			double powerConstant = 0.3d;
			cont.setLiftPower(powerConstant);
			if (limitSwitch.get()) {
				enMotor.reset();
			}
		}
	}

	public double calcHeight() {
		// TODO better than this please
		double distance = enMotor.getDistance();
		// TODO 2 carriage code

		return distance;
	}

	public String toString() {
		return "LiftTask: " + liftHeight + "\n";
	}
}
