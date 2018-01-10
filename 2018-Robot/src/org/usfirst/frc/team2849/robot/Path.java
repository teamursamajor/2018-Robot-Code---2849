package org.usfirst.frc.team2849.robot;

import java.util.ArrayList;

public class Path {
	
	private ArrayList<Waypoint> path;
	
	public Path() {
		path = new ArrayList<Waypoint>();
	}
	
	public Path(ArrayList<Waypoint> points) {
		path = new ArrayList<Waypoint>();
		for (Waypoint point : points) {
			path.add(point);
		}
	}
	
	public Waypoint findNextPoint(double pos) {
		for (int i = 0; i < path.size(); i++) {
			if (path.get(i).getPosition() > pos) return path.get(i);
		}
		return null;
	}
	
	public Waypoint findLastPoint(double pos) {
		for (int i = 0; i < path.size(); i++) {
			if (path.get(i).getPosition() < pos) return path.get(i);
		}
		return null;
	}
	
	public Waypoint findNearestPoint(double pos) {
		Waypoint next = findNextPoint(pos);
		Waypoint last = findLastPoint(pos);
		if (last != null) {
			if (next != null) {
				if (next.getPosition() - pos <= pos - last.getPosition()) return next;
				else return last;
			}
			return null;
		}
		return next;
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
