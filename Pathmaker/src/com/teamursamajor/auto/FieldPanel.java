package com.teamursamajor.auto;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;

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

import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.Graphics2D;
//import java.IOException;

import java.awt.BasicStroke;
import java.io.BufferedWriter;

import java.io.*;
//TODO - fix some logic when not being rushed
  /*
         * makePanelForField: 
         * makes a panel that displays the field image
         * as well as the over field image.
         * 
         * this panel also enables us to click on it and
         * draw paths onto it
         * 
         * Sets it size and mouse listeners
         */
        
public class FieldPanel extends JPanel {

    private BufferedImage fieldImage, overFieldImage, pathImage;
    private Graphics2D pathGraphics;
    private File pathFile;
    private int[] pointA = new int[2];
    private int[] pointB = new int[2];

    public FieldPanel(BufferedImage fieldImage, BufferedImage overFieldImage){
        this.fieldImage = fieldImage;
        this.overFieldImage = overFieldImage;
        
        pathImage = new BufferedImage(400,800, BufferedImage.TYPE_4BYTE_ABGR);
        pathGraphics = (Graphics2D) pathImage.getGraphics();
        pathGraphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        pathGraphics.setColor(Color.GREEN);
        
        addFieldAction();

        int [] delMe = {0,0};
        pointA = delMe;
        
    }

    public void paint (Graphics g){
        super.paint(g);
        g.drawImage(fieldImage, 0, 0, 400, 800, null);
        g.drawImage(pathImage, 0, 0, 400, 800, null);
        g.drawImage(overFieldImage, 0, 0, 400, 800, null);
    }

    private void addFieldAction(){
        this.addMouseMotionListener(new MouseMotionListener(){
            public void mouseDragged(MouseEvent event){
            
                int [] coords = {event.getX(), event.getY()};
                pointB = coords;
                
                try {
                   //writes info of robot at point b
                   writeToPathWriterFile(pointB);
                }
                catch (Exception e){
                    System.out.println("ERROR WRITING TO FILE");
                }
               // System.out.println(drawnPath.toString());                    

                drawPoints();
                
                //BECAUSE REFERING TO MOUSE LISTENER
                //this.repaint();
            }
        public void mouseMoved(MouseEvent e){};
        });
    }
    
    private void drawPoints (){
        pathGraphics.drawLine(pointA[0], pointA[1], pointB[0], pointB[1]);
        this.repaint();
        updatePoints();
    }    

    private void writeToPathWriterFile (int[] coords)throws IOException{
        FileWriter fileWriter = new FileWriter(pathFile, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(coords[0] + ", Y: " + coords[1] + "\n");
        writer.flush();
        writer.close();
    }


    private void updatePoints(){
        pointA = pointB;
    }

    public void setPointA (int [] xyCoords){
        pointA = xyCoords;
    }

    public void setPathFile (File f){
        pathFile = f;
    }

}
