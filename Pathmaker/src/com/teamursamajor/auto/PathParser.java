package com.teamursamajor.auto;
/**
 * Add your docs here.
 */
import java.util.ArrayList;


public class PathParser implements FieldMeasurements{
    private ArrayList <RobotStats> path = new ArrayList <RobotStats>();

    public void PathParser (ArrayList<RobotStats> path){
        this.path = path;
        setPathValues();
    }

    public void setPathValues (){
        RobotStats pointA;
        RobotStats pointB;

        for (int i = 0; i < path.size()-1; i++){
            pointA = path.get(i);
            pointB = path.get(i+1);

            pointA.setAngle(calculateAngle(pointA, pointB));
        }

    }

    public void calculateAngle(RobotStats pointA, RobotStats pointB){
        double[] aLoc = pointA.getFieldLocation();
        double[] bLoc = pointB.getFieldLocation();

        


        double theta = Math.atan2(aLoc[1]-bLoc[1], aLoc[0]-bLoc[0]);
        theta += Math.PI/2.0;
        double angle = Math.toDegrees(theta);

        if (angle < 0) {
            angle += 360;
        }
        
        System.out.println(angle);
    }


    

}
