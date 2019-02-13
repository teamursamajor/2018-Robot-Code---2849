package com.teamursamajor.auto;

public class FieldObject implements FieldMeasurements{
    //private double test = fieldWidth;
    private double xFeet, yFeet;
    
    public FieldObject(double xFeet, double yFeet){
        this.xFeet = xFeet;
        this.yFeet = yFeet;
        
        //For debugging
        if (this.xFeet > FieldMeasurements.WIDTH){
            System.out.println("X COORDINATE OF GAME PIECE IS TOO BIG (" + this.xFeet + ")ft");
        }
        if (this.yFeet > FieldMeasurements.HEIGHT){
            System.out.println("Y COORDINATE OF GAME PIECE IS TOO BIG (" + this.yFeet +")ft");
        }
    }

    public void setLocation (double xFeet, double yFeet){
        this.xFeet = xFeet;
        this.yFeet = yFeet;
    }


    public FieldObject flipYAxis (){
        FieldObject result = this;
        return result;
    }

    public String toString(){
        return "xFeet: " + xFeet + ", yFeet: " + yFeet;
    }
}