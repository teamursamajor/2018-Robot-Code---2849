package org.usfirst.frc.team2849.util;

public class AngleHelper {
	
	public static double getSmallestAngleBetween(double angle1, double angle2) {
		if (angle1 > angle2) {
			angle2 += 360;
		}
		double diff = Math.abs(angle2 - angle1);
		if (diff > 180) {
			return Math.signum(angle2 - angle1) * (360 - diff);
		} else  {
			return Math.signum(angle2 - angle1) * diff;
		}
		// if (diff > 180) return (360 - diff);
		// else return diff;
	}

}
