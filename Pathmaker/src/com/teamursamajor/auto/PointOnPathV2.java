package com.teamursamajor.auto;

import javax.swing.JPanel;

public class PointOnPathV2 extends JPanel implements FieldMeasurements {
    private int xPixel, yPixel;
    private double xFeet, yFeet;
  // private int textBoxHeight = 25;
    private int pointNumber; // Nth Point in the current path ?
    
    
    
    /* Field Measurements have:
     * -xy conversions
     * -
     * 
     * Move acceleration, direction etc. to its own 'robot stats' class
     * Message -> toString instead, if it writes
     * to a file just use the toString instead of
     * the message string.
     * 
     * For now, don't have the points change color.
     * Get rid of the buttons - redundant and don't work
     */

    public PointOnPathV2 (double xFeet, double yFeet, int pointNumber){
        /*
         *TODO - figure out which constructor to use 
         */

         this.xFeet = xFeet;
         this.yFeet = yFeet;
        
         this.pointNumber = pointNumber;
         
        
    }

    public void addPointToPanel (JPanel pointPanel){
         JPanel result = pointPanel;
    }


    public String toString(){
        /*
         * "Point " + Point# + ":"
         * "- xCoordinates:"
         * "-- " + xFeet + " ft, " + xPix + " pixels"
         * same for yCoordinates^
         * "- Color: " + color
         * 
         * =========
         * 
         * Example:
         * Point 3:
         * - xCoordinates:
         * -- 12 ft, 25 pixels
         * - yCoordinates:
         * -- 23 ft, 102 pixels
         * - Color: Red
         */

         //TODO - have another toString but for the file; this toString is for debugging and for readibility

         String str = "Point " + pointNumber +": \n"
            +"- xCoordinates: \n"
            +"-- " + xFeet + " ft, " + xPixel + "pixels \n"
            +"- yCoordinates: \n"
            +"-- " + yFeet + " ft, " + yPixel + "pixels \n"
            +"- Color: N/A";


        return "";
    }


    //No Color Change

    //TODO - make a path prediction class and in here call it to set the values in the robot stats class

}