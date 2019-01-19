package org.usfirst.frc.team2849.path;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double x;
	protected double y;
	protected double xft;// convert pixels to feet, look up conversion , field is 54/27ft.
	protected double yft;
	static final double xconv = 27d/* ft */ / 400/* pixels */;
	static final double yconv = 54d/* ft */ / 800/* pixels */;
	int precision = 2;
	int i;
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
				+ String.format(" %-12f  ", acceleration) + String.format(" %-9f  ", Math.round(xft * 100) / 100.0)
				+ String.format(" %-9f ", Math.round(yft * 100) / 100.0);

	}
	// bottons to impliment{
	// highlight segment
	// drop box
	// pick up box

	// the ability to insert points not from the end
	PointonPath(double pos, double dir, double xft_, double yft_) {
		position = pos;
		direction = dir;
		xft = xft_;// Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10, precision);
		yft = yft_;// Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10, precision);
		x = xft / (Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10, precision));
		y = yft / (Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10, precision));
		acceleration = 0;
		velocity = 0;
		time = 0;
		// x = x_;
		// y = y_;
		// xft = Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10,
		// precision);
		// yft = Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10,
		// precision);
	}

	public PointonPath(double pos, double dir, double xft_, double yft_, double time, double vel, double accel) {
		position = pos;
		direction = dir;
		xft = xft_; // Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10, precision);
		yft = yft_; // Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10, precision);
		x = xft / xconv;
		y = yft / yconv;
		acceleration = accel;
		velocity = vel;
		this.time = time;
	}

	PointonPath(double x_, double y_, int i_) {
		acceleration = 0;
		velocity = 0;
		time = 0;
		x = x_;
		y = y_;
		i = i_;
		xft = Math.floor(x * xconv * Math.pow(10, precision)) / Math.pow(10, precision);
		yft = Math.floor(y * yconv * Math.pow(10, precision)) / Math.pow(10, precision);
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// System.out.println(e.getY() + " " + (e.getY()) + " " + (10) + " " + (35));
				if (e.getX() > 295 & e.getX() < 320 & e.getY() > 10 & e.getY() < 35&i!=0) {
					PathMakerHasButtonsButMayNotBeWorking.frame.repaint();
					PathMakerHasButtonsButMayNotBeWorking.path.remove(i);
					PathMakerHasButtonsButMayNotBeWorking.overlay = new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);
					if (-PathMakerHasButtonsButMayNotBeWorking.pointpaneltranslate > PointonPath.h
							* (PathMakerHasButtonsButMayNotBeWorking.path.size() - 800 / PointonPath.h + 1)) {
						if (PathMakerHasButtonsButMayNotBeWorking.path.size() > 800 / PointonPath.h + 1)
							PathMakerHasButtonsButMayNotBeWorking.pointpaneltranslate = -PointonPath.h
									* (PathMakerHasButtonsButMayNotBeWorking.path.size() - 800 / PointonPath.h + 1);
						else
							PathMakerHasButtonsButMayNotBeWorking.pointpaneltranslate = 0;
					}
					PathMakerHasButtonsButMayNotBeWorking.frame.repaint();
				}
				if (e.getX() > 220 & e.getX() < 245 & e.getY() > 10 & e.getY() < 35) {
					highlight = !highlight;
					PathMakerHasButtonsButMayNotBeWorking.frame.repaint();
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
		PathMakerHasButtonsButMayNotBeWorking.pointpanel.add(this);
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

	public void paint(Graphics g, int i_) {
		i = i_;
		this.setLocation(0, i * h + PathMakerHasButtonsButMayNotBeWorking.pointpaneltranslate);
		this.setSize(335, h);
		g.setColor(Color.BLACK);
		g.drawRect(0, i * h, 334, h);
		g.setFont(new Font("Times New Roman", 200, 32));
		g.drawString(xft + "," + yft, 0 + 10, i * h + 30);

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
		if (i != PathMakerHasButtonsButMayNotBeWorking.path.size() - 1) {
			Graphics2D g2d = (Graphics2D) PathMakerHasButtonsButMayNotBeWorking.overlay.getGraphics();
			g2d.setColor(colorarraay.get(i % colorarraay.size()));
			g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.drawLine((int) PathMakerHasButtonsButMayNotBeWorking.path.get(i).x, (int) PathMakerHasButtonsButMayNotBeWorking.path.get(i).y,
					(int) PathMakerHasButtonsButMayNotBeWorking.path.get(i + 1).x, (int) PathMakerHasButtonsButMayNotBeWorking.path.get(i + 1).y);
			if (highlight) {
				g2d.setColor(Color.green);
				g2d.setStroke(new BasicStroke(7, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g2d.drawLine((int) PathMakerHasButtonsButMayNotBeWorking.path.get(i).x, (int) PathMakerHasButtonsButMayNotBeWorking.path.get(i).y,
						(int) PathMakerHasButtonsButMayNotBeWorking.path.get(i + 1).x, (int) PathMakerHasButtonsButMayNotBeWorking.path.get(i + 1).y);
			}
		}

	}
	
	public void setDirection(double dir) {
		this.direction = dir;
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

	public double getXInches() {
		return xft * 12;
	}
	
	public double getYInches() {
		return yft * 12;
	}
	
}
