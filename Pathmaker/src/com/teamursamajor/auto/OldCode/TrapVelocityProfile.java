package com.teamursamajor.auto;
import java.util.ArrayList;

public class TrapVelocityProfile {
	
	public class Node {
		
		private double time;
		private double dist;
		private double vel;
		private double acc;
		
		public Node (double time, double dist, double vel, double acc) {
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
	
	private ArrayList<Node> profile;
	
	public TrapVelocityProfile(double maxAccel, double maxVel, double dt, double distance) {
		this.maxAccel = maxAccel;
		this.maxVel = maxVel;
		this.dt = dt;
		this.distance = distance;
		profile = new ArrayList<Node>();
		makeProfile();
	}
	
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
		for (double t = 0; t <= totalTime; t += dt) {
			profile.add(makeNode(t));
		}
		profile.add(makeNode(totalTime));
	}
	
	private Node makeNode(double time) {
		if (time <= accelTime) {
			curVel = maxAccel * time;
			return new Node(time, (maxAccel / 2) * Math.pow(time, 2), curVel, maxAccel);
		} else if (time <= accelTime + cruiseTime) {
			return new Node(time, accelDist + maxVel * (time - accelTime), maxVel, 0);
		} else if (time < totalTime) {
			return new Node(time, accelDist + cruiseDist + curVel * (time - (accelTime + cruiseTime)) + (-maxAccel / 2) * Math.pow(time - (accelTime + cruiseTime), 2),curVel - (maxAccel * (time - (accelTime + cruiseTime))), -maxAccel);
		} else  {
			return new Node(time, distance, 0, 0); // end node
		}
	}
	
	public String toString() {
		String toPrint = "";
		for (Node point : profile) {
			toPrint += point + System.lineSeparator();
		}
		return toPrint;
	}
	
	public ArrayList<Node> getNodes() {
		return profile;
	}
	
}
