package com.teamursamajor.auto;

import java.util.ArrayList;

import com.teamursamajor.auto.PointonPath;
import com.teamursamajor.auto.TrapVelocityProfile.Node;

public class Path {

	private ArrayList<PointonPath> path;
	private int nextPoint;
	private String name;

	public Path() {
		path = new ArrayList<PointonPath>();
		nextPoint = 0;
	}

	public Path(String name) {
		path = new ArrayList<PointonPath>();
		nextPoint = 0;
		this.name = name;
	}

	public Path(String name, ArrayList<PointonPath> points) {
		path = new ArrayList<PointonPath>();
		for (PointonPath point : points) {
			path.add(point);
		}
		nextPoint = 0;
		this.name = name;
	}

	public PointonPath findNextPoint(double pos) {
		if (path.get(nextPoint).getPosition() <= pos)
			nextPoint++;
		try {
			return path.get(nextPoint);
		} catch (Exception e) {
			return new PointonPath(Integer.MAX_VALUE, Integer.MAX_VALUE, -1, -1);
		}
	}

	public PointonPath findLastPoint(double pos) {
		try {
			return path.get(nextPoint - 1);
		} catch (Exception e) {
			return new PointonPath(Integer.MIN_VALUE, Integer.MIN_VALUE, -1, -1);
		}
	}

	public PointonPath findNearestPoint(double pos) {
		PointonPath next = findNextPoint(pos);
		PointonPath last = findLastPoint(pos);
		if (next.getPosition() - pos <= pos - last.getPosition())
			return next;
		else
			return last;
	}

	public PointonPath[] findSurroundingPoints(double pos) {
		for (int i = 1; i < path.size(); i++) {
			if (pos <= path.get(i).getPosition()) {
				return new PointonPath[] { path.get(i - 1), path.get(i)	};
			}
		}
		return new PointonPath[] {null, null};
		
	}

	public void add(PointonPath point) {
		for (int i = 0; i < path.size(); i++) {
			if (point.getPosition() < path.get(i).getPosition()) {
				path.add(i, point);
				return;
			}
		}
		path.add(point);
	}

	public PointonPath get(int index) {
		return path.get(index);
	}

