package org.usfirst.frc.team2849.controls;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Wrapper class for Joystick that adds useful methods as well as rumble!
 * @author FRC Team 2849 URSA MAJOR 2016 Season
 */
public class XboxController extends Joystick implements Runnable {

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

	private boolean running = false;
	private long rumbleStopTime = 0;
    Latch buttonLatch[]=new Latch[10];
    Latch axisLatch[]=new Latch [6];
	public XboxController(int port) {
		super(port);
		Thread rumbleThread = new Thread(this, "rumbleThread");
		rumbleThread.start();
		for (Latch num1: buttonLatch){
			num1=new Latch();
		}
		for (Latch num2: axisLatch){
			num2=new Latch();
		}
	}

	/**
	 * Starts the controller rumbling for a set amount of time
	 * @param rumbleTime
	 * 				time for the controller to rumble in milliseconds
	 */
	public void rumbleFor(int rumbleTime) {
		rumbleStopTime = System.currentTimeMillis() + rumbleTime;
	}
	
	/**
	 * Stops the controller from rumbling
	 */
	public void stopRumble() {
		rumbleStopTime = System.currentTimeMillis();
	}
	
	/**
	 * Gets the value of a button
	 * @param buttonNumber
	 * 				the button whose value is to be read
	 * @return the button's value
	 */
	public boolean getButton(int buttonNumber) {
		return super.getRawButton(buttonNumber);
	}
	
	/**
	 * Gets the value of an axis
	 * @param axisNumber
	 * 				the axis whose value is to be read
	 * @return the axis' value
	 */
	public double getAxis(int axisNumber) {
		return this.getRawAxis(axisNumber);
	}
	
	/**
	 * Checks if an axis is greater than (NOT equal to) a threshold
	 * @param axisNumber
	 * 				axis whose value is to be compared
	 * @param greaterThan
	 * 				value to compare the axis to
	 * @return true if the axis is greater than the threshold, false otherwise
	 */
	public boolean getAxisGreaterThan(int axisNumber, double greaterThan) {
		return this.getRawAxis(axisNumber) > greaterThan;
	}
	
	/**
	 * Checks if an axis is less than (NOT equal to) a threshold
	 * @param axisNumber
	 * 				axis whose value is to be compared
	 * @param lessThan
	 * 				value to compare the axis to
	 * @return true if the axis is less than the threshold, false otherwise
	 */
	public boolean getAxisLessThan(int axisNumber, double lessThan) {
		return this.getRawAxis(axisNumber) < lessThan;
	}
	
	public double getSquaredAxis(int axisNumber) {
		double rawInput = this.getAxis(axisNumber);
		return rawInput * Math.abs(rawInput);
	}
	
	/**
	 * Checks if a specified POV is pressed
	 * @param dPadNumber
	 * 				the POV value to be checked
	 * @return true if the specified POV is pressed, false otherwise
	 */
	public boolean getDPad(int dPadNumber) {
		return this.getPOV(0) == dPadNumber;
	}
	
	public boolean getSingleButtonPress(int indexnum){
		return buttonLatch[indexnum-1].buttonPress(getButton(indexnum));
	}
	
	public boolean getSingleAxisPress(int indexnum){
		return axisLatch[indexnum-1].buttonPress(getAxisGreaterThan(indexnum,0.25));
	}
	/**
	 * Started on object init, runs in background and monitors rumble
	 */
	
	public void run() {
		while (running) {
			if (System.currentTimeMillis() - rumbleStopTime < 0) {
				this.setRumble(RumbleType.kLeftRumble, 1);
				this.setRumble(RumbleType.kRightRumble, 1);
			} else {
				this.setRumble(RumbleType.kLeftRumble, 0);
				this.setRumble(RumbleType.kRightRumble, 0);
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Rising edge detector
	 * @author kingeinstein
	 *
	 */
	
	public class Latch {
		private boolean lastInput = false;
		
		public Latch(){
			
		}
		
		public boolean buttonPress(boolean button){
			boolean press = !lastInput && button;
			lastInput = button;
			return press;
			
		}
	}

}