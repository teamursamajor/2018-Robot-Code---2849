package com.teamursamajor.auto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

//SAVE ME BEFORE

//import javafx.scene.shape.Box;


//import org.usfirst.frc.team2849.path.PathMaker;
//import org.usfirst.frc.team2849.path.PointonPath;
//
//import org.usfirst.frc.team2849.path.PathMaker;
//import org.usfirst.frc.team2849.path.PointonPath;

public class PathMaker {
	// take points accumulate dist between for path length
	// angle from arctan
	// to get left and right, add left and right perpendicular to heading
	// be able to insert points at specific locations
	
	//load a path to display

	public static void main(String[] argsokcharlie) {
		//System.out.println(System.getPr);
		PathMaker.init();
	}
	static JFrame frame;
	static String[] presets = new String[] { "auto1", "auto2", "auto3", "auto4" };
	static BufferedImage field;//picture of field
	static BufferedImage overfield;//picture of obstacles
	//Dimensions of field in anchor points
	static BufferedImage overlay = new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);//image drawn on to make path
	
	static JPanel pointpanel;
	static ArrayList<PointonPath> path = new ArrayList<PointonPath>();

	static int slow = 0;
	
	static int[] prev = new int[] { 0, 0 };// for mouse motion ignore
	static boolean once = true;// for mouse motion ignore
	static boolean once2 = true;// for mouse motion ignore
	static int prevscroll = 0;
	static int pointpaneltranslate = 0;
	
	
	public static void output() {
		double totalDist = 0;
		ArrayList<PointonPath> output = new ArrayList<PointonPath>();
		output.add(
				new PointonPath(totalDist,
						negmod(Math.atan2(path.get(1).yft - path.get(0).yft,
								path.get(1).xft - path.get(0).xft), Math.PI * 2) * (180 / Math.PI),
						path.get(0).xft, path.get(0).yft));
		for (int i = 1; i < path.size(); i++) {
			totalDist += Math.sqrt(Math.pow(path.get(i).xft - path.get(i - 1).xft, 2)
					+ Math.pow(path.get(i).yft - path.get(i - 1).yft, 2));
			output.add(
					new PointonPath(totalDist,
							negmod(Math.atan2(path.get(i).yft - path.get(i - 1).yft,
									path.get(i).xft - path.get(i - 1).xft), Math.PI * 2) * (180 / Math.PI),
							path.get(i).xft, path.get(i).yft));
		}
		Path mapped = new Path("output", output);
		new PathWriter(new Path[] {mapped, mapped}, "outpath.txt");
		mapped.mapVelocity();
		new PathWriter(new Path[] {mapped, mapped}, "outmapped.txt");
		new PathWriter(mapped.separate(1.067), "outsepped.txt");
		System.out.println("outputed");
	}
	//TODO- Change
	static ArrayList<PointonPath> pathl = new ArrayList<PointonPath>();
	static ArrayList<PointonPath> pathr = new ArrayList<PointonPath>();
	public static void input() {
		pathl = new PathReader("outsepped.txt",false).getLeftPath().getPoints();
		pathr = new PathReader("outsepped.txt",false).getRightPath().getPoints();
		//path is average
		//pathl and pathr are sides
		//button and boolean to switch
		ArrayList<PointonPath> copy = new ArrayList<PointonPath>();
		for(int i=0;i<pathl.size();i++) {
			copy.add(new PointonPath((pathl.get(i).x+pathr.get(i).x)/2,(pathl.get(i).y+pathr.get(i).y)/2,copy.size()));
		}
		path=copy;
		PathMaker.overlay=new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);
		pointpaneltranslate = 0;
		frame.repaint();
		frame.dispatchEvent(new MouseWheelEvent (frame,0,0,0,0,0,0,false, 3,1,0));
	}
	
	
	
