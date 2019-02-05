package com.teamursamajor.auto;

import javax.swing.JPanel;
import java.util.ArrayList;

public class PathPoints extends JPanel implements FieldMeasurements {
    
    private ArrayList<PointOnPath> points;

    public PathPoints (){
        points = new ArrayList<PointOnPath>();
    }

    public void addPoint (PointOnPath p){
        points.add(p);
    }
    
    public void clearPoints (){
        points = new ArrayList<PointOnPath>();
    }
    
    public ArrayList<PointOnPath> getPoints(){
        return this.points;
    }

    //No Color Change

    //TODO - make a path prediction class and in here call it to set the values in the robot stats class
   
}


