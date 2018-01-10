package org.usfirst.frc.team2849.robot;

public class Waypoint {
	
	private double position;
	private double direction;
	
	public Waypoint(double position, double direction) {
		this.position = position;
		this.direction = direction;
	}
	
	public double getPosition() {
		return position;
	}
	
	public double getDirection() {
		return direction;
	}

}