// INIT FUNCTION=====================	
	static void init() {
		frame = new JFrame() ;
		//ImageIcon icon = new ImageIcon(new File(System.getProperty("user.dir") + "/../2019field(transparent).png"));
		File f = new File((System.getProperty("user.dir") + "/../Icon.png"));
		System.out.println(f.toString());
		frame.setIconImage(new ImageIcon(f.toString()).getImage());
		frame.setLayout(null);//WAS: null
		frame.setSize(1000, 850);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				//for debugging
//				System.out.println(e.getModifiers());
//				System.out.println(e.getClickCount());
//				System.out.println(e.getScrollType());
//				System.out.println(e.getScrollAmount());
//				System.out.println(e.getWheelRotation());
				double sig = e.getWheelRotation();
				pointpaneltranslate -= sig * 1.2 * Math.PI / 4 * PointonPath.h;
				if (pointpaneltranslate > 0) {
					pointpaneltranslate = 0;
				}
				if (-pointpaneltranslate > PointonPath.h * (path.size() - 800 / PointonPath.h + 1)) {
					if (path.size() > 800 / PointonPath.h + 1)
						pointpaneltranslate = -PointonPath.h * (path.size() - 800 / PointonPath.h + 1);
					else
						pointpaneltranslate = 0;
				}
				frame.repaint();
			}
		});
		
		importImages();
		
		
		
		
		
		
		
		
		
		
		
		//PRESET PANEL===========
		JPanel presetpanel = new JPanel();
		frame.add(presetpanel);
		//PRESETS  ARRAY
		
		presetpanel.setSize(200, 850);//200, 50
		presetpanel.setLocation(0, 50);
//		preset.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(preset.getSelectedItem());
//			}
//		});
		
		JPanel fieldPanel = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(field, 0, 0, 400, 800, null);
				g.drawImage(overlay, 0, 0, 400, 800, null);
				g.drawImage(overfield, 0, 0, 400, 800, null);
			}
		};
		frame.add(fieldPanel);
		fieldPanel.setSize(400, 850);
		fieldPanel.setLocation(200, 0);
		fieldPanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if (once) {
					prev = new int[] { e.getX(), e.getY() };
					once = false;
				}
				if (slow % 2 == 0) {
					path.add(new PointonPath(e.getX(), e.getY(), path.size()));
					frame.repaint();
				}
				slow++;
				slow %= 2;
			}
			public void mouseMoved(MouseEvent e) {}
		});
		fieldPanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				once = true;
				frame.repaint();
			}
		});
		
		JPanel Scrollpanel = new JPanel() {
			public void paint(Graphics g) {
				if (path.size() > 0 & PointonPath.h * (path.size() - 800 / PointonPath.h + 1) != 0) {
					int h = 800 / path.size() * (800 / PointonPath.h);
					g.fillRect(0, -pointpaneltranslate * 800 / (PointonPath.h * path.size()), 50, h);
				}
			}
		};
		frame.add(Scrollpanel);
		Scrollpanel.setSize(50, 800);
		Scrollpanel.setLocation(950, 50);
		Scrollpanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				once2 = true;
			}
		});
		Scrollpanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if (once2) {
					prevscroll = e.getY();
					once2 = false;
				}
				pointpaneltranslate += -e.getY() + prevscroll;
				prevscroll = e.getY();
				if (pointpaneltranslate > 0) {
					pointpaneltranslate = 0;
				}
				if (-pointpaneltranslate > PointonPath.h * (path.size() - 800 / PointonPath.h + 1)) {
					if (path.size() > 800 / PointonPath.h + 1)
						pointpaneltranslate = -PointonPath.h * (path.size() - 800 / PointonPath.h + 1);
					else
						pointpaneltranslate = 0;
				}
				frame.repaint();
			}
			public void mouseMoved(MouseEvent e) {}
		});
		
		pointpanel = new JPanel() {
			public void paint(Graphics g) {
				g.translate(0, pointpaneltranslate);
				g.setColor(Color.white);
				g.fillRect(0, 0, 325, PointonPath.h * path.size());
				for (int i = 0; i < path.size(); i++) {
					path.get(i).paint(g, i);
				}
			}
		};
		frame.add(pointpanel);
		pointpanel.setLocation(625, 50);
		pointpanel.setSize(325, 800);
		
		//Output Read Clear
		//TODO -  add text book for read/output - file name
		JPanel menupanel = new JPanel() {
			public void paint(Graphics g) {
				g.fillRect(0, 0, 325, 50);
				
				g.setColor(Color.white);
				g.fill3DRect(10, 10, 50, 30, true);
				g.setColor(Color.black);
				g.drawString("Output",15,30);
				
				g.setColor(Color.white);
				g.fill3DRect(70, 10, 50, 30, true);
				g.setColor(Color.black);
				g.drawString("Read",75,30);
				
				g.setColor(Color.white);
				g.fill3DRect(130, 10, 50, 30, true);
				g.setColor(Color.black);
				g.drawString("Clear", 135, 30);
			}
		};
		frame.add(menupanel);
		menupanel.setSize(325, 50);
		menupanel.setLocation(625, 0);
		menupanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getX() > 10 & e.getX() < 60 & e.getY() > 10 & e.getY() < 40) {
					output();
				}
				if (e.getX() > 70 & e.getX() < 70+50 & e.getY() > 10 & e.getY() < 40) {
					input();
				}
				if (e.getX() > 130 & e.getX() < 130 + 50 & e.getY() > 10 & e.getY() < 40) {
					clearpath();
				}
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		frame.setVisible(true);
	
	
	
	
	
	
	//BUTTONS
