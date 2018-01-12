package autonomous;
import org.usfirst.frc.team2849.robot.*;
public class TurnTo{
	
	public TurnTo(){
		//TODO Initialize this constructor
	}
	/**
	 * Method to turn to a desired angle. Turns clockwise/counterclockwise depending on which is most optimal.
	 * @param desiredAngle the angle you want to turn TO.
	 */
	public void turnTo(double desiredAngle) {
		double angle = Drive.getHeading();
		//TODO powerConstant is temporary for now; will be replaced with P/PI controlling
		double powerConstant = 0.5;
		while (!inRange(angle, desiredAngle, 0.5)) {
			drive((Math.signum(turnAmount(desiredAngle))*powerConstant),-1*(Math.signum(turnAmount(desiredAngle))*powerConstant));
		}
	}
	
	/** 
	 * Determines what angle to turn by and which direction depending on which is most optimal.
	 * Positive output = clockwise
	 * Negative output = counterclockwise
	 * @param desiredAngle the angle you want to turn TO.
	 */
	public double turnAmount(double desiredAngle) {
		double angle = getHeading();
		double turnAmount = desiredAngle - angle;
		if (desiredAngle > (angle + 180))
			turnAmount = (turnAmount - 360) % 360;
		return turnAmount;
	}
	
	/**
	 * Checks if the value is within range of the center. Returns true if the value is within range of center.
	 * @param value The value being checked 
	 * @param center The center value for the range
	 * @param range The range of acceptable values.
	 * @return
	 */
	public boolean inRange(double value, double center, double range) {
		return (value < center + range) && (value > center - range);
	}
	
	/**
	 * Turns the robot by the amount entered in the parameter. Ex: If you want to go from 30 to 120 degrees, enter 90.
	 * @param desiredAngle the angle you want to turn BY.
	 */
	public void turnBy(double desiredAngle){
		double currentAngle = getHeading();
	    double finalAngle= currentAngle+desiredAngle;
	    turnTo(finalAngle);
		}
	


}
