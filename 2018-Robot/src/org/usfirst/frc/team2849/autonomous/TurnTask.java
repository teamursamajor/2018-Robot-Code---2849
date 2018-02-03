package org.usfirst.frc.team2849.autonomous;

import org.usfirst.frc.team2849.controls.AutoControl;
import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.robot.Drive;

public class TurnTask extends AutoTask {

	public enum Turntype {
		TURN_TO, TURN_BY
	};

	private double desiredAngle;
	private Turntype type;
	
	public TurnTask(ControlLayout cont, Turntype type, double desiredAngle) {
		super(cont);
		this.desiredAngle = desiredAngle;
		this.type = type;
	}

	/**
	 * Method to turn to a desired angle. Turns clockwise/counterclockwise
	 * depending on which is most optimal. 15th January: Tested turnBy, works
	 * smoothly. TODO Add proportional control, fix turn direction!!!
	 */
	public void turnTo(double desiredAngle) {
		desiredAngle = Drive.fixHeading(desiredAngle);
		int count = 0;
		double powerConstant = 0;
		double angle = Drive.getHeading();
		//Logger.log("Start Angle: " + Drive.getHeading(), LogLevel.DEBUG);
		Logger.log("Desired Angle: " + desiredAngle, LogLevel.DEBUG);
		while (Math.abs(turnAmount(desiredAngle)) > 2) {
			angle = Drive.getHeading();
			powerConstant = getPower(turnAmount(desiredAngle));
			if (count % 100 == 0) {
				//Logger.log("Current Angle: " + angle, LogLevel.DEBUG);
				//Logger.log("\tPower Constant: " + powerConstant, LogLevel.DEBUG);
				//Logger.log("\tDesired Angle: " + desiredAngle, LogLevel.DEBUG);
			}
			count++;

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cont.setPower(1 * (Math.signum(turnAmount(desiredAngle)) * powerConstant),
					-1 * (Math.signum(turnAmount(desiredAngle)) * powerConstant));
		}
//		Logger.log("End Angle: " + Drive.getHeading(), LogLevel.DEBUG)
		cont.setPower(0, 0);
	}

	private double getPower(double turnAmount) {
		//TODO test this
		double lowPower = .6;
		double highPower = 1;
		return ( (highPower - lowPower)/ (1 + Math.exp(4 - 0.06 * Math.abs(turnAmount)))) + lowPower;
	}

	/**
	 * Determines what angle to turn by and which direction depending on which
	 * is most optimal. Positive output = clockwise Negative output =
	 * counterclockwise
	 * 
	 * @param desiredAngle
	 *            the angle you want to turn TO.
	 */
	public double turnAmount(double desiredAngle) {
		double angle = Drive.getHeading();
		desiredAngle = Drive.fixHeading(desiredAngle);
		double turnAmount = desiredAngle - angle;
		if (turnAmount > 180)
			turnAmount = -180 + (turnAmount % 180);
		else if (turnAmount < -180)
			turnAmount = 360 + (turnAmount % 360);
		return turnAmount;
	}

	/**
	 * Turns the robot by the amount entered in the parameter. Ex: If you want
	 * to go from 30 to 120 degrees, enter 90.
	 * 
	 * @param desiredAngle
	 *            the angle you want to turn BY.
	 */
	public void turnBy(double desiredAngle) {
		double currentAngle = Drive.getHeading();
		double finalAngle = currentAngle + desiredAngle;
		turnTo(finalAngle);
	}

	public void run() {
		if (type == Turntype.TURN_TO) {
			turnTo(desiredAngle);
		} else if (type == Turntype.TURN_BY) {
			turnBy(desiredAngle);
		}
	}

	public String toString() {
		return "TurnAmount: " + desiredAngle + ", TurnType: " + type.name() + "\n";
	}
}
