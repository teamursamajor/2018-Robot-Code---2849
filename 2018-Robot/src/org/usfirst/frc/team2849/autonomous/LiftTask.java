package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.ControlLayout;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;

public class LiftTask extends AutoTask {

//	// TODO maybe delete later? doesn't seem necessary
//	private double liftHeight = 0;
//	private Encoder enMotor;
//	private DigitalInput limitSwitch = new DigitalInput(1);
//	private double error = 0.1;
//	private double powerConstant = 0.3d;

	// TODO change this value
	double inchesPerTick = 0.011505d;

	public enum LiftType {
		BOTTOM, VAULT, SWITCH, SCALE
	}

	private LiftType lift;

	public LiftTask(ControlLayout cont, double height, LiftType liftPreset) {
		super(cont);
//		lift = liftPreset;
//		liftHeight = height;
//		enMotor = new Encoder(5, 4);
//		enMotor.setDistancePerPulse(inchesPerTick);
	}

	@Override
	public void run() {
//		if (DriverStation.getInstance().isAutonomous()) {
//			switch (lift) {
//			case BOTTOM:
//				cont.setLiftHeight(0.0);
//				break;
//			case VAULT:
//				cont.setLiftHeight(1.75);
//				break;
//			case SWITCH:
//				cont.setLiftHeight(20d);
//			case SCALE:
//				cont.setLiftHeight(75d);
//			}
//		}
//		cont.setLiftHeight(currentHeight());
//		cont.setLiftPower(powerConstant);

		// while (tempHeight + error > currentHeight() && tempHeight - error <
		// currentHeight()) {
		// //why is this in the loop? it would never = 0 then
		// if (limitSwitch.get()) {
		// enMotor.reset();
		// }
		// }
	}

//	public double currentHeight() {
//		// TODO better than this please
//		double distance = enMotor.getDistance();
//		// TODO 2 carriage code
//
//		return distance;
//	}
//
//	public String toString() {
//		return "LiftTask: " + liftHeight + "\n";
//	}
}