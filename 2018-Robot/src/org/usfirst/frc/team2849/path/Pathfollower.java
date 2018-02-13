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
		PointonPath point = path.findNextPointDist(distance);
		error = point.getPosition() - distance;
		System.out.println("   Point Position: " + point.getPosition());
		System.out.println("   Point time: " + point.getTime());
		System.out.println("   Distance: " + distance);
		System.out.println("   Error: " + error);
		System.out.println("   Velocity: " + point.getVelocity());
		System.out.println("   Acceleration: " + point.getAccel());
		double out = kp * error + kd * (point.getVelocity() - (error - prevError) / path.getDt())
				+ kv * point.getVelocity() + ka * point.getAccel();
		prevError = error;
		out = constrain(out);
		return out;
	}
	
	public double getSteering(PointonPath point, double heading) {
		System.out.println("   Heading: " + heading);
		System.out.println("   Point Heading: " + point.getDirection());
		return kturn * AngleHelper.getSmallestAngleBetween(heading, point.getDirection());
	}
	
	public double getDoubleSteering(PointonPath point, PointonPath secondPoint, double heading) {
		System.out.println("   Heading: " + heading);
		System.out.println("   First Point Heading: " + point.getDirection());
		System.out.println("   Second Point Heading: " + secondPoint.getDirection());
		double smallestAngle = AngleHelper.getSmallestAngleBetween(heading, point.getDirection());
		if (Math.abs(smallestAngle) >= 1) 
			return kturn * smallestAngle + .25 * kturn * AngleHelper.getSmallestAngleBetween(heading, secondPoint.getDirection());
		else return 0;
	}
	
	public double constrain(double power) {
		if (power > 1) {
			return 1;
		} else if (power < 0) {
			return 0;
		} else return power;
	}

}
