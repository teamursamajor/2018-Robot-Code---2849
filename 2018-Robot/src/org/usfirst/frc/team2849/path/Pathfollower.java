package org.usfirst.frc.team2849.path;

import org.usfirst.frc.team2849.util.AngleHelper;

public class Pathfollower {

	private double kp;
	private double ki;
	private double kd;
	private double kv;
	private double ka;
	private double kturn;

	private double error;
	private double prevError;

	public Pathfollower(double kp, double ki, double kd, double kv, double ka, double kturn) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		this.kv = kv;
		this.ka = ka;
		this.kturn = kturn;
		prevError = 0;
	}

	public double getCorrection(Path path, double distance, double time) {
		PointonPath point = path.findNextPoint(time);
		error = (point.getPosition() * 12) - distance;
		System.out.println("   Point Position: " + (point.getPosition() * 12));
		System.out.println("   Distance: " + distance);
		System.out.println("   Error: " + error);
		System.out.println("   Velocity: " + point.getVelocity());
		System.out.println("   Acceleration: " + point.getAccel());
		double out = kp * error + kd * ((error - prevError) / path.getDt() - (point.getVelocity() * 12))
				+ kv * point.getVelocity() + ka * point.getAccel();
		prevError = error;
		return out;
	}
	
	public double getSteering(PointonPath point, double heading) {
		return kturn * AngleHelper.getSmallestAngleBetween(heading, point.getDirection());
	}

}
