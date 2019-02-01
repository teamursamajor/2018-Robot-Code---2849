package com.teamursamajor.auto;

import javax.swing.JPanel;

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
import java.awt.Graphics2D;

public class GuiPanels extends PathMaker {
    /* Field Panel:
     * 
     * 
     * 
     * 
     */
    private JPanel fieldPanel;

    /*
     * Point Panel:
     */

    private JPanel pointPanel;

    private JPanel scrollPanel;

    private JPanel buttonPanel;

    private int pointEntryHeight = 45;//Height of the box that point info is displayed in
    private int numberOfPoints = 0;

    public GuiPanels(){
        this.buttonPanel = new JPanel();
        this.buttonPanel.setSize(200,850);

        //DESCRIPTION OF FIELD PANEL
        this.setFieldPanel();
        this.fieldPanel.setSize(400,850);//Location = 400,850

        this.setScrollPanel();
        this.setPointPanel();

    }

    private void setFieldPanel(){
        this.fieldPanel = new JPanel(){
            public void paint(Graphics g) {
                g.drawImage(fieldImage, 0, 0, 400, 800, null);
                //overlay (path?)
                g.drawImage(overFieldImage, 0, 0, 400, 800, null);
            }
        };

        //DESCRIPTION

        this.fieldPanel.addMouseMotionListener(new MouseMotionListener(){
            //ACTION - PAINT PATH (FIRST FIELD MOUSE EVENT)
            public void mouseDragged(MouseEvent e){

            }
            public void mouseMoved(MouseEvent e){}
        });

        //DESCRIPTION - 2nd FIELD MOUSE EVENT
        this.fieldPanel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				//once = true;
				screenFrame.repaint();
			}
		});
    }

    private void setScrollPanel(){

    }

    //Todo - include width param
    private void setPointPanel(){
        //ACTIONS - Add point
        //width == 335, rect = 334xheight
        this.pointPanel = new JPanel();
        this.pointPanel.setSize(335,1);
        //pointPanel.setGraphii
    }

    //return new pointpanel
    //TEST
    public JPanel addPointToPanel (PointOnPath point){
        //TODO - add time of point + set the font and size

        Graphics g = pointPanel.getGraphics();

        String pointInfo = "X:"+point.getX()+", Y:"+point.getY();
        String breakLine = "-------------------------------------";//TODO - paint line on panel instead
        JLabel pointEntry = new JLabel(pointInfo+"\n"+breakLine);
 
         numberOfPoints++;
         //int heightOfPointPanel = numberOfPoints*pointEntryHeight;
         pointPanel.setSize(pointPanel.getWidth(), pointPanel.getHeight()+pointEntryHeight);
         g.setFont(new Font("Helvetica", Font.BOLD, 12));

         pointPanel.setSize(0,0);
         //pointPanel.add(pointEntry);
        // pointPanel.paint((Graphics) pointEntry);
         System.out.println(pointEntry);
         return pointPanel;
    }

/**
 * public static JButton toSqrButton (JButton b){
		String str = "<html>" + b.getLabel();
		b.setLabel(str);
		Border blackline = BorderFactory.createLineBorder(Color.BLACK);
		
		b.setBorder(blackline);
		
		b.setPreferredSize(new Dimension(75,40));
		
		b.setFont(new Font("Helvetica", Font.BOLD, 9));
		
		return b;
	}
 */




/**
 * text field = file text box
 * 
 * JLabel label1 = new JLabel("====Starting Areas====");
	presetpanel.add(label1);
 * public void paint(Graphics g, int i_) {
		i = i_;
		this.setLocation(0, i * h + OldPathMaker.pointpaneltranslate); NEED TO FIND
		this.setSize(335, h);
		g.setColor(Color.BLACK);
		g.drawRect(0, i * h, 334, h);
		g.setFont(new Font("Times New Roman", 200, 32));
		System.out.println("XFeet: " + xFeet + "YFeet: " + yFeet + "/n" + x + "    " + y);
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
 */

    //TODO -  menu panel - but change the text to buttons

    public JPanel getPointPanel(){
        return this.pointPanel;
    }
}