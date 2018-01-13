import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	// pick up box
	
	//the ability to insert points not from the end
	PointonPath(double x_,double y_,int i_){
		x=x_;
		y=y_;
		i=i_;
		xft=Math.floor(x*xconv*Math.pow(10, precision))/Math.pow(10, precision);
		yft=Math.floor(y*yconv*Math.pow(10, precision))/Math.pow(10, precision);
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(e.getX()>295&e.getX()<320&e.getY()>10&e.getY()<35) {
					PathMaker.frame.repaint();
					PathMaker.path.remove(i);
					PathMaker.frame.repaint();
				}
				if(e.getX()>220&e.getX()<245&e.getY()>10&e.getY()<35) {
//					stupid = !stupid;
//					if(stupid) {
						highlight=!highlight;
//					}
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
	boolean highlight=false;
	boolean stupid = false;
	public void paint(Graphics g,int i_) {
		i=i_;
		this.setLocation(0,i*h);
		this.setSize(335,h);
		g.setColor(Color.BLACK);
		g.drawRect(0,i*h,334,h);
		g.setFont(new Font("Times New Roman",200,32));
		g.drawString(xft+","+yft, 0+10, i*h+30);
		
		g.setColor(Color.green);
		g.fill3DRect(220, 10+i*h, 25, 25, true);//button to highlight
		g.setColor(Color.cyan);
		g.fill3DRect(245, 10+i*h, 25, 25, true);//button to pickup
		g.setColor(Color.yellow);
		g.fill3DRect(270, 10+i*h, 25, 25, true);//button to drop box
		g.setColor(Color.red);
		g.fill3DRect(295, 10+i*h, 25, 25, true);//button to delete
		if(i==0) {
			g.setColor(Color.green);
			g.fillOval(170, 10+i*h, 30, 30);
		}else {
			if(i%5==0) 
				g.setColor(new Color(255,0,0));
			else if (i%5==1) g.setColor(new Color(209, 76, 15));
			else if (i%5==2) g.setColor(new Color(171, 140, 28));
			else if (i%5==3) g.setColor(new Color(133, 204, 41));
			else if (i%5==4) g.setColor(new Color(102, 255, 51));
			
			g.fillOval(175, 15+i*h, 20, 20);
		}
		if(i!=PathMaker.path.size()-1) {
			Graphics2D g2d = (Graphics2D)PathMaker.overlay.getGraphics();
			if(i%5==0) 
				g2d.setColor(new Color(255,0,0));
			else if (i%5==1) g2d.setColor(new Color(209, 76, 15));
			else if (i%5==2) g2d.setColor(new Color(171, 140, 28));
			else if (i%5==3) g2d.setColor(new Color(133, 204, 41));
			else if (i%5==4) g2d.setColor(new Color(102, 255, 51));
			g2d.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
			g2d.drawLine((int)PathMaker.path.get(i).x,(int)PathMaker.path.get(i).y,(int)PathMaker.path.get(i+1).x,(int)PathMaker.path.get(i+1).y);
			if(highlight) {
				g2d.setColor(Color.green);
				g2d.setStroke(new BasicStroke(7,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				g2d.drawLine((int)PathMaker.path.get(i).x,(int)PathMaker.path.get(i).y,(int)PathMaker.path.get(i+1).x,(int)PathMaker.path.get(i+1).y);
			}
		}
		
	}
	
}
