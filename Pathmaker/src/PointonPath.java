import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class PointonPath extends JPanel{
	protected double x;
	protected double y;
	protected double xft;//convert pixels to feet, look up conversion , field is 54/27ft.
	protected double yft;
	double xconv=27d/*ft*//400/*pixels*/;
	double yconv=54d/*ft*//800/*pixels*/;
	int precision =2;
	int i;
	String message = "";
	static int h=50;
	
	//bottons to impliment{
	// highlight segment
	// drop box
	// 
	PointonPath(double x_,double y_,int i_){
		x=x_;
		y=y_;
		i=i_;
		xft=Math.floor(x*xconv*Math.pow(10, precision))/Math.pow(10, precision);
		yft=Math.floor(y*yconv*Math.pow(10, precision))/Math.pow(10, precision);
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(e.getX()>270&e.getX()<315&e.getY()>10&e.getY()<35) {
					PathMaker.frame.repaint();
					PathMaker.path.remove(i);
					PathMaker.frame.repaint();
				}
				System.out.println("horray"+i);
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		PathMaker.pointpanel.add(this);
	}
	public void paint(Graphics g,int i_) {
		i=i_;
		this.setLocation(0,i*h);
		this.setSize(310,h);
		g.setColor(Color.BLACK);
		g.drawRect(0,i*h,299,h);
		g.setFont(new Font("Times New Roman",200,32));
		g.drawString(xft+","+yft, 0+10, i*h+30);
		g.setColor(Color.red);
		g.fill3DRect(270, 10+i*h, 25, 25, true);//button to delete
		
	}
	
}
