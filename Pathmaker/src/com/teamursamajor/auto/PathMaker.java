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
            pathFile = new File(System.getProperty("user.dir") + "/../PathWriter.txt");
		} catch (Exception E) {
            System.out.println("ERROR READING IMAGES");
			E.printStackTrace();
        }
       
        
        
        init();
         setReferencePoints();
        System.out.println(PlayingField.hatchIntake.toString());

        screenFrame.setVisible(true);
    }



 
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
        
         MenuPanel menu = new MenuPanel();
         screenFrame.add(menu);


        FieldPanel fieldTEST = new FieldPanel(fieldImage, overFieldImage);
        fieldTEST.setPathFile(pathFile);

        screenFrame.add(fieldTEST);
        fieldTEST.setSize(400,850);
        fieldTEST.setVisible(true);
        fieldTEST.setLocation(200,0);

        menu.setLocation(625, 50); //size - 325, 800
         //PRESET PANEL
         /*
          * Defines the button panel 
          */
         //JPanel buttonPanel = GuiPanels.buttonPanel;         screenFrame.add(buttonPanel);
         buttonPanel.setSize(200,850);
         buttonPanel.setLocation(0,50);

         //fieldPanel.setLocation(200,0);

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
