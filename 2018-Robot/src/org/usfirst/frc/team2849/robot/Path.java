package org.usfirst.frc.team2849.robot;

import java.util.ArrayList;

public class Path {
	
	private ArrayList<Waypoint> path;
	private int nextPoint;
	
	public Path() {
		path = new ArrayList<Waypoint>();
		nextPoint = 0;
	}
	
	public Path(ArrayList<Waypoint> points) {
		path = new ArrayList<Waypoint>();
		for (Waypoint point : points) {
			path.add(point);
		}
		nextPoint = 0;
	}
	
	public Waypoint findNextPoint(double pos) {
		if (path.get(nextPoint).getPosition() <= pos) nextPoint++;
		try {
			return path.get(nextPoint);
		} catch (Exception e) {
			return new Waypoint(Integer.MAX_VALUE, Integer.MAX_VALUE);
		}
	}
	
	public Waypoint findLastPoint(double pos) {
		try {
			return path.get(nextPoint - 1);
		} catch (Exception e) {
			return new Waypoint(Integer.MIN_VALUE, Integer.MIN_VALUE);
		}
	}
	
	public Waypoint findNearestPoint(double pos) {
		Waypoint next = findNextPoint(pos);
		Waypoint last = findLastPoint(pos);
		if (next.getPosition() - pos <= pos - last.getPosition()) return next;
		else return last;
	}
	
	public void add(Waypoint point) {
		for (int i = 0; i < path.size(); i++) {
			if (point.getPosition() < path.get(i).getPosition()) {
				path.add(i, point);
				return;
			}
		}
		path.add(point);
	}
	
	public Waypoint get(int index) {
		return path.get(index);
	}

}