package org.usfirst.frc.team2849.autonomous;
import org.usfirst.frc.team2849.robot.*;
public class TurnTask implements AutoTask{
	
	public enum Turntype { TURN_TO, TURN_BY };
	private double desiredAngle;
	private Turntype type;
	
	public TurnTask(Turntype type, double desiredAngle){
		this.desiredAngle = desiredAngle;
		this.type = type;
	}
	/**
	 * Method to turn to a desired angle. Turns clockwise/counterclockwise depending on which is most optimal.
	 * 15th January: Tested turnBy, works smoothly.
	 * TODO Add proportional control, fix turn direction!!!
	 */
	public void turnTo(double desiredAngle) {
		desiredAngle = Drive.fixHeading(desiredAngle);
		int count = 0;
		double angle = Drive.getHeading();
		//TODO powerConstant is temporary for now; will be replaced with P/PI controlling
		double powerConstant = 0.5;
		System.out.println("Start Angle: " + Drive.getHeading());
		System.out.println("Desired Angle: " + desiredAngle);
		while (Math.abs(turnAmount(desiredAngle)) > 2) {
			angle = Drive.getHeading();
			if (count%10000 == 0) {
				System.out.print("Current Angle: " + angle);
				System.out.print("\tPower Constant: " + powerConstant);
				System.out.println("\tDesired Angle: " + desiredAngle);

			}
			count++;

			//if (Math.abs(angle - desiredAngle) < 20) {
			//	powerConstant -= Math.abs(angle - desiredAngle)/100;
/**				System.out.println("Angle Difference: " + Math.abs(angle - desiredAngle)); */	
			//}

			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Drive.drive(-1*(Math.signum(turnAmount(desiredAngle))*powerConstant),(Math.signum(turnAmount(desiredAngle))*powerConstant), false);
		}
/**		System.out.println("End Angle: " + Drive.getHeading()); */
		Drive.drive(0, 0, true);
	}
	
	/** 
	 * Determines what angle to turn by and which direction depending on which is most optimal.
	 * Positive output = clockwise
	 * Negative output = counterclockwise
	 * @param desiredAngle the angle you want to turn TO.
	 */
	public double turnAmount(double desiredAngle) {
		double angle = Drive.getHeading();
		double turnAmount = desiredAngle - angle;
		if (turnAmount > 180)
			turnAmount = 180 - (turnAmount % 180);
		return turnAmount;
	}
	
	/**
	 * Turns the robot by the amount entered in the parameter. Ex: If you want to go from 30 to 120 degrees, enter 90.
	 * @param desiredAngle the angle you want to turn BY.
	 */
	public void turnBy(double desiredAngle){
		double currentAngle = Drive.getHeading();
	    double finalAngle= currentAngle+desiredAngle;
	    turnTo(finalAngle);
	}
	
	public void runTask() {
		if(type == Turntype.TURN_TO){
			turnTo(desiredAngle);
		} else if (type == Turntype.TURN_BY){
			turnBy(desiredAngle);
		}
	}
	public String toString() {
		return "TurnAmount: " + desiredAngle + ", TurnType: " + type.name() + "\n";
	}
}
