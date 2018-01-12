import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

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
	
	static int[] prev=new int[] {0,0};//for mouse motion ignore
	static boolean once = true;//for mouse motion ignore
	//types of things
	//starting
	//turning --probably not
	//putting down box
	//picking up a box---maybe
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
		feildPanel.setLocation(650,0);
		feildPanel.setSize(1000-650, 900);
		frame.add(feildPanel);
		
		JPanel pointpanel = new JPanel() {
			public void paint(Graphics g) {
				
			}
		};
		frame.add(pointpanel);
		frame.setVisible(true);
	}
}
