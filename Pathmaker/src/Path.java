

import java.util.ArrayList;

public class Path {
	
	private ArrayList<Waypoint> path;
	private int nextPoint;
	private String name;
	
	public Path() {
		path = new ArrayList<Waypoint>();
		nextPoint = 0;
	}
	
	public Path(String name) {
		path = new ArrayList<Waypoint>();
		nextPoint = 0;
		this.name = name;
	}
	
	public Path(String name, ArrayList<Waypoint> points) {
		path = new ArrayList<Waypoint>();
		for (Waypoint point : points) {
			path.add(point);
		}
		nextPoint = 0;
		this.name = name;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int numPoints() {
		return path.size();
	}
	
	public String toString() {
		String toReturn = "Path " + name + ":";
		for (Waypoint point : path) {
			toReturn += "\n" + point;
		}
		return toReturn;
	}

}
