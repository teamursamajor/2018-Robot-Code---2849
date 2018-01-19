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
		for (PointonPath point : path) {
			if (point.getPosition() < pos) {
				try {
					return new PointonPath[] {point, path.get(path.indexOf(point) + 1)};
				} catch (IndexOutOfBoundsException e) {
					return new PointonPath[] {point, new PointonPath(point.getPosition() + 1, point.getDirection(), point.xft + Math.cos(point.getDirection()), point.yft + Math.sin(point.getDirection()))};
				}
			}
		}
		return new PointonPath[] {new PointonPath(path.get(0).getPosition() - 1, path.get(0).getDirection(), path.get(0).xft - Math.cos(path.get(0).getDirection()), path.get(0).yft - Math.sin(path.get(0).getDirection())), path.get(0)};
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
		Path leftPath = new Path();
		Path rightPath = new Path();
		double leftX;
		double leftY;
		double rightX;
		double rightY;
		double perpHeading;
		double cos;
		double sin;
		for (PointonPath point : path) {
			perpHeading = point.getDirection() + 90 % 360;
			cos = Math.cos(perpHeading);
			sin = Math.sin(perpHeading);

		}
		return new Path[] { null, null };
	}

	public PointonPath pointAt(double dist) {
		PointonPath[] neighbors = findSurroundingPoints(dist);
		double dir = neighbors[0].getDirection() + ((neighbors[0].getDirection() - neighbors[1].getDirection()) / (neighbors[0].getPosition() - neighbors[1].getPosition()));
		double xft = neighbors[0].xft + Math.cos(dir) * (neighbors[1].xft - neighbors[0].xft);
		double yft = neighbors[0].yft + Math.sin(dir) * (neighbors[1].yft - neighbors[0].yft);
		return new PointonPath(dist, dir, xft, yft);
	}
	
	public void mapVelocity() {
		PointonPath approxPoint;
		ArrayList<PointonPath> mappedPath = new ArrayList<PointonPath>();
		TrapVelocityProfile trap = new TrapVelocityProfile(1, 10, .2, path.get(path.size() - 1).getPosition());
		for (Node point : trap.getNodes()) {
			approxPoint = pointAt(point.getDist());
			mappedPath.add(new PointonPath(point.getDist(), approxPoint.getDirection(), approxPoint.xft, approxPoint.yft, point.getTime(), point.getVel(), point.getAcc()));
		}
		path = mappedPath;
	}
}
