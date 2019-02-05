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
import java.awt.BasicStroke;

//import sun.jvmstat.perfdata.monitor.v1_0.BasicType;

import java.awt.Graphics2D;

public class FieldPanel extends JPanel {
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
    private int height, width;
    private Image fieldImage, overField;//change to buffered image?
    private BufferedImage drawnPath;
    private Graphics g;
    private Graphics2D pathGraphics;

    // public FieldPanel(){
    //    Graphics g = this.getGraphics();
    // }

    public FieldPanel (Image fieldImage, Image overField, int width, int height){
        g = this.getGraphics();
        this.fieldImage = fieldImage;
        this.overField = overField;
        drawnPath = new BufferedImage(400,800, BufferedImage.TYPE_4BYTE_ABGR);
        this.width = width;//400
        this.height = height;//800
        this.setSize(width, height);
        pathGraphics = (Graphics2D) drawnPath.getGraphics();
        pathGraphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
       
        updateField();
    }

    public void updateField() {
        try {
          g.drawImage(fieldImage, 0, 0, width, height, null);
         // g.drawImage(drawnPath, 0, 0, width, height, null);//CHECK NULL
          gig.drawImage(overField, 0, 0, width, height, null);
        }
        catch (java.lang.NullPointerException e){
            System.out.println("ERROR");
        }
        
    }

    public void drawPoints (PointOnPath point1, PointOnPath point2){
        int [] point1Coordinates = {point1.getXPixelCoord(), point1.getYPixelCoord()};
        int [] point2Coordinates = {point2.getXPixelCoord(), point2.getYPixelCoord()};

        pathGraphics.setColor(Color.BLACK);
        pathGraphics.drawLine(point1Coordinates[0], point1Coordinates[1],
                              point2Coordinates[0], point2Coordinates[1]);
        this.updateField();
    }
}