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

    public GuiPanels(){
        this.buttonPanel = new JPanel();
        this.buttonPanel.setSize(200,850);

        //DESCRIPTION OF FIELD PANEL
        this.setFieldPanel();
        this.fieldPanel.setSize(400,850);//Location = 400,850



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

    private void setPointPanel(){
        //ACTIONS - Add point
    }


    //TODO -  menu panel - but change the text to buttons
}