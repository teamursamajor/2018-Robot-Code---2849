package org.usfirst.frc.team2849.path;

import java.util.ArrayList;

public class Path {
	
	private ArrayList<PointonPath> path;
	private int nextPoint;
	
	public Path() {
		path = new ArrayList<PointonPath>();
		nextPoint = 0;
	}
	
	public Path(ArrayList<PointonPath> points) {
		path = new ArrayList<PointonPath>();
		for (PointonPath point : points) {
			path.add(point);
		}
		nextPoint = 0;
	}
	
	public PointonPath findNextPoint(double pos) {
		if (path.get(nextPoint).getPosition() <= pos) nextPoint++;
		try {
			return path.get(nextPoint);
		} catch (Exception e) {
			return new PointonPath(Integer.MAX_VALUE, Integer.MAX_VALUE);
		}
	}
	
	public PointonPath findLastPoint(double pos) {
		try {
			return path.get(nextPoint - 1);
		} catch (Exception e) {
			return new PointonPath(Integer.MIN_VALUE, Integer.MIN_VALUE);
		}
	}
	
	public PointonPath findNearestPoint(double pos) {
		PointonPath next = findNextPoint(pos);
		PointonPath last = findLastPoint(pos);
		if (next.getPosition() - pos <= pos - last.getPosition()) return next;
		else return last;
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

}
