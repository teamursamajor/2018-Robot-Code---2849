import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
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
	static void init() {
		frame = new JFrame();
		frame.setLayout(null);
		frame.setSize(1000,900);
		JPanel presetpanel = new JPanel();
		frame.add(presetpanel);
		JComboBox preset = new JComboBox(presets);
		presetpanel.add(preset);
		presetpanel.setSize(100,50);
		presetpanel.setLocation(100, 50);
		preset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(preset.getSelectedItem());
			}
		});
		JPanel feildPanel = new JPanel() {
			
			public void paint(Graphics g) {
				g.fillRect(0, 0, 100,100);
			}
		};
		feildPanel.setSize(500,500);
		feildPanel.setLocation(200,0);
		frame.add(feildPanel);
		
		frame.setVisible(true);
	}
}
