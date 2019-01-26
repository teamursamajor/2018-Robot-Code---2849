package com.teamursamajor.auto;

public class FieldObject implements FieldMeasurements{
    //private double test = fieldWidth;
    private double xFeet, yFeet;
    
    public FieldObject(int xFeet, int yFeet){
        this.xFeet = xFeet;
        this.yFeet = yFeet;
        
        //For debugging
        if (this.xFeet > FieldMeasurements.width){
            System.out.println("X COORDINATE OF GAME PIECE IS TOO BIG (" + this.xFeet + ")ft");
        }
        if (this.yFeet > FieldMeasurements.height){
            System.out.println("Y COORDINATE OF GAME PIECE IS TOO BIG (" + this.yFeet +")ft");
        }
    }

    public FieldObject flipYAxis (){
        FieldObject result = this;
        return result;
    }
}