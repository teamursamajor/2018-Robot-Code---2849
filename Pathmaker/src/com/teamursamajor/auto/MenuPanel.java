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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException; 

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.Arrays;

import java.io.*; 
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFormattedTextField;
import java.text.Format;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;


//TODO - fix some logic when not being rushed
public class MenuPanel extends JPanel {

    private JButton resetPath, savePath, newPath;

    //Drop Down Menu - select a path file to open
    private JComboBox selectPathFileDropDownMenu;

    //text box:
    /* Updates when selecting a file from the drop down menu
     * when saving pop up message - either "Are You Sure You Want To Overwrite..." OR "Save Path As ...."
     */
    private ArrayList<String> pathNames;
    private File [] pathFiles;
    private Map <String, File> pathMap = new LinkedHashMap<String, File>();
    private FieldPanel fieldPanel;
    private File selectedFile;
    private Map <String, Curve> curveMap = new HashMap <String, Curve>();//DONT NEED
    private String pathLocations = "C:/Users/teamursamajor/git/2018-Robot-Code---2849/Pathmaker/PathFiles";
    private int TICKS_PER_POINT = 3;
    //Boolean drawingNewPath = false;

     public MenuPanel (FieldPanel f){
        this(335, f);
     }

     public MenuPanel(int width, FieldPanel fieldPanel){
        this.setSize(width, 500);

        this.addButtons();
        this.fieldPanel = fieldPanel;

        readAvailablePaths(pathLocations);
    
        setPathMap();

        //System.out.println(pathMap.keySet());
        
        setTempDropDown();

        JPanel sliderPanel = setSliderPanel(width);
        
        JPanel startPointPanel = setStartPointPanel(width);

        this.add(selectPathFileDropDownMenu);
        this.add(sliderPanel);
        this.add(startPointPanel);
        displayPathFile((String) selectPathFileDropDownMenu.getSelectedItem());

        try {
            BufferedImage diagramA = ImageIO.read(new File(System.getProperty("user.dir") + "Rocket Diagram"));
            BufferedImage diagramB = ImageIO.read(new File(System.getProperty("user.dir") + "CargoBayDiagram"));        // JLabel syntax = new JLabel
            JPanel diagramPanel = new JPanel(){
                public void paintGraphics(Graphics g){
                    g.drawImage(diagramA, 20,20,20,20,null);
                    g.drawImage(diagramB, 20,20,20,20,null);
                }
            };
            diagramPanel.setSize(width, 100);
            this.add(diagramPanel);
            diagramPanel.repaint();
        }
        catch (Exception e){
            System.out.println("Could not draw Diagrams");
        }
    }

    public void setField (FieldPanel fieldPanel){
        this.fieldPanel = fieldPanel;
    }