/*Points: (2019)
 * A - input hatch
 * B - high starting platforms
 * C/D - regular starting positions
 * E - rocket
 * F - front facing cargo bay
 * G - side cargo bays
 * 
 * 
 * 		L			R
 * A-----B---------B-----A
 * ------C----D----C------
 * -----------------------
 * E---------F-F---------E
 * ---------G---G---------
 * E--------G---G--------E
 * ---------G---G---------	
 */
	
	/*
	 * L - Left
	 * R - Right
	 * T - Top
	 * M - Middle
	 * B - Bottom
	 */
		
		//TODO - change icon
	
	
	
	JButton startPlatformRT = new JButton("Right Raised Platform");
	JButton startPlatformRB = new JButton("Right Lower Platform");
	JButton startPlatformLT = new JButton("Left Raised Platform");
	JButton startPlatformLB = new JButton("Left Bottom Platform");
	JButton startMiddlePlatform = new JButton("Bottom Middle Platform");
	JButton startHatchIntakeR = new JButton("Right Hatch Intake");//FIX TEXT CUT OFF
	JButton startHatchIntakeL = new JButton("Left Hatch Intake");
	JButton startRocketRT = new JButton("Top Right Rocket");
	JButton startRocketRB = new JButton("Bottom Right Rocket");
	JButton startRocketLT = new JButton("Top Left Rocket");
	JButton startRocketLB = new JButton("Bottom Left Rocket");
	JButton startCargoBayFrontR = new JButton("Front Right Cargo Bay Door");
	JButton startCargoBayFrontL = new JButton("Front Left Cargo Bay Door");
	JButton startCargoBayRT = new JButton("Top Right Cargo Bay Door");
	JButton startCargoBayRM = new JButton("Middle Right Cargo Bay Door");
	JButton startCargoBayRB = new JButton("Bottom Right Cargo Bay Door");
	JButton startCargoBayLT = new JButton("Top Left Cargo Bay Door");
	JButton startCargoBayLM = new JButton("Middle Left Cargo Bay Door");
	JButton startCargoBayLB = new JButton("Bottom Left Cargo Bay Door");
	
	//Original code - .setBounds(30, 165+20n, 150, 15)
	//n = number of buttons
	startPlatformRT = toSqrButton(startPlatformRT);
	startPlatformRB = toSqrButton(startPlatformRB);
	startPlatformLT = toSqrButton(startPlatformLT);
	startPlatformLB = toSqrButton(startPlatformLB);
	startMiddlePlatform = toSqrButton(startMiddlePlatform);
	startHatchIntakeR = toSqrButton(startHatchIntakeR);
	startHatchIntakeL = toSqrButton(startHatchIntakeL);
	startRocketRT = toSqrButton(startRocketRT);
	startRocketRB = toSqrButton(startRocketRB);
	startRocketLT = toSqrButton(startRocketLT);//105,205,75,15
	startRocketLB = toSqrButton(startRocketLB);
	startCargoBayFrontR = toSqrButton(startCargoBayFrontR);
	startCargoBayFrontL = toSqrButton(startCargoBayFrontL);
	startCargoBayRT = toSqrButton(startCargoBayRT);
	startCargoBayRM = toSqrButton(startCargoBayRM);
	startCargoBayRB = toSqrButton(startCargoBayRB);
	startCargoBayLT = toSqrButton(startCargoBayLT);
	startCargoBayLM = toSqrButton(startCargoBayLM);
	startCargoBayLB = toSqrButton(startCargoBayLB);
	//TODO - add text box that says ===Cargo Bay===
	
	
	//Adds buttons to preset panel - TODO - turn to array/list
	JLabel label1 = new JLabel("====Starting Areas====");
	presetpanel.add(label1);
	presetpanel.add(startPlatformRT);
	presetpanel.add(startPlatformLT);
	presetpanel.add(startPlatformRB);
	presetpanel.add(startPlatformLB);
	presetpanel.add(startMiddlePlatform);
	JLabel label2 = new JLabel("==Hatch Intake Station==");
	presetpanel.add(label2);
	presetpanel.add(startHatchIntakeR);
	presetpanel.add(startHatchIntakeL);
	JLabel label3 = new JLabel("=======Rocket=======");
	presetpanel.add(label3);
	presetpanel.add(startRocketRT);
	presetpanel.add(startRocketLT);
	presetpanel.add(startRocketRB);
	presetpanel.add(startRocketLB);
	JLabel label4 = new JLabel("======Cargo Bay======");
	presetpanel.add(label4);
	presetpanel.add(startCargoBayFrontR);
	presetpanel.add(startCargoBayFrontL);
	presetpanel.add(startCargoBayLM);
	presetpanel.add(startCargoBayRT);
	presetpanel.add(startCargoBayLT);
	presetpanel.add(startCargoBayRM);
	presetpanel.add(startCargoBayLM);
	presetpanel.add(startCargoBayRB);
	presetpanel.add(startCargoBayLB);
	
	//frame.pack();
	
	//Sets start point positions
	//TODO - LOOK UP ACTUALL POINTS

	
	//Sets anchor points
	/*Points: (2019)
	 * A - input hatch
	 * B - high starting platforms
	 * C/D - regular starting positions
	 * E - rocket
	 * F - front facing cargo bay
	 * G - side cargo bays
	 * 
	 * 
	 * 		L			R
	 * A-----B---------B-----A
	 * ------C----D----C------
	 * -----------------------
	 * E---------F-F---------E
	 * ---------G---G---------
	 * E--------G---G--------E
	 * ---------G---G---------	
	 */
		
	
