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
	
	//writing drawn to file
	//loading from file
	//find format from charlie
	
	//label the points by number so you can delete specific ones
	//be able to insert points at specific locations
	static JPanel pointpanel;
	static ArrayList<PointonPath> path = new ArrayList<PointonPath>();
	static void init() {
		frame = new JFrame() {
			public void repaint() {
				super.repaint();
				overlay=new BufferedImage(400,800,BufferedImage.TYPE_4BYTE_ABGR);
			}
		};
		frame.setLayout(null);
		frame.setSize(1000,850);
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
				g.drawImage(field, 0, 0, 400, 800, null);
				g.drawImage(overlay, 0, 0, 400, 800, null);
//				for(int i=0;i<path.size()-1;i++) {
//					Graphics2D g2d = (Graphics2D)g;
//					g2d.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
//					g2d.drawLine((int)path.get(i).x,(int)path.get(i).y,(int)path.get(i+1).x,(int)path.get(i+1).y);
//				}
			}
		};
		feildPanel.setSize(450, 850);
		overlay=new BufferedImage(400,800,2);
		feildPanel.setLocation(200,0);
		feildPanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if(once) {
					prev=new int[] {e.getX(), e.getY()};
					once=false;
				}
				if(slow%8==0) {
					path.add(new PointonPath(e.getX(), e.getY(),path.size()-1));
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
				frame.repaint();
			}
		});
		frame.add(feildPanel);
		
		JPanel Scrollpanel = new JPanel() {
			public void paint(Graphics g) {
				if(path.size()>0&PointonPath.h*(path.size()-800/PointonPath.h+1)!=0) {
					int h=800/path.size()*(800/PointonPath.h);
					g.fillRect(0, -pointpaneltranslate*800/(PointonPath.h*path.size()), 50, h);
				}
			}
		};
		Scrollpanel.setSize(50, 800);
		Scrollpanel.setLocation(950,50);
		pointpanel = new JPanel() {
			public void paint(Graphics g) {
				g.translate(0, pointpaneltranslate);
				g.setColor(Color.white);
				g.fillRect(0, 0, 325, PointonPath.h*path.size());
				for(int i=0;i<path.size();i++) {
					path.get(i).paint(g, i);
				}
			}
		};
		Scrollpanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				once2=true;
			}
		});
		Scrollpanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if(once2) {
					prevscroll=e.getY();
					once2=false;
				}
				pointpaneltranslate += -e.getY()+prevscroll;
				prevscroll=e.getY();
				if(pointpaneltranslate>0) {
					pointpaneltranslate=0;
				}
				if(-pointpaneltranslate>PointonPath.h*(path.size()-800/PointonPath.h+1)) {
					if(path.size()>800/PointonPath.h+1)
						pointpaneltranslate=-PointonPath.h*(path.size()-800/PointonPath.h+1);
					else
						pointpaneltranslate=0;
				}
				frame.repaint();
			}
			public void mouseMoved(MouseEvent e) {}
		});
		pointpanel.setLocation(625,50);
		pointpanel.setSize(325, 800);
		frame.add(pointpanel);
		frame.add(Scrollpanel);
		frame.setVisible(true);
	}
	static int slow=0;
	static int[] prev=new int[] {0,0};//for mouse motion ignore
	static boolean once = true;//for mouse motion ignore

	static int prevscroll= 0;
	static boolean once2=true;
	static int pointpaneltranslate = 0;
}
