package org.usfirst.frc.team2849.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * joy is shameful and dishonors my entire family for generations to come
 * @author FRC Team 2849 URSA MAJOR 2018 Season
 */
public class Xbox {

	//Use these ints when referring to a Joystick axis or button for readability
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int BUTTON_LEFTBUMPER = 5;
	public static final int BUTTON_RIGHTBUMPER = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;
	public static final int BUTTON_LEFTSTICK = 9;
	public static final int BUTTON_RIGHTSTICK = 10;

	public static final int AXIS_LEFTSTICK_X = 0;
	public static final int AXIS_LEFTSTICK_Y = 1;
	public static final int AXIS_LEFTTRIGGER = 2;
	public static final int AXIS_RIGHTTRIGGER = 3;
	public static final int AXIS_RIGHTSTICK_X = 4;
	public static final int AXIS_RIGHTSTICK_Y = 5;

	public static final int POV_NONE = -1;
	public static final int POV_UP = 0;
	public static final int POV_RIGHT = 90;
	public static final int POV_DOWN = 180;
	public static final int POV_LEFT = 270;

	private static Joystick joy;

	public static void init(int port) {
		joy = new Joystick(port);
	}
	
	/**
	 * Gets the value of a button
	 * @param buttonNumber
	 * 				the button whose value is to be read
	 * @return the button's value
	 */
	public static boolean getButton(int buttonNumber) {
		return joy.getRawButton(buttonNumber);
	}
	
	/**
	 * Gets the value of an axis
	 * @param axisNumber
	 * 				the axis whose value is to be read
	 * @return the axis' value
	 */
	public static double getAxis(int axisNumber) {
		return joy.getRawAxis(axisNumber);
	}
	
	/**
	 * Checks if an axis is greater than (NOT equal to) a threshold
	 * @param axisNumber
	 * 				axis whose value is to be compared
	 * @param greaterThan
	 * 				value to compare the axis to
	 * @return true if the axis is greater than the threshold, false otherwise
	 */
	public static double getAxisGreaterThan(int axisNumber, double greaterThan) {
		if(Math.abs(joy.getRawAxis(axisNumber)) > greaterThan){
			return joy.getRawAxis(axisNumber);
		}else{
			return 0;
		}
	}
	
	/**
	 * Checks if an axis is less than (NOT equal to) a threshold
	 * @param axisNumber
	 * 				axis whose value is to be compared
	 * @param lessThan
	 * 				value to compare the axis to
	 * @return true if the axis is less than the threshold, false otherwise
	 */
	public static boolean getAxisLessThan(int axisNumber, double lessThan) {
		return joy.getRawAxis(axisNumber) < lessThan;
	}
	
	/**
	 * Checks if a specified POV is pressed
	 * @param dPadNumber
	 * 				the POV value to be checked
	 * @return true if the specified POV is pressed, false otherwise
	 */
	public static boolean getDPad(int dPadNumber) {
		return joy.getPOV(0) == dPadNumber;
	}

}