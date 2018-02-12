package org.usfirst.frc.team2849.robot;

public interface UrsaRobot {
	//2704
	//2407
	public static final int DRIVE_FRONT_LEFT = 1;
	public static final int DRIVE_FRONT_RIGHT = 7;
	public static final int DRIVE_REAR_LEFT = 0;
	public static final int DRIVE_REAR_RIGHT = 6;
	
	public static final int INTAKE_LEFT = 3;
	public static final int INTAKE_RIGHT = 4;
	
	public static final int LIFT = 5;
	
	public static final int CONTROLLER_PORT = 1;
	
	public static final int LEFT_ENCODER_CHANNEL_A = 0;
	public static final int LEFT_ENCODER_CHANNEL_B = 1;
	public static final int RIGHT_ENCODER_CHANNEL_A = 2;
	public static final int RIGHT_ENCODER_CHANNEL_B = 3;
	
	public static final double ROBOT_WIDTH_INCHES  = 32d;/*in*///12;//ft
	public static final double ROBOT_DEPTH_INCHES = 28d;/*in*///12;//ft
	
	public static final double ROBOT_WIDTH_FEET = 32.0 / 12.0;
	public static final double ROBOT_DEPTH_FEET = 28.0 / 12.0;
	
	public static final double MAX_VELOCITY = 160; // inches / second
	public static final double MAX_ACCELERATION = 160 * 2; // inches / second^2
	
	public static final int LED_Solenoid_port_1 = 1;
	public static final int LED_Solenoid_port_2 = 2;
	public static final int LED_Solenoid_port_3 = 3;
	public static final int LED_Solenoid_port_4 = 4;
	public static final int LED_Solenoid_port_5 = 5;
	public static final int LED_Solenoid_port_6 = 6;

}