//	AnchorPoint startPlatforms = new AnchorPoint();//D
//	AnchorPoint startHatchIntakes = new AnchorPoint();//LA
	AnchorPoint startRockets = new AnchorPoint(0, 19.5233333333);//Left E down
//	AnchorPoint startCargoBay = new AnchorPoint();// Left middle G

	
	
	//Action for Starting Area Buttons
	//High
	
	//Low
	
	
	//Action for  Hatch Intake Buttons   
	startHatchIntakeR.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			//debug("Right Hatch Intake Button was Pressed");
			
			path.clear();
//			path.add(new PointonPath(startAtHatches.getX(),
//									 startAtHatches.getY(),
//									 0));
			PathMaker.overlay = new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);
			frame.repaint();
		}
	});
	startHatchIntakeL.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			path.clear();
//			path.add(new PointonPath(flipByYAxis(startAtHatches.getX()),
//									 startAtHatches.getY(),
//									 0));
			resetFrame();
		}
	});
	
	
	//Action for Rocket Buttons
	startRocketRT.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			path.clear();
				path.add(new PointonPath(startRockets.getX(),
										 startRockets.getY(),
										 0));
			resetFrame();
		}
	});
	
	//Action for Cargo Bay Buttons
	
	
	//DESCRIPTION
	JComboBox preset = new JComboBox(presets);
	//presetpanel.add(preset);//AUTO BUTTON
	preset.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			
		}
	});
	frame.setVisible(true);
	
	//SCROLL TESTING
	
	
	
	
	//TODO - add scrolling functionality to preset? - look at white bar on side
	
	} // end of init function ==============================
	

	
	//TODO - delete
	//A---|---- -> ----|---A
	public static double flipByYAxis(double xCoord){
		/*
		 * x = frame width - x
		 * return x
		 */
		
		double res = 400 - xCoord;
		return res;
	}
	
	/*
	 * A    |
	 * |    |
	 * - -> -
	 * |    |
	 * |    A
	 */
	public static void flipByXAxis(int x, int y, int inflectionPoint){
		/*
		 * int distFromInflectionPoint = inflectionPoint - y
		 * int newYCord = inflectionPoint + distFromInflectionPoint
		 * return newYCord
		 */
	}
	
	public static void moveX (int dist){
		/*
		 * xCoordinate + dist
		 */
	}
	
	public static void moveY (int dist){
		/*
		 * yCoordinate + dist
		 */
	}
	

