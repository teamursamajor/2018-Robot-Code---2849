package com.teamursamajor.auto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PointonPath extends JPanel {
	protected double x;
	protected double y;
	protected double xFeet;// convert pixels to feet, look up conversion , field is 54/27ft.
	protected double yFeet;
	double xconv = 27d/* ft */ / 400/* pixels */;
	double yconv = 54d/* ft */ / 800/* pixels */;
	int precision = 2;
	int i;//TODO - change
	String message = "";
	static int h = 50;
	private double position;
	private double direction;
	private ArrayList<Color> colorarraay;
	private double acceleration;
	private double velocity;
	private double time;

	public double getDirection() {
		return direction;
	}

	public String toString() {
		return String.format(" %-9f  ", time) + String.format(" %-9f  ", position)
				+ String.format(" %-13f  ", direction) + String.format(" %-10f  ", velocity)
				+ String.format(" %-12f  ", acceleration) + String.format(" %-9f  ", Math.round(xFeet * 100) / 100.0)
				+ String.format(" %-9f ", Math.round(yFeet * 100) / 100.0);

	}
	// bottons to impliment{
	// highlight segment
	// drop box
	// pick up box

	// the ability to insert points not from the end
	public PointonPath(double pos, double dir, double xFeet, double yFeet) {
		position = pos;
		direction = dir;
		this.xFeet = xFeet;// Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10, precision);
		this.yFeet = yFeet;// Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10, precision);
		x = xFeet / (Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10, precision));
		y = yFeet / (Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10, precision));
		acceleration = 0;
		velocity = 0;
		time = 0;
		// x = x_;
		// y = y_;
		// xFeet = Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10,
		// precision);
		// yFeet = Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10,
		// precision);
	}

	PointonPath(double pos, double dir, double xFeet_, double yFeet_, double time, double vel, double accel) {
		position = pos;
		direction = dir;
		xFeet = xFeet_; // Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10, precision);
		yFeet = yFeet_; // Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10, precision);
		x = xFeet / xconv;//*->/
		y = yFeet / yconv;//*->/
		acceleration = accel;
		velocity = vel;
		this.time = time;
	}

	PointonPath(int xPixels, int yPixels){
		acceleration = 0;
		velocity = 0;
		time = 0;
		this.x = xPixels;
		this.y = yPixels;
		this.i = 0;

		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println(e.getY() + " " + (e.getY()) + " " + (10) + " " + (35));
				if (e.getX() > 295 & e.getX() < 320 & e.getY() > 10 & e.getY() < 35) {
					PathMaker.frame.repaint();
					PathMaker.path.remove(i);
					PathMaker.overlay = new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);
					if (-PathMaker.pointpaneltranslate > PointonPath.h
							* (PathMaker.path.size() - 800 / PointonPath.h + 1)) {
						if (PathMaker.path.size() > 800 / PointonPath.h + 1)
							PathMaker.pointpaneltranslate = -PointonPath.h
									* (PathMaker.path.size() - 800 / PointonPath.h + 1);
						else
							PathMaker.pointpaneltranslate = 0;
					}
					PathMaker.frame.repaint();
				}
				if (e.getX() > 220 & e.getX() < 245 & e.getY() > 10 & e.getY() < 35) {
					highlight = !highlight;
					PathMaker.frame.repaint();
				}
				// System.out.println("horray" + i);
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		PathMaker.pointpanel.add(this);
		colorarraay = new ArrayList<Color>();
		int depth = 15;
		for (int i = 0; i < depth; i++) {
			colorarraay.add(new Color(255 - 255 / depth * i, 0, 255 / depth * i));
		}
		for (int i = depth - 1; i >= 0; i--) {
			colorarraay.add(new Color(255 - 255 / depth * i, 0, 255 / depth * i));
		}
	}

	PointonPath(double xFeet, double yFeet, int colorIndex) {
		acceleration = 0;
		velocity = 0;
		time = 0;
		this.xFeet = xFeet;//PIXELS
		this.yFeet = yFeet;//PIXELS
		this.i = colorIndex;//COLOR?

		//AnchorPoint corner = new AnchorPoint(0, 0);

		this.x = xFeet/xconv;
		this.y = yFeet/yconv;
		

		// xFeet = Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10, precision);
		// yFeet = Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10, precision);
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println(e.getY() + " " + (e.getY()) + " " + (10) + " " + (35));
				if (e.getX() > 295 & e.getX() < 320 & e.getY() > 10 & e.getY() < 35) {
					PathMaker.frame.repaint();
					PathMaker.path.remove(i);
					PathMaker.overlay = new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);
					if (-PathMaker.pointpaneltranslate > PointonPath.h
							* (PathMaker.path.size() - 800 / PointonPath.h + 1)) {
						if (PathMaker.path.size() > 800 / PointonPath.h + 1)
							PathMaker.pointpaneltranslate = -PointonPath.h
									* (PathMaker.path.size() - 800 / PointonPath.h + 1);
						else
							PathMaker.pointpaneltranslate = 0;
					}
					PathMaker.frame.repaint();
				}
				if (e.getX() > 220 & e.getX() < 245 & e.getY() > 10 & e.getY() < 35) {
					highlight = !highlight;
					PathMaker.frame.repaint();
				}
				// System.out.println("horray" + i);
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		PathMaker.pointpanel.add(this);
		colorarraay = new ArrayList<Color>();
		int depth = 15;
		for (int i = 0; i < depth; i++) {
			colorarraay.add(new Color(255 - 255 / depth * i, 0, 255 / depth * i));
		}
		for (int i = depth - 1; i >= 0; i--) {
			colorarraay.add(new Color(255 - 255 / depth * i, 0, 255 / depth * i));
		}
	}

	boolean highlight = false;

	//2019 TODO - Change color based on velocity=======================
	public void paint(Graphics g, int i_) {
		i = i_;
		this.setLocation(0, i * h + PathMaker.pointpaneltranslate);
		this.setSize(335, h);
		g.setColor(Color.BLACK);
		g.drawRect(0, i * h, 334, h);
		g.setFont(new Font("Times New Roman", 200, 32));
		g.drawString(xFeet + "," + yFeet, 0 + 10, i * h + 30);

		g.setColor(Color.green);
		g.fill3DRect(220, 10 + i * h, 25, 25, true);// button to highlight
		g.setColor(Color.cyan);
		g.fill3DRect(245, 10 + i * h, 25, 25, true);// button to pickup
		g.setColor(Color.yellow);
		g.fill3DRect(270, 10 + i * h, 25, 25, true);// button to drop box
		g.setColor(Color.red);
		g.fill3DRect(295, 10 + i * h, 25, 25, true);// button to delete
		if (i == 0) {
			g.setColor(Color.green);
			g.fillOval(170, 10 + i * h, 30, 30);
		} else {
			g.setColor(colorarraay.get(i % colorarraay.size()));
			g.fillOval(175, 15 + i * h, 20, 20);
		}
		if (i != PathMaker.path.size() - 1) {
			Graphics2D g2d = (Graphics2D) PathMaker.overlay.getGraphics();
			g2d.setColor(colorarraay.get(i % colorarraay.size()));
			g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.drawLine((int) PathMaker.path.get(i).x, (int) PathMaker.path.get(i).y,(int) PathMaker.path.get(i + 1).x, (int) PathMaker.path.get(i + 1).y);
			if (highlight) {
				g2d.setColor(Color.green);
				g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g2d.drawLine((int) PathMaker.path.get(i).x, (int) PathMaker.path.get(i).y,(int) PathMaker.path.get(i + 1).x, (int) PathMaker.path.get(i + 1).y);
			}
		}

	}

	public double getPosition() {
		return position;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public double getAccel() {
		return acceleration;
	}
	
	public double getTime() {
		return time;
	}

}
