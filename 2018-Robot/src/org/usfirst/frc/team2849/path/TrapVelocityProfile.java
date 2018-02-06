package org.usfirst.frc.team2849.path;

import java.util.ArrayList;

import org.usfirst.frc.team2849.util.AngleHelper;

public class TrapVelocityProfile {

	public class Node {

		private double time;
		private double dist;
		private double vel;
		private double acc;

		public Node(double time, double dist, double vel, double acc) {
			this.time = time;
			this.dist = dist;
			this.vel = vel;
			this.acc = acc;
		}

		public double getDist() {
			return dist;
		}

		public double getTime() {
			return time;
		}

		public double getVel() {
			return vel;
		}

		public double getAcc() {
			return acc;
		}

		public String toString() {
			return time + ": " + dist + ", " + vel + ", " + acc;
		}

	}

	private double maxAccel;
	private double maxVel;
	private double dt;
	private double distance;
	private double accelTime;
	private double cruiseTime;
	private double curVel;
	private double totalTime;
	private double accelDist;
	private double cruiseDist;
	private Path path;
	private double totalDist;

	private Path mappedPath;

	/**
	 * Trapezoidal velocity profile for mapping velocity, acceleration, and time to
	 * a Path.
	 * 
	 * @param maxAccel
	 *            Max acceleration in in/s^2
	 * @param maxVel
	 *            Max velocity in in/s
	 * @param dt
	 *            Time between each point in s
	 * @param distance
	 *            Total distance of the Path in inches
	 */
	public TrapVelocityProfile(double maxAccel, double maxVel, double dt, Path path) {
		this.maxAccel = maxAccel;
		this.maxVel = maxVel;
		this.dt = dt;
		this.path = path;
		this.totalDist = 0;
		this.distance = path.get(path.numPoints() - 1).getPosition();
		mappedPath = new Path();
		makeProfile();
	}

	/*
	 * private void makeProfile() { accelTime = maxVel / maxAccel; accelDist =
	 * (maxAccel / 2) * Math.pow(accelTime, 2); if (accelDist > distance / 2) {
	 * accelDist -= (accelDist * 2 - distance) / 2; accelTime = Math.sqrt(2 *
	 * accelDist / maxAccel); } cruiseDist = distance - (2 * accelDist); cruiseTime
	 * = cruiseDist / maxVel; totalTime = 2 * accelTime + cruiseTime; for (double t
	 * = 0; t <= totalTime; t += dt) { profile.add(makePoint(t)); }
	 * profile.add(makePoint(totalTime)); }
	 */

	private void makeProfile() {
		accelTime = maxVel / maxAccel;
		accelDist = (maxAccel / 2) * Math.pow(accelTime, 2);
		if (accelDist > distance / 2) {
			accelDist -= (accelDist * 2 - distance) / 2;
			accelTime = Math.sqrt(2 * accelDist / maxAccel);
		}
		cruiseDist = distance - (2 * accelDist);
		cruiseTime = cruiseDist / maxVel;
		totalTime = 2 * accelTime + cruiseTime;
		maxVel = maxAccel * accelTime;

		System.out.println("Acceleration time: " + accelTime);
		System.out.println("Acceleration distance: " + accelDist);
		System.out.println("Cruise time: " + cruiseTime);
		System.out.println("Cruise Distance: " + cruiseDist);

		System.out.println("Total calculated time: " + totalTime);
		System.out.println("Total given distance: " + distance);
		double ds;
		double dir;
		double xft;
		double yft;
		PointonPath toAdd;
		PointonPath[] points;
		mappedPath
				.add(new PointonPath(0, path.get(0).getDirection(), path.get(0).xft, path.get(0).yft, 0, 0, maxAccel));
		int lastIndex = 0;
		for (double t = dt; t < totalTime; t += dt) {
			if (t < accelTime) {
				System.out.println("t < accelTime");
				System.out.println("curVel: " + curVel);
				System.out.println("totalDist: " + totalDist);
				System.out.println("");
				curVel = maxAccel * t;
				totalDist = (maxAccel / 2) * Math.pow(t, 2);
				ds = totalDist - mappedPath.get(lastIndex).getPosition();
				points = path.findSurroundingPoints(totalDist);
				System.out.println("Direction 0: " + points[0].getDirection());
				System.out.println("Direction 1: " + points[1].getDirection());
				System.out.println("Smallest Angle: " + AngleHelper.getSmallestAngleBetween(points[0].getDirection(), points[1].getDirection()));
				System.out.println("Distance difference: " + (totalDist - points[0].getPosition()));
				System.out.println("");
				dir = points[0].getDirection() + (totalDist - points[0].getPosition())
						* (AngleHelper.getSmallestAngleBetween(points[0].getDirection(), points[1].getDirection())
								/ (points[1].getPosition() - points[0].getPosition()));
				xft = points[0].xft + Math.cos(Math.toRadians(dir)) * (ds / 12);
				yft = points[0].yft + Math.sin(Math.toDegrees(dir)) * (ds / 12);
				toAdd = new PointonPath(totalDist, dir, xft, yft, t, curVel, maxAccel);
			} else if (t < accelTime + cruiseTime) {
				totalDist = accelDist + maxVel * (t - accelTime);
				ds = totalDist - mappedPath.get(lastIndex).getPosition();
				points = path.findSurroundingPoints(totalDist);
				dir = points[0].getDirection() + (totalDist - points[0].getPosition())
						* (AngleHelper.getSmallestAngleBetween(points[0].getDirection(), points[1].getDirection())
								/ (points[1].getPosition() - points[0].getPosition()));
				xft = points[0].xft + Math.cos(Math.toRadians(dir)) * (ds / 12);
				yft = points[0].yft + Math.sin(Math.toDegrees(dir)) * (ds / 12);
				toAdd = new PointonPath(totalDist, dir, xft, yft, t, maxVel, 0);
			} else if (t < totalTime) {
				System.out.println("accelTime < t < totalTime");
				System.out.println("curVel: " + curVel);
				System.out.println("totalDist: " + totalDist);
				System.out.println("");
				curVel = maxVel - maxAccel * (t - (accelTime + cruiseTime));
				totalDist = accelDist + cruiseDist + maxVel * (t - (accelTime + cruiseTime))
						- (maxAccel / 2) * Math.pow(t - (accelTime + cruiseTime), 2);
				ds = totalDist - mappedPath.get(lastIndex).getPosition();
				points = path.findSurroundingPoints(totalDist);
				dir = points[0].getDirection() + (totalDist - points[0].getPosition())
						* (AngleHelper.getSmallestAngleBetween(points[0].getDirection(), points[1].getDirection())
								/ (points[1].getPosition() - points[0].getPosition()));
				xft = points[0].xft + Math.cos(Math.toRadians(dir)) * (ds / 12);
				yft = points[0].yft + Math.sin(Math.toDegrees(dir)) * (ds / 12);
				toAdd = new PointonPath(totalDist, dir, xft, yft, t, curVel, -maxAccel);
			} else {
				System.out.println("Time should not be greater than totalTime: " + t);
				toAdd = null;
			}
			mappedPath.add(toAdd);
			lastIndex++;
		}
		mappedPath.add(new PointonPath(distance, path.get(path.numPoints() - 1).getDirection(),
				path.get(path.numPoints() - 1).xft, path.get(path.numPoints() - 1).yft, totalTime, 0, 0)); // end point
		System.out.println("Total accumulated distance: " + totalDist);
	}

