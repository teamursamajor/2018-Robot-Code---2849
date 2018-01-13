import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PathMaker {
	// frame
	// menu
	// 2d map of field
	// drop down menu of preset autos
	// options to draw line or click points to define path
	// also sidemenu with all current points, which can be deleted with red x
	// output to text file.
	public static void main(String[] argsokcharlie) {
		PathMaker.init();
	}
	static JFrame frame;
	static String[] presets = new String[] {"auto1","auto2","auto3","auto4"};
	static BufferedImage field;
	static BufferedImage overlay;
	
	//types of things
	//starting
	//turning --probably not
	//putting down box
	//picking up a box---maybe
	
	//actually a scrollbar
	
	//writing drawn to file
	//loading from file
	//find format from charlie
	
	//label the points by number so you can delete specific ones
	//be able to insert points at specific locations
	
	static ArrayList<PointonPath> path = new ArrayList<PointonPath>();
	static void init() {
		frame = new JFrame();
		frame.setLayout(null);
		frame.setSize(1000,900);
		JPanel presetpanel = new JPanel();
		frame.add(presetpanel);
		JComboBox preset = new JComboBox(presets);
		presetpanel.add(preset);
		presetpanel.setSize(200,50);
		presetpanel.setLocation(0, 50);
		preset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(preset.getSelectedItem());
			}
		});
		try {
			field = ImageIO.read(new File("Z:/git/2018-Robot-Code---2849/Pathmaker/field2.png"));
		}catch(Exception E) {E.printStackTrace();}
		JPanel feildPanel = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(field, 0, 0, 450, 900, null);
				g.drawImage(overlay, 0, 0, 450, 900, null);
				for(int i=0;i<path.size()-1;i++) {
					Graphics2D g2d = (Graphics2D)g;
					g2d.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
					g2d.drawLine((int)path.get(i).x,(int)path.get(i).y,(int)path.get(i+1).x,(int)path.get(i+1).y);
				}
			}
		};
		feildPanel.setSize(450, 900);
		overlay=new BufferedImage(450,900,2);
		feildPanel.setLocation(200,0);
		feildPanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if(once) {
					prev=new int[] {e.getX(), e.getY()};
					once=false;
				}
				if(slow%8==0) {
					path.add(new PointonPath(e.getX(), e.getY()));
					frame.repaint();
				}
				slow++;slow%=8;
			}
			public void mouseMoved(MouseEvent e) {}
		});
		feildPanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				once=true;
			}
		});
		frame.add(feildPanel);
		
		JPanel pointpanel = new JPanel() {
			public void paint(Graphics g) {
				g.translate(0, pointpaneltranslate);
				for(int i=0;i<path.size();i++) {
					g.setColor(Color.BLACK);
					g.drawRect(0,i*75,310,75);
					g.setFont(new Font("Times New Roman",200,32));
					g.drawString(path.get(i).xft+","+path.get(i).yft, 0+10, i*75+40);
					path.get(i).setLocation(0,i*75);
					path.get(i).setSize(310,75);
					g.setColor(Color.red);
					g.draw3DRect(275, 10+i*75, 25, 25, true);
				}
			}
		};
		pointpanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int y=e.getY()+10-pointpaneltranslate;
				if(y%75>10&y%75<25) {
					path.remove(y/75);
				}
				frame.repaint();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				once2=true;
			}
		});
		pointpanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if(once2) {
					prevscroll=e.getY();
					once2=false;
				}
				pointpaneltranslate -= -e.getY()+prevscroll;
				prevscroll=e.getY();
				if(pointpaneltranslate>0) {
					pointpaneltranslate=0;
				}
				if(pointpaneltranslate<-75*(path.size()-11)) {
					pointpaneltranslate=-75*(path.size()-11);
				}
				frame.repaint();
			}
			public void mouseMoved(MouseEvent e) {}
		});
		pointpanel.setLocation(650,0);
		pointpanel.setSize(350, 900);
		frame.add(pointpanel);
		frame.setVisible(true);
	}
	static int slow=0;
	static int[] prev=new int[] {0,0};//for mouse motion ignore
	static boolean once = true;//for mouse motion ignore

	static int prevscroll= 0;
	static boolean once2=true;
	static int pointpaneltranslate = 0;
}
