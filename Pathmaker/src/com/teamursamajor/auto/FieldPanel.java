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
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private BufferedImage fieldImage, overFieldImage, pathImage, bezierCurveImage;
    private Graphics2D pathGraphics, curveGraphics;
    private File pathFile;
    private int[] pointA = new int[2];
    private int[] pointB = new int[2];
    private int[] startPoint = new int[2];
    private int slowDownWriter = 0;
    private int slowFactor = 3;//3 - 25
    private ArrayList <Integer[]> drawnPath;

    public FieldPanel(BufferedImage fieldImage, BufferedImage overFieldImage, File pathFile){
        this.fieldImage = fieldImage;
        this.overFieldImage = overFieldImage;
        this.pathFile = pathFile;
        
        pathImage = new BufferedImage(400,800, BufferedImage.TYPE_4BYTE_ABGR);
        pathGraphics = (Graphics2D) pathImage.getGraphics();
        pathGraphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        pathGraphics.setColor(Color.GREEN);

        bezierCurveImage = new BufferedImage(400, 800, BufferedImage.TYPE_4BYTE_ABGR);
        curveGraphics = (Graphics2D) bezierCurveImage.getGraphics();
        curveGraphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        curveGraphics.setColor(Color.RED);

        
        addFieldAction();

        int [] delMe = {0,0};
        pointA = delMe;//TODO - change to start point
        startPoint = pointA;
        drawnPath = new ArrayList <Integer[]>();
        Integer a = pointA[0];
        Integer b = pointA[1];
        Integer [] arr = {a,b};
        drawnPath.add(arr);
        //drawnPath.add(pointA);

        // try {
        //     clearPath(startPoint);
        // } catch (Exception e){
                
        // }
    }

    public void paint (Graphics g){
        super.paint(g);
        g.drawImage(fieldImage, 0, 0, 400, 800, null);
        g.drawImage(pathImage, 0, 0, 400, 800, null);
        g.drawImage(bezierCurveImage, 0, 0, 400, 800, null);
        g.drawImage(overFieldImage, 0, 0, 400, 800, null);
    }

    private void addFieldAction(){
        this.addMouseMotionListener(new MouseMotionListener(){
            public void mouseDragged(MouseEvent event){
            
                int [] coords = {event.getX(), event.getY()};
                pointB = coords;
                
                if (slowDownWriter % slowFactor == 0){
            //         try {
            //        //writes info of robot at point b
            //         writeToPathWriterFile(pointB);
            //         }
            //         catch (Exception e){
            //             System.out.println("ERROR WRITING TO FILE");
            //         }
            //    // System.out.println(drawnPath.toString());                    

                    drawPoints();
                }
                slowDownWriter = (slowDownWriter+1) % slowFactor;

                //BECAUSE REFERING TO MOUSE LISTENER
                //this.repaint();
            }
        public void mouseMoved(MouseEvent e){};
        });

        this.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				int [] coords = {e.getX(), e.getY()};
                pointB = coords;
                // try {
                //     //writes info of robot at point b
                //      writeToPathWriterFile(pointB);
                //      }
                //      catch (Exception err){
                //          System.out.println("ERROR WRITING TO FILE");
                //      }
                // System.out.println(drawnPath.toString());                    
 
                     drawPoints();
			}
        });
    }
    
    private void drawPoints (){
        pathGraphics.drawLine(pointA[0], pointA[1], pointB[0], pointB[1]);
        this.repaint();
        updatePoints();
    }

    public void clearField (){
        pathGraphics.setBackground(new Color(0,0,0,0));
        pathGraphics.clearRect(0,0, 400, 800);
        curveGraphics.setBackground(new Color(0,0,0,0));
        curveGraphics.clearRect(0,0, 400, 800);
        this.repaint();
    }


    private void updatePoints(){
        pointA = pointB;

        Integer a = pointA[0];
        Integer b = pointA[1];
        Integer [] arr = {a,b};
        drawnPath.add(arr);
    }

    public void setPointA (int [] xyCoords){
        pointA = xyCoords;
    }

   
    public Curve drawBezierCurve(){
        Curve c = new Curve();
        int[][] p = new int [drawnPath.size()][2];
        for (int i = 0; i < drawnPath.size(); i++){
            Integer [] arr = drawnPath.get(i);
            p[i][0] = (int)arr [0];
            p[i][1] = (int)arr [1];
        }

        c.recCurve(p, curveGraphics);
        this.repaint();
        
        return c;
    }

    public void testMe (int[]a, int[]b){
        pathGraphics.drawLine(a[0], a[1], b[0], b[1]);
        this.repaint();
    }

    // public void setStartPoint (int [] startPoint){
    //     this.startPoint = startPoint;
    // }

    public int [] getStartPoint (){
        return startPoint;
    }

    public ArrayList<Integer[]> getDrawnPath(){
        return drawnPath;
    }
    public void setStartPoint(int [] coord){
        pointA = coord;
        drawnPath = new ArrayList<Integer[]>();
        Integer [] i = {
            pointA[0], pointA[1]
        };
        drawnPath.add(i);
    }

    public void setUpdateVal(int i){
        slowFactor = i;
    }

    public void drawFromFile (ArrayList<Integer[]> points){
        drawnPath = new ArrayList<Integer[]>();
        clearField();
        for (Integer[] point : points){
            drawnPath.add(point);
        }

        for (int i = 0; i < drawnPath.size() - 1; i++){
            Integer[] point1 = drawnPath.get(i);
            Integer[] point2 = drawnPath.get(i+1);
            curveGraphics.drawLine(point1[0], point1[1], point2[0], point2[1]);
        }
      //  drawBezierCurve();
        this.repaint();
        Integer[] tempPointA = drawnPath.get(0);
        int[] temp = {tempPointA[0], tempPointA[1]};
        pointA = temp;
        drawnPath = new ArrayList<Integer[]>();
        drawnPath.add(tempPointA);
    }

}