    private void setTempDropDown (){
        String [] names = new String [pathNames.size()];
        for (int i = 0; i < pathNames.size(); i++){
            names[i] = pathNames.get(i);
        }


        setPathMap();

        JComboBox <String> tempDropDown = new JComboBox<>(names);

        tempDropDown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    String str = (String) selectPathFileDropDownMenu.getSelectedItem();
                    displayPathFile(str);
                } catch (Exception err){

                }
                
            }
        });
        
        this.selectPathFileDropDownMenu = tempDropDown;
        selectPathFileDropDownMenu.setEditable(true);
    }

    public void readAvailablePaths(String pathFolderDirection){
        /**
         * for each txt file in folder
         * add option to menu bar
         */
        

        File pathFolder = new File(pathFolderDirection);

        String [] temp = pathFolder.list();
        
        pathNames = new ArrayList<String>();
        
        for (String str : temp){
            if (!(str.contains("PathWriter"))){
                pathNames.add(str.replace(".txt",""));
            }
        }
        
        pathFiles = pathFolder.listFiles();
        this.setPathMap();
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
    }

    public void displayPathFile(String fileName){
        try {
            Scanner sc = new Scanner (pathMap.get(fileName));
          //  System.out.println(fileName + " FILE NAME AFTER READ");
            ArrayList<Integer[]> points = new ArrayList<Integer[]>();
            while (sc.hasNextLine()){
                String str = sc.nextLine();
               // System.out.println(str);
                if (str.contains("{")){
                    Integer[] pointsToAdd = new Integer[2];
                    str = str.substring(str.indexOf("{"));
                    String pointA = str.substring(1, str.indexOf(","));
                    String pointB = str.substring(str.indexOf(",") +2, str.length()-1);
                    pointsToAdd[0] = Integer.parseInt(pointA);
                    pointsToAdd[1] = Integer.parseInt(pointB);
                    points.add(pointsToAdd);
                }
            }
            fieldPanel.drawFromFile(points);
        }
        
        catch (IOException e){
            System.out.println("CANT READ PATH");
        }
    }

    public JPanel setSliderPanel(int width){
        JSlider slider = new JSlider (JSlider.HORIZONTAL, 3, 25, TICKS_PER_POINT);
        slider.setMajorTickSpacing(3);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
       // slider.setValue(3);

        JLabel value = new JLabel (" Current Value: " + slider.getValue() + " ");
        value.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                value.setText(" Current Value: " + slider.getValue() + " ");
                fieldPanel.setUpdateVal(slider.getValue());
            }
        });
        
        JPanel sliderPanel = new JPanel();
        sliderPanel.setSize(width, 50);
        sliderPanel.add(value);
        sliderPanel.add(slider);       
        sliderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        return sliderPanel;
    }

    public JPanel setStartPointPanel(int width){
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter intOnly = new NumberFormatter(format);
        intOnly.setValueClass(Integer.class);
       //TODO - set min/max, fix backspace key
        intOnly.setAllowsInvalid(false);

        JLabel startPointName = new JLabel();
        JLabel unitA = new JLabel (" inches");
        JLabel unitB = new JLabel (" inches");
        JLabel xName = new JLabel("X-coord:");
        JFormattedTextField xPixText = new JFormattedTextField(intOnly);
        xPixText.setPreferredSize(new Dimension(75,20));
        JLabel yName = new JLabel("Y-coord:");
        JFormattedTextField yPixText = new JFormattedTextField(intOnly);
        yPixText.setPreferredSize(new Dimension(75,20));


        JButton setPoint = new JButton("Set");
        setPoint.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //fieldPanel.setStartPoint, redraw
                
            }
        });

        JPanel startPointTextPanel = new JPanel();
        startPointTextPanel.setLayout(new GridLayout(2,3));
        startPointTextPanel.setSize((width/3)*2,100);
        startPointTextPanel.add (xName);
        startPointTextPanel.add (xPixText);
        startPointTextPanel.add (unitA);
        startPointTextPanel.add (yName);
        startPointTextPanel.add (yPixText);//WAS Y
        startPointTextPanel.add (unitB);
        
        JPanel startPointSetPanel = new JPanel();
        startPointSetPanel.setSize(width/3,100);
        setPoint.setPreferredSize(new Dimension(55,50));
        startPointSetPanel.add(setPoint);

        JPanel startPointPanel = new JPanel();
        startPointPanel.setSize(width,100);
        startPointPanel.add(startPointTextPanel);
        startPointPanel.add(startPointSetPanel);
        startPointPanel.setBorder(BorderFactory.createLoweredBevelBorder());

        return startPointPanel;
    }

    private void addButtons (){
        //Clears the screen drawing + the temp text file
        JButton clearPathButton = setButtonLayout(new JButton("Clear Path")); 
        //flips both the drawn path and text file
        JButton flipByYAxisButton = setButtonLayout(new JButton("Flip By Y-Axis"));

        JButton savePathButton = setButtonLayout(new JButton("Save Path"));
        savePathButton.setToolTipText("Saves the GREEN path to the path file in the drop down menu");

        JButton showPathButton = setButtonLayout(new JButton("Show Selected Path"));
        showPathButton.setToolTipText("Show Selected Path");
        //TODO- have delete path file button + read as text file button
        
        
        clearPathButton.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**
                 * Clears crnt path + path writer . txt
                 */
                try {
                    //fieldPanel.clearPath(fieldPanel.getStartPoint());
                    fieldPanel.clearField();

                    File f = pathMap.get((String) selectPathFileDropDownMenu.getSelectedItem());
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    String str = "";
                    while ( !(str = br.readLine()).contains("}")){}
                    str = str.substring(str.indexOf("{"));
                    str = str.replaceAll(" ", "");
                    int [] startPoint = new int[2];
                    startPoint [0] = Integer.parseInt(str.substring(1, str.indexOf(",")));
                    startPoint [1] = Integer.parseInt(str.substring(str.indexOf(",")+1, str.length()-1));
                    //System.out.println(startPoint [0]);


                    fieldPanel.setStartPoint(startPoint);

                } catch (Exception error){
                        System.out.println("COULD NOT CLEAR PATH");
                }
            }
        });

        flipByYAxisButton.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**
                 * flips crnt path by the Y axis
                 */
                int[] a = {50, 12};
                int[] b = {13, 95};
                fieldPanel.testMe(a,b);
            }
        });

        //TODO - DO TEST TO SEE IF IT IS A VALID PATH FIRST
        savePathButton.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**
                 * Copies the text from path writer 2 file in combo box
                 */
                // String str = (String) selectPathFileDropDownMenu.getSelectedItem();
                // str = pathLocations + str + ".txt";
                //selectedFile = new File(str);
                //TODO -  try and if it doesnt work make new file then rewrite
                Curve c = fieldPanel.drawBezierCurve();
                try {
                    writeToFile((String) selectPathFileDropDownMenu.getSelectedItem(), c);
                }
                catch (Exception err){
                    
                    try {
                        String newFileName = (String) selectPathFileDropDownMenu.getSelectedItem();
                        File newFile = new File (pathLocations + "/" +newFileName + ".txt");
                   // if (newFile.creatNewFile()) System.out.println("Succes");
                        boolean successful = newFile.createNewFile(); 
                   
                        pathMap.put(newFileName, newFile);

                        writeToFile(newFileName, c);
                        setTempDropDown();
                        displayPathFile((String) selectPathFileDropDownMenu.getSelectedItem());
                       // fieldPanel.repaint();
                    }
                    catch (IOException error){
                        System.out.println("CANT MAKE NEW FILE");
                    }
                }
                
                /**
                 * Draw bezier curve
                 * ask if this is correct
                 * path parser = new path parser
                 * parse path
                 */
            }
        });


        showPathButton.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e){
                /**
                 * shows the path in the crnt path file
                 */
                Curve newCurveForFile = fieldPanel.drawBezierCurve();
                if (!fieldPanel.isNewPath()){
                    displayPathFile((String)selectPathFileDropDownMenu.getSelectedItem());
                }
                
                //curveMap.add(selectedFile, newCurveForFile);
            }
        });

        //TODO - add delete path file button
        JButton [] buttons = {clearPathButton, flipByYAxisButton, savePathButton, showPathButton};
        
        //TODO -  do this last (+ buttons arr)
        for (JButton b : buttons){
            this.add(b);
        }

    }

    private JButton setButtonLayout(JButton button){
        //String str = button.getLabel()
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
		
		button.setBorder(blackline);
		
		button.setPreferredSize(new Dimension(75,25));
		
		button.setFont(new Font("Helvetica", Font.BOLD, 9));
        
        return button;
    }
    
 

    private void writeToFile (String fileName, Curve c) throws IOException{
        clearFile(fileName);
        File f = pathMap.get(fileName);
        FileWriter fileWriter = new FileWriter(f, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        ArrayList <RobotStats> info = new ArrayList <RobotStats>();
        info = c.getPath();
        for (RobotStats stat : info){
            writer.write(stat.toString() + "\n");
        }


        // writer.write(coords[0] + ", Y: " + coords[1] + "\n");
        writer.flush();
        writer.close();
    }
    public void clearFile(String fileName) throws IOException{
        SimpleDateFormat format = new SimpleDateFormat ("===yy/MM/dd - hh:mm:ss===\n");
        String str = format.format(new Date());
        FileWriter writer = new FileWriter(pathMap.get(fileName));
        writer.write(str);
        writer.flush();
        writer.close();
    }


}