//Clears path
	public static void clearpath() {
		for (int i = 1; i < path.size();) {
			PathMaker.frame.repaint();
			PathMaker.path.remove(i);
			PathMaker.overlay = new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);
			if (-PathMaker.pointpaneltranslate > PointonPath.h * (PathMaker.path.size() - 800 / PointonPath.h + 1)) {
				if (PathMaker.path.size() > 800 / PointonPath.h + 1)
					PathMaker.pointpaneltranslate = -PointonPath.h * (PathMaker.path.size() - 800 / PointonPath.h + 1);
				else
					PathMaker.pointpaneltranslate = 0;
			}
		}
		frame.repaint();
	}
	
	public static void resetFrame() {
		PathMaker.overlay = new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);
		frame.repaint();
	}
	
	@SuppressWarnings("deprecation")
	public static JButton toSqrButton (JButton b){
		String str = "<html>" + b.getLabel();
		b.setLabel(str);
		Border blackline = BorderFactory.createLineBorder(Color.BLACK);
		
		b.setBorder(blackline);
		
		b.setPreferredSize(new Dimension(75,40));
		
		b.setFont(new Font("Helvetica", Font.BOLD, 9));
		
		return b;
	}


	
	private static double negmod(double atan2, double modvalue) {
		while (atan2 < 0) {
			atan2 += modvalue;
		}
		return atan2 % modvalue;
	}
	
//TODO- Add a function to convert feet to pixels
	
//Import Images =============================
	private static void importImages() {
		System.out.println("Importing Images?");
		try {
			field = ImageIO.read(new File(System.getProperty("user.dir") + "/../2019field.jpeg"));
			overfield = ImageIO.read(new File(System.getProperty("user.dir") + "/../2019field(transparent).png"));
	
		} catch (Exception E) {
			E.printStackTrace();
		}
		System.out.println("Images Imported!");
	}
	
	private static void debug (String str){
		System.out.println("DEBUGGING: " + str);
	}
}
//19.5233333333ft = yval of middle of spaceship hopefully

//cd src/com/teamursamajor/auto