	/*
	 * private PointonPath makePoint(double time) { PointonPath[] points; double ds;
	 * double dir; double xft; double yft; if (time <= accelTime) { curVel =
	 * maxAccel * time; ds = (maxAccel / 2) * Math.pow(time, 2); totalDist += ds;
	 * points = path.findSurroundingPoints(totalDist); dir =
	 * points[0].getDirection() + (ds - points[0].getPosition()) *
	 * (AngleHelper.getSmallestAngleBetween(points[0].getDirection(),
	 * points[1].getDirection()) / (points[1].getPosition() -
	 * points[0].getPosition())); xft = points[0].xft +
	 * Math.cos(Math.toRadians(dir)) * ds; yft = points[0].xft +
	 * Math.sin(Math.toDegrees(dir)) * ds; return new PointonPath(totalDist, dir,
	 * xft, yft, time, curVel, maxAccel); } else if (time <= accelTime + cruiseTime)
	 * { ds = maxVel * (time - accelTime); totalDist += ds; points =
	 * path.findSurroundingPoints(totalDist); dir = points[0].getDirection() + (ds -
	 * points[0].getPosition()) *
	 * (AngleHelper.getSmallestAngleBetween(points[0].getDirection(),
	 * points[1].getDirection()) / (points[1].getPosition() -
	 * points[0].getPosition())); xft = points[0].xft +
	 * Math.cos(Math.toRadians(dir)) * ds; yft = points[0].xft +
	 * Math.sin(Math.toDegrees(dir)) * ds; return new PointonPath(totalDist, dir,
	 * xft, yft, time, maxVel, 0); } else if (time < totalTime) { ds = curVel *
	 * (time - (accelTime + cruiseTime)) + (-maxAccel / 2) * Math.pow(time -
	 * (accelTime + cruiseTime), 2); totalDist += ds; points =
	 * path.findSurroundingPoints(totalDist); dir = points[0].getDirection() + (ds -
	 * points[0].getPosition()) *
	 * (AngleHelper.getSmallestAngleBetween(points[0].getDirection(),
	 * points[1].getDirection()) / (points[1].getPosition() -
	 * points[0].getPosition())); xft = points[0].xft +
	 * Math.cos(Math.toRadians(dir)) * ds; yft = points[0].xft +
	 * Math.sin(Math.toDegrees(dir)) * ds; return new PointonPath(totalDist, dir,
	 * xft, yft, time, maxVel - maxAccel * (time - (accelTime + cruiseTime))) return
	 * new Node(time, accelDist + cruiseDist + curVel * (time - (accelTime +
	 * cruiseTime)) + (-maxAccel / 2) * Math.pow(time - (accelTime + cruiseTime),
	 * 2),curVel - (maxAccel * (time - (accelTime + cruiseTime))), -maxAccel); }
	 * else { return new Node(time, distance, 0, 0); // end node } }
	 */

	public String toString() {
		String toPrint = "";
		for (PointonPath point : mappedPath.getPoints()) {
			toPrint += point + System.lineSeparator();
		}
		return toPrint;
	}

	public double getDt() {
		return dt;
	}

	public double getMaxVel() {
		return maxVel;
	}

	public ArrayList<PointonPath> getMappedPoints() {
		return mappedPath.getPoints();
	}

}
