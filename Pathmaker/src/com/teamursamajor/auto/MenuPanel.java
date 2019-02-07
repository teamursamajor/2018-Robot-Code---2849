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
//TODO - fix some logic when not being rushed
public class MenuPanel extends JPanel {

    private JButton resetPath, savePath, newPath;

    //Drop Down Menu - select a path file to open
    private JComboBox selectPathFile;

    //text box:
    /* Updates when selecting a file from the drop down menu
     * when saving pop up message - either "Are You Sure You Want To Overwrite..." OR "Save Path As ...."
     */
    private ArrayList<String> pathNames;
    private File [] pathFiles;
    private Map <String, File> pathMap = new LinkedHashMap<String, File>();

     public MenuPanel (){
        this(335);
     }

     public MenuPanel(int width){
        this.setSize(width, 500);

        // String [] pathNames = {"Path 1", "Path 2"};


        // JComboBox<String> PathOptions = new JComboBox <>(pathNames);
        // PathOptions.setEditable(true);
        
        // readAvailablePaths("FILE");
        
        
        
        // this.add(PathOptions);
        this.addButtons();
        
        readAvailablePaths("C:/Users/teamursamajor/git/2018-Robot-Code---2849/Pathmaker/PathFiles");
    
        setPathMap();

        //System.out.println(pathMap.keySet());
        
        setDropDown();

        this.add(selectPathFile);
    
    
    }

    private void setDropDown (){
        String [] names = new String [pathNames.size()];
        for (int i = 0; i < pathNames.size(); i++){
            names[i] = pathNames.get(i);
        }

        JComboBox <String> dropDown = new JComboBox<>(names);
        this.selectPathFile = dropDown;
        //selectPathFile.setSize(320,50);
        setPathMap();   



    }

    private void addButtons (){
        //Clears the screen drawing + the temp text file
        JButton clearPathButton = setButtonLayout(new JButton("Clear Path")); 
        //flips both the drawn path and text file
        JButton flipByYAxisButton = setButtonLayout(new JButton("Flip By Y-Axis"));

        JButton savePathButton = setButtonLayout(new JButton("Save Path"));

        JButton showPathButton = setButtonLayout(new JButton("Show Selected Path"));
        //TODO- have delete path file button + read as text file button
        JButton [] buttons = {clearPathButton, flipByYAxisButton, savePathButton, showPathButton};
        
        //TODO -  do this last (+ buttons arr)
        for (JButton b : buttons){
            this.add(b);
        }
        
        buttons[0].addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**
                 * Clears crnt path + path writer . txt
                 */
            }
        });

        buttons[1].addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**
                 * flips crnt path by the Y axis
                 */
            }
        });

        buttons[2].addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**
                 * Copies the text from path writer 2 file in combo box
                 */
                
            }
        });

        buttons[3].addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**
                 * shows the path in the crnt path file
                 */
            }
        });

        //TODO - add delete path file button

    }

    private JButton setButtonLayout(JButton button){
        //String str = button.getLabel()
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
		
		button.setBorder(blackline);
		
		button.setPreferredSize(new Dimension(75,25));
		
		button.setFont(new Font("Helvetica", Font.BOLD, 9));
        
        return button;
    }
    //Todo change to set
    public void readAvailablePaths(String pathFolderDirection){
        /**
         * for each txt file in folder
         * add option to menu bar
         * 
         * this will be called everytime you create a path
         * 
         */
        

        File pathFolder = new File(pathFolderDirection);

        String [] temp = pathFolder.list();
        
        pathNames = new ArrayList<String>();
        
        for (String str : temp){
            if (!(str.contains("PathWriter"))){
                pathNames.add(str.replace(".txt",""));
            }
        }
        
        System.out.println(pathNames.toString());

        pathFiles = pathFolder.listFiles();

        this.setPathMap();
        //System.out.println(pathNames.toString());
    }

    private void setPathMap (){
        pathMap.clear();
        for (String nameOfPath : pathNames){
            for (File f : pathFiles){
                if (f.toString().contains(nameOfPath)){
                    pathMap.put(nameOfPath, f);
                    break;
                }
            }
        }

        System.out.println(pathMap.toString());
    }

   // public void readFile (String patg)

    public void displayPathFile(){
        /**
         * Move to path setr
         */
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
    //TODO - add flip by x/y buttons
    //TODO - add bezier curve button
}
