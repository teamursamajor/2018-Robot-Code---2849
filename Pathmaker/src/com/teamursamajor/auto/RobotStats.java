package com.teamursamajor.auto;
/**
 * Add your docs here.
 */
import java.util.ArrayList;


public class RobotStats implements FieldMeasurements{

    //May not need time?
    private double predictedVelocity, predictedAngle;
    private double[] predictedLocation;//feet
    private int[] pixelLocation;
    private double distanceToTravel;
    //FOR LAST YEAR
    private double velocity, direction, time, acceleration;

    public RobotStats (int pixX, int pixY){
        int [] coords = {pixX, pixY};
        pixelLocation = coords;
    
        double xLocation = pixX * X_CONVERSION;
        double yLocation = pixY * Y_CONVERSION;
        double [] robotLocation = {xLocation, yLocation};
        predictedLocation = robotLocation;


    }

    public double getDistanceToTravel(){
        return distanceToTravel;
    }

    public void setDistanceToTravel(double dist){
        distanceToTravel = dist;
    }

    public void setAngle (double angle){
       predictedAngle = angle;
       
    }

    public int[] getPixelLocation(){
        return pixelLocation;
    }

    public double[] getFieldLocation(){
        return predictedLocation;
    }

    

    public String toString (){
        return (distanceToTravel +", "+ predictedAngle + "{" + pixelLocation[0] + ", " + pixelLocation[1] + "}");
    }
    




    public void setStats(RobotStats next){
        
    }







    public double getDirection(){
        return this.direction;
    }

    public double getXFeet(){
        return predictedLocation[0];
    }

    public double getYFeet(){
        return predictedLocation[1];
    }

    public double getTime(){
        return time;
    }
}
