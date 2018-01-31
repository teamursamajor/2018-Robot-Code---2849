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
	public enum LiftType{BOTTOM, VAULT, SWITCH, SCALE}
	
	private LiftType lift;
	public LiftTask(ControlLayout cont, double height, LiftType liftPreset) {
		super(cont);
		lift = liftPreset;
		liftHeight = height;
		enMotor = new Encoder(5, 4);
		enMotor.setDistancePerPulse(inchesPerTick);
	}

	//TODO how will this work with a controller joystick at variable heights?
	@Override
	public void run() {
		//TODO we may need a conditional for the switch statement
		switch(lift) {
		case BOTTOM: 
			liftHeight = 0;
			break;
		case VAULT:
			liftHeight  = 1.75;
			break;
		case SWITCH:
			liftHeight = 20;
		case SCALE:
			liftHeight = 75;
		}
		int tempHeight = 0;
		double error = 0.1;
		while (tempHeight + error > currentHeight() && tempHeight - error < currentHeight()) {
			
			//why is this in the loop? it would never = 0 then
			double powerConstant  = 0.3d;
			cont.setLiftPower(powerConstant);
			if (limitSwitch.get()) {
				enMotor.reset();
			}
		}
	}

	public double currentHeight() {
		// TODO better than this please
		double distance = enMotor.getDistance();
		// TODO 2 carriage code

		return distance;
	}

	public String toString() {
		return "LiftTask: " + liftHeight + "\n";
	}
}