import java.awt.BasicStroke;
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
	// 2d map of feild
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
			field = ImageIO.read(new File("Z:/git/2018-Robot-Code---2849/Pathmaker/field.png"));
		}catch(Exception E) {E.printStackTrace();}
		JPanel feildPanel = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(field, 0, 0, 450, 900, null);
				g.drawImage(overlay, 0, 0, 450, 900, null);
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
				Graphics2D g2d = (Graphics2D)overlay.getGraphics();
				g2d.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				g2d.drawLine(e.getX(), e.getY(), prev[0], prev[1]);
				prev=new int[] {e.getX(), e.getY()};
				path.add(new PointonPath(e.getX(), e.getY()));
				frame.repaint();
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
					g.drawRect(0,i*75,310,75);
					g.setFont(new Font("Times New Roman",200,32));
					g.drawString(path.get(i).xft+","+path.get(i).yft, 0+10, i*75+40);
				}
			}
		};
		pointpanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
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
				frame.repaint();
			}
			public void mouseMoved(MouseEvent e) {}
		});
		pointpanel.setLocation(650,0);
		pointpanel.setSize(350, 900);
		frame.add(pointpanel);
		frame.setVisible(true);
	}
	
	static int[] prev=new int[] {0,0};//for mouse motion ignore
	static boolean once = true;//for mouse motion ignore

	static int prevscroll= 0;
	static boolean once2=true;
	static int pointpaneltranslate = 0;
}