	public ArrayList<PointonPath> getPoints() {
		return path;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int numPoints() {
		return path.size();
	}

	public String toString() {
		String toReturn = "Path " + name + ":";
		for (PointonPath point : path) {
			toReturn += "\n" + point;
		}
		return toReturn;
	}

	public Path[] separate(double separation) {
		Path leftPath = new Path(name + "Left");
		Path rightPath = new Path(name + "Right");
		double leftDist;
		double rightDist;
		double leftX;
		double leftY;
		double rightX;
		double rightY;
		double leftVel;
		double rightVel;
		double leftAcc;
		double rightAcc;
		double perpHeading;
		double cos;
		double sin;
		double time = 0;
		perpHeading = Math.toRadians((path.get(0).getDirection() + 90) % 360);
		cos = Math.cos(perpHeading);
		sin = Math.sin(perpHeading);
		rightX = path.get(0).xft + (cos * (separation / 2));
		rightY = path.get(0).yft + (sin * (separation / 2));
		leftX = path.get(0).xft - (cos * (separation / 2));
		leftY = path.get(0).yft - (sin * (separation / 2));
		leftDist = 0;
		rightDist = 0;
		leftVel = path.get(0).getVelocity();
		rightVel = path.get(0).getVelocity();
		leftAcc = path.get(0).getAccel();
		rightAcc = path.get(0).getAccel();
		leftPath.add(new PointonPath(leftDist, path.get(0).getDirection(), leftX, leftY, time, leftVel, leftAcc));
		rightPath.add(new PointonPath(rightDist, path.get(0).getDirection(), rightX, rightY, time, rightVel, rightAcc));
		for (int i = 1; i < path.size() - 1; i++) {
			time = path.get(i).getTime();
			perpHeading = Math.toRadians((path.get(i).getDirection() + 90) % 360);
			cos = Math.cos(perpHeading);
			sin = Math.sin(perpHeading);
			rightX = path.get(i).xft + (cos * (separation / 2));
			rightY = path.get(i).yft + (sin * (separation / 2));
			leftX = path.get(i).xft - (cos * (separation / 2));
			leftY = path.get(i).yft - (sin * (separation / 2));
			rightDist += Math.sqrt(Math.pow(rightX - rightPath.get(i - 1).xft, 2) + Math.pow(rightY - rightPath.get(i - 1).yft, 2));
			leftDist += Math.sqrt(Math.pow(leftX - leftPath.get(i - 1).xft, 2) + Math.pow(leftY - leftPath.get(i - 1).yft, 2));
			rightVel = (rightDist - rightPath.get(i - 1).getPosition()) / (time - rightPath.get(i - 1).getTime()); 
			leftVel = (leftDist - leftPath.get(i - 1).getPosition()) / (time - leftPath.get(i - 1).getTime());
			rightAcc = (rightVel - rightPath.get(i - 1).getVelocity()) / (time - rightPath.get(i - 1).getTime());
			leftAcc = (leftVel - leftPath.get(i - 1).getVelocity()) / (time - leftPath.get(i - 1).getTime());
			leftPath.add(new PointonPath(leftDist, path.get(i).getDirection(), leftX, leftY, time, leftVel, leftAcc));
			rightPath.add(new PointonPath(rightDist, path.get(i).getDirection(), rightX, rightY, time, rightVel, rightAcc));
		}
		time = path.get(path.size() - 1).getTime();
		perpHeading = Math.toRadians((path.get(path.size() - 1).getDirection() + 90) % 360);
		cos = Math.cos(perpHeading);
		sin = Math.sin(perpHeading);
		rightX = path.get(path.size() - 1).xft + (cos * (separation / 2));
		rightY = path.get(path.size() - 1).yft + (sin * (separation / 2));
		leftX = path.get(path.size() - 1).xft - (cos * (separation / 2));
		leftY = path.get(path.size() - 1).yft - (sin * (separation / 2));
		rightDist += Math.sqrt(Math.pow(rightX - rightPath.get(path.size() - 2).xft, 2) + Math.pow(rightY - rightPath.get(path.size() - 2).yft, 2));
		leftDist += Math.sqrt(Math.pow(leftX - leftPath.get(path.size() - 2).xft, 2) + Math.pow(leftY - leftPath.get(path.size() - 2).yft, 2));
		rightVel = (rightDist - rightPath.get(path.size() - 2).getPosition()) / (time - rightPath.get(path.size() - 2).getTime()); 
		leftVel = (leftDist - leftPath.get(path.size() - 2).getPosition()) / (time - leftPath.get(path.size() - 2).getTime());
		rightAcc = (rightVel - rightPath.get(path.size() - 2).getVelocity()) / (time - rightPath.get(path.size() - 2).getTime());
		leftAcc = (leftVel - leftPath.get(path.size() - 2).getVelocity()) / (time - leftPath.get(path.size() - 2).getTime());
		leftPath.add(new PointonPath(leftDist, path.get(path.size() - 1).getDirection(), leftX, leftY, time, 0, 0));
		rightPath.add(new PointonPath(rightDist, path.get(path.size() - 1).getDirection(), rightX, rightY, time, 0, 0));
		return new Path[] { leftPath, rightPath };
	}
	
	public double getSmallestAngleBetween(double angle1, double angle2) {
		double diff = Math.abs(angle2 - angle1);
		return diff > 180 ? 360 - diff : diff;
		// if (diff > 180) return (360 - diff);
		// else return diff;
	}

	// RTFM @ charlie
	public PointonPath pointAt(double dist) {
		PointonPath[] neighbors = findSurroundingPoints(dist);
		System.out.println(dist);
		System.out.println(neighbors[0]);
		System.out.println(neighbors[1]);
		double dir = neighbors[0].getDirection() + (getSmallestAngleBetween(neighbors[0].getDirection(), neighbors[1].getDirection()) / (neighbors[0].getPosition() - neighbors[1].getPosition()));
		double radDir = Math.toRadians(dir);
		double xft = neighbors[0].xft + (Math.abs(dist - neighbors[0].getPosition()) * Math.cos(radDir));
		double yft = neighbors[0].yft + (Math.abs(dist - neighbors[0].getPosition()) * Math.sin(radDir));
		PointonPath newPoint = new PointonPath(dist, dir, xft, yft);
		System.out.println(newPoint);
		return newPoint;
	}
	
	public void mapVelocity() {
		System.out.println(path.get(0));
		PointonPath approxPoint;
		ArrayList<PointonPath> mappedPath = new ArrayList<PointonPath>();
		TrapVelocityProfile trap = new TrapVelocityProfile(1, 10, .1, path.get(path.size() - 1).getPosition());
		for (Node point : trap.getNodes()) {
			approxPoint = pointAt(point.getDist());
			mappedPath.add(new PointonPath(point.getDist(), approxPoint.getDirection(), approxPoint.xft, approxPoint.yft, point.getTime(), point.getVel(), point.getAcc()));
		}
		path = mappedPath;
	}
}
