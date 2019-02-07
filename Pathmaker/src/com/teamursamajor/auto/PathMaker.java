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
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import java.io.*;

/**
 * Todo:
 * In menu panel add flip by x buttons that only appear when
 * certains start points are selected
 * 
 * 
 */

public class PathMaker implements PlayingField {

    //Frame where everything is described
    static JFrame screenFrame;

    //This Images are set in the main method
    static BufferedImage fieldImage, overFieldImage, pathImage;
    static Graphics2D pathGraphics; 
    static ArrayList<PointOnPath> drawnPath = new ArrayList<PointOnPath>();
    static PathPoints pathPoints;
    static JPanel fieldPanel = new JPanel();
    static File pathFile = new File ("C:/Users/teamursamajor/git/2018-Robot-Code---2849/Pathmaker/PathFiles/PathWriter.txt");//TODO - change
    /*
     * Button Panel:
     * JPanel that contains all of the buttons
     * that we use to set a starting point for
     * our paths.
     */
     static JPanel buttonPanel = new JPanel();
        
     /*
      * Mouse Listener for field/scrool panel 
      */

     
    public static void main (String[] args){

        //Sets field images
       pathPoints = new PathPoints();
        try {
			fieldImage = ImageIO.read(new File(System.getProperty("user.dir") + "/../2019field.jpeg"));
			overFieldImage = ImageIO.read(new File(System.getProperty("user.dir") + "/../2019field(transparent).png"));
	
		} catch (Exception E) {
            System.out.println("ERROR READING IMAGES");
			E.printStackTrace();
        }
       
        
        pathImage = new BufferedImage(400,800, BufferedImage.TYPE_4BYTE_ABGR);
        pathGraphics = (Graphics2D) pathImage.getGraphics();
        pathGraphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        init();
        
        // fieldPanel = updateField();
        // //fieldPanel.setSize(400,800);
        // System.out.println(fieldPanel.toString());
        // addFieldActions();

        // screenFrame.add(fieldPanel);
        // fieldPanel.setSize(400,800);
        // fieldPanel.setVisible(true);

         //Sets reference points for buttons
         setReferencePoints();
        System.out.println(PlayingField.hatchIntake.toString());

        screenFrame.setVisible(true);
    }



    //Output - N/A ?
    //Input - N/A  ?
    /* Init - Definitely need - the following are the broken down portions of it.
     * - set Frame YES
     * mouseWheelListener - Maybe?
     * 
     *  
     */
//INIT=========================
     //Rule for new panel - always add to screen panel first
     static void init(){
         //clear path file
         
         screenFrame = new JFrame();
         
         File ursaMajorBearIcon = new File ((System.getProperty("user.dir") + "/../Icon.png"));
         screenFrame.setIconImage(new ImageIcon(ursaMajorBearIcon.toString()).getImage());
         
         screenFrame.setLayout(null);
         screenFrame.setSize(1000, 850);
         screenFrame.setDefaultCloseOperation(screenFrame.EXIT_ON_CLOSE); 
        

        
         PointOnPath test1 = new PointOnPath(13,52,2);
         PointOnPath test2 = new PointOnPath(120,92,4);
         MenuPanel menu = new MenuPanel();
         screenFrame.add(menu);
         drawnPath.add(test1);

         fieldPanel = new JPanel(){
            public void paint (Graphics g){
                g.drawImage(fieldImage, 0, 0, 400, 800, null);
                g.drawImage(pathImage, 0, 0, 400, 800, null);
                g.drawImage(overFieldImage, 0, 0, 400, 800, null);
            }
         };
          addFieldActions();
       //  screenFrame.add(field);
         drawPoints(test1, test2);
         
        // fieldPanel = addFieldActions(fieldPanel);


         screenFrame.add(fieldPanel);
         fieldPanel.setSize(400,850);
        
         //field.setVisible(true);         
         //JPanel fieldTest = field;
        // screenFrame.add(fieldTest);
         //AFTER ADDING PANELS
        // screenFrame.setVisible(true);
         //MOUSE LISTENER A
         
    
         //PRESET PANEL
         /*
          * Defines the button panel 
          */
         //JPanel buttonPanel = GuiPanels.buttonPanel;         screenFrame.add(buttonPanel);
         buttonPanel.setSize(200,850);
         buttonPanel.setLocation(0,50);

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
        //  screenFrame.add(fieldPanel);
        //  fieldPanel.setSize(400,850);
          fieldPanel.setLocation(200,0);

        }

        public static void writePath (PointOnPath p) throws IOException {
            FileWriter writer = new FileWriter(pathFile);
            writer.write(p.toString());
            writer.flush();
            writer.close();
        }
        
        public static void addFieldActions(){
            fieldPanel.addMouseMotionListener(new MouseMotionListener(){
                public void mouseDragged(MouseEvent e){
                    //System.out.println(e.getX());
                    PointOnPath p = new PointOnPath(e.getX(), e.getY(), 76);
                   try {
                       writePath(p);
                   }
                   catch (Exception e2){
                        System.out.println("ERROR WRITING TO FILE");
                   }
                   // System.out.println(drawnPath.toString());                    

                    PointOnPath prevPoint = drawnPath.get(drawnPath.size()-1);
                    drawPoints(prevPoint, p);
                    fieldPanel.repaint();
                    drawnPath.add(p);
                   
                    //drawnPath.add(p);
                }
            public void mouseMoved(MouseEvent e){};
            });
            
            
        }

        

        public static void drawPoints (PointOnPath point1, PointOnPath point2){
            int [] point1Coordinates = {point1.getXPixelCoord(), point1.getYPixelCoord()};
            int [] point2Coordinates = {point2.getXPixelCoord(), point2.getYPixelCoord()};
    
            pathGraphics.setColor(Color.GREEN);
            pathGraphics.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            pathGraphics.drawLine(point1Coordinates[0], point1Coordinates[1],
                                  point2Coordinates[0], point2Coordinates[1]);
            //this.updateField();
            fieldPanel.repaint();
            
        }    





    //======= METHODS NOT RELATED TO JPANELS OR INIT ======

    /* setReferencePoints Method =========================
     *
     * This method sets the objects in the 
     * Playing Field interface. 
     * 
     * These will provide us points to flip, move and
     * rotate as we please.
     * 
     * The location of these points are in feet.
     */
    public static void setReferencePoints(){
        PlayingField.startHabitat.setLocation(5,5);
        PlayingField.rocket.setLocation(10,10);
        PlayingField.cargoBay.setLocation(20,20);
        PlayingField.hatchIntake.setLocation(30,40);
        PlayingField.cargoIntake.setLocation(40,80);
    }

}

//cd src/com/teamursamajor/auto
