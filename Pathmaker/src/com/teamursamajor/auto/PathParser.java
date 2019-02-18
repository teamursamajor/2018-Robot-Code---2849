package com.teamursamajor.auto;
/**
 * Add your docs here.
 */
import java.util.ArrayList;


public class PathParser implements FieldMeasurements{
    private ArrayList <RobotStats> path = new ArrayList <RobotStats>();
    private ArrayList <PointOnPath> drawnPath;

    // public PathParser (ArrayList<RobotStats> path){
    //     this.path = path;
    //     setPathValues();
    // }

    // public void writeToFile(Curve c, File f){
    //     path = c.getPath();
        
    //     setPathValues();

    //     for (RobotStats r : path){
    //         /**
    //          * write to file r.toString, path reader info
    //          */
    //     }

    // }



    public void setPathValues (){
        RobotStats pointA;
        RobotStats pointB;

        for (int i = 0; i < path.size()-1; i++){
            pointA = path.get(i);
            pointB = path.get(i+1);

            pointA.setAngle(calculateAngle(pointA, pointB));
            pointA.setDistanceToTravel(calculateDistance(pointA, pointB));
        }

    }

    public ArrayList<RobotStats> getPath (){
        return path;
    }

    public void setPath (){
        System.out.println("E");
    }

    public double calculateAngle(RobotStats pointA, RobotStats pointB){
        double[] aLoc = pointA.getFieldLocation();
        double[] bLoc = pointB.getFieldLocation();

        double theta = Math.atan2(aLoc[1]-bLoc[1], aLoc[0]-bLoc[0]);
        theta += Math.PI/2.0;
        double angle = Math.toDegrees(theta);

        if (angle < 0) {
            angle += 360;
        }
        
        //System.out.println(angle);
        return angle;
    }

    public double calculateDistance(RobotStats pointA, RobotStats pointB){
        double[] pointALoc = pointA.getFieldLocation();
        double[] pointBLoc = pointB.getFieldLocation();
        double dist = Math.abs( (pointALoc[0] - pointBLoc[0]) - (pointALoc[1] - pointBLoc[1]));
        return dist;
    
    
    }



}
