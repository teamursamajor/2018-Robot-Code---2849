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

public class PathMaker implements PlayingField {

    //Frame where everything is described
    static JFrame screenFrame;

    //This Images are set in the main method
    static BufferedImage fieldImage, overFieldImage; 
    static ArrayList<PointOnPath> drawnPath = new ArrayList<PointOnPath>();
    
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
        init();
        try {
			fieldImage = ImageIO.read(new File(System.getProperty("user.dir") + "/../2019field.jpeg"));
			overFieldImage = ImageIO.read(new File(System.getProperty("user.dir") + "/../2019field(transparent).png"));
	
		} catch (Exception E) {
			E.printStackTrace();
        }
        
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

     //Rule for new panel - always add to screen panel first
     static void init(){
         screenFrame = new JFrame();
         
         File ursaMajorBearIcon = new File ((System.getProperty("user.dir") + "/../Icon.png"));
         screenFrame.setIconImage(new ImageIcon(ursaMajorBearIcon.toString()).getImage());
         
         screenFrame.setLayout(null);
         screenFrame.setSize(1000, 850);
         screenFrame.setDefaultCloseOperation(screenFrame.EXIT_ON_CLOSE); 
        

        // GuiPanels gui = new GuiPanels();
         //PointPanel pointPanel = new PointPanel();
         PointOnPath test1 = new PointOnPath(13,52,2);
         PointOnPath test2 = new PointOnPath(12,42,4);
         MenuPanel menu = new MenuPanel();
        // menu.setEditable(true);
         //menu.addActionListener()
         screenFrame.add(menu);




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
        //  fieldPanel.setLocation(200,0);

        }

       

        public JPanel makePanelForField (){
            //TODO - FIX
                        

            JPanel field = null;
            return field;
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