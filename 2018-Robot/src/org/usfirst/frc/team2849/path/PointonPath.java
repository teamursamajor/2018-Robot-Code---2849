package org.usfirst.frc.team2849.path;

public class PointonPath {
	
	private double position;
	private double direction;
	
	public PointonPath(double position, double direction) {
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
