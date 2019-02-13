package com.teamursamajor.auto;
/**
 * Add your docs here.
 */
import java.util.ArrayList;


public class RobotStats implements FieldMeasurements{

    //May not need time?
    private double predictedVelocity, predictedAngle, time;
    private double[] predictedLocation;//feet
    private int[] pixelLocation;
    private double distanceToTravel;

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
        this.angle = angle;
    }

    public int[] getPixelLocation(){
        return pixelLocation;
    }

    public double[] getFieldLocation(){
        return predictedLocation;
    }

    


    

}
