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
import javax.swing.JSeparator;

public class PointPanel extends JPanel {
    /*
     * Point Panel:
     */
    private int pointEntryHeight = 45;//Height of the box that point info is displayed in
    private int numberOfPoints = 0;


    public PointPanel(){
        this(335);
    }

    public PointPanel(int pixelWidth){
        
       this.setSize(pixelWidth, 1);
       this.setFont(new Font("Helvetica", Font.BOLD, 12));
       
    }

    


    //return new this
    //TEST
    public void addPointToPanel (PointOnPath point){
        //TODO - add time of point + set the font and size

        Graphics g = this.getGraphics();

        String pointInfo = "X:"+point.getX()+", Y:"+point.getY();
        String breakLine = "-------------------------------------";//TODO - paint line on panel instead
        JLabel pointEntry = new JLabel(pointInfo+"\n"+breakLine);
 
         numberOfPoints++;
         //int heightOfthis = numberOfPoints*pointEntryHeight;
         this.setSize(this.getWidth(), this.getHeight()+pointEntryHeight);
         
         //this.setSize(0,0);
         
         this.add(pointEntry);
         //this.addSeparator();
         this.add(new JSeparator());

         //this.paint(g);
         System.out.println(pointEntry);
         //return this;
    }
}