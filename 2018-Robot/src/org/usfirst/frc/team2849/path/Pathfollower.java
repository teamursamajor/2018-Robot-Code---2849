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

	public double getCorrection(Path path, double distance) {
		PointonPath point = path.findNextPoint(distance);
		error = point.getPosition() - distance;
		double out = kp * error + kd * ((error - prevError) / path.getDt() - point.getVelocity())
				+ kv * point.getVelocity() + ka * point.getAccel();
		prevError = error;
		return out;
	}
	
	public double getSteering(PointonPath point, double heading) {
		return kturn * AngleHelper.getSmallestAngleBetween(heading, point.getDirection());
	}

}
