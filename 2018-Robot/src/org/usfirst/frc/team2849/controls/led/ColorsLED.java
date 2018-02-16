package org.usfirst.frc.team2849.controls.led;

public class ColorsLED {
	
	private static boolean redLED;
	private static boolean blueLED;
	private static boolean greenLED;
	
	public static void setRed(){
		redLED = true;
		blueLED = false;
		greenLED = false;
	}
	
	public static void setBlue(){
		redLED = false;
		blueLED = true;
		greenLED = false;
	}
	public static void setGreen(){
		redLED = false;
		blueLED = false;
		greenLED = true;
	}
	public static void setCyan(){
		redLED = false;
		blueLED = true;
		greenLED = true;
	}
	public static void setPurple(){
		redLED = true;
		blueLED = true;
		greenLED = false;
	}
	public static void setYellow(){
		redLED = true;
		blueLED = false;
		greenLED = true;
	}
	public static void setNullColor(){
		redLED = false;
		blueLED = false;
		greenLED = false;
	}
	public static void setWhite(){
		redLED = true;
		blueLED = true;
		greenLED = true;
	}
	
	public static boolean getRed(){
		return redLED;
	}
	public static boolean getBlue(){
		return blueLED;
	}
	public static boolean getGreen(){
		return greenLED;
	}
	
	
}
