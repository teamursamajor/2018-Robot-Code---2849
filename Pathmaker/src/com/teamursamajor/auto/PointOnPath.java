package com.teamursamajor.auto;

import javax.swing.JPanel;

public class PointOnPath extends JPanel implements FieldMeasurements {
    private int xPixel, yPixel;
    private double xFeet, yFeet;
    private double time;
    private double direction;
    private int pointNumber; // Nth Point in the current path ?
    //private ArrayList <PointOnPat
    
    
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

    public PointOnPath (int xPixel, int yPixel, int pointNumber){
        /*
         *TODO - figure out which constructor to use 
         */

         this.xPixel = xPixel;
         this.yPixel = yPixel;
        
         this.pointNumber = pointNumber;
         
        
    }

    public void addPoint (PointOnPath p){

    }
    
    public void clearPoints (){
        
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

         //String str = "Point " + pointNumber +": \n"
            // +"- xCoordinates: \n"
            // +"-- " + xFeet + " ft, " + xPixel + "pixels \n"
            // +"- yCoordinates: \n"
            // +"-- " + yFeet + " ft, " + yPixel + "pixels \n"
            // +"- Color: N/A";

        String str = "X:" + xPixel +", Y:" +yPixel;
        return str;
    }
    
    public int getXPixelCoord (){
        return this.xPixel;
    }
    public int getYPixelCoord (){
        return this.yPixel;
    }

    //No Color Change

    //TODO - make a path prediction class and in here call it to set the values in the robot stats class
   
}


