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

public class MenuPanel extends JPanel {

    private JButton resetPath, savePath, newPath;

    //Drop Down Menu - select a path file to open
    private JComboBox selectPathFile;

    //text box:
    /* Updates when selecting a file from the drop down menu
     * when saving pop up message - either "Are You Sure You Want To Overwrite..." OR "Save Path As ...."
     */

     public MenuPanel (){
        this(335);
     }

     public MenuPanel(int width){
        this.setSize(width, 500);

        String [] pathNames = {"Path 1", "Path 2"};


        JComboBox<String> test = new JComboBox <>(pathNames);
        test.setEditable(true);
        
        readAvailablePaths("FILE");
        
        
        
        this.add(test);
    }

    public void readAvailablePaths(String pathFileDirection){
        /**
         * for each txt file in folder
         * add option to menu bar
         * 
         * this will be called everytime you create a path
         * 
         */
    }

    public void displayPathFile(){

    }

    public void updateTempTextFile(){
        /**
         * Writes to the temporary text file, call before using the write path file
         */
    }

    private void writePathFile (){
        /**
         * Copies and pastes the temporary text file
         * then clears it
         */
    }
}
