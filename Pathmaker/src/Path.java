

import java.util.ArrayList;

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
		if (path.get(nextPoint).getPosition() <= pos) nextPoint++;
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
		return new Path[] {null, null};
	}

}
