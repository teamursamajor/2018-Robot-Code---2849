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

    static JFrame screenFrame;

    //This Images are set in the main method
    static BufferedImage field, overField; 
    static ArrayList<PointOnPathV2> drawnPath = new ArrayList<PointOnPathV2>();
    


   
    public static void main (String[] args){

        //Sets field images
        try {
			field = ImageIO.read(new File(System.getProperty("user.dir") + "/../2019field.jpeg"));
			overField = ImageIO.read(new File(System.getProperty("user.dir") + "/../2019field(transparent).png"));
	
		} catch (Exception E) {
			E.printStackTrace();
        }
        
        //Sets reference points for buttons
        setReferencePoints();
        System.out.println(PlayingField.hatchIntake.toString());
    }



    //Output - N/A ?
    //Input - N/A  ?
    /* Init - Definitely need - the following are the broken down portions of it.
     *
     * 
     * 
     *  
     */










    //======= METHODS NOT RELATED TO JPANELS OR INIT ======

    /* setReferencePoints Methd =========================
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