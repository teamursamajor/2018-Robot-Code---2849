package com.teamursamajor.auto.OldCode;

public class AnchorPoint {
	private double xCoord, yCoord;
	private double xFeet, yFeet;
	private double defaultInflectionPoint;// for flipping over x axis
	private int halfImageHeight = 400; //Half of the image's height
	private double xConversion, yConversion;

	//Width of the image frame/2:
	//int width = 800/2;
	//
	public AnchorPoint (double xCoord, double yCoord, double inflectionPoint){
		setXConversion(400,74);//74? 54
		setYConversion(800,27);//27? 40

		// System.out.println("X Conversion: " + xConversion);
		// System.out.println("Y Conversion: " + yConversion);

		double [] coords = feetToPixels(xCoord, yCoord); 
		
		this.xFeet = xCoord;
		this.yFeet = yCoord;

		this.xCoord = coords[0];
		this.yCoord = coords[1];
		defaultInflectionPoint = inflectionPoint;
	}
	
	public AnchorPoint (double xCoord, double yCoord){
		this(xCoord, yCoord, xCoord);
	}

	//A---|---- -> ----|---A
	public double flipByYAxis(){
		return flipByYAxis(xCoord);
	}
	
	//NOT TESTED
	public double flipByYAxis(double x){
		/*
		 * x = frame width - x
		 * return x
		 */
		
		double res = 400 - x;
		return res;
	}
	
	/*
	 * A    |
	 * |    |
	 * - -> -
	 * |    |
	 * |    A
	 */
	public double flipByXAxis(){
		return flipByXAxis(yCoord, defaultInflectionPoint);
	}
	
	//NOTTESTED
	public double flipByXAxis(double y, double inflectionPoint){
		/*
		 * int distFromInflectionPoint = inflectionPoint - y
		 * int newYCord = inflectionPoint + distFromInflectionPoint
		 * return newYCord
		 */
		
		double dist = inflectionPoint - y;
		return inflectionPoint + dist;
	}
	
	//NOTTESTED
	public void moveX (int dist){
		/*
		 * xCoordinate + dist
		 */
	}
	
	//NOTTESTED
	public void moveY (int dist){
		/*
		 * yCoordinate + dist
		 */
	}

	/*
	 *Start measuring from the end of the driver station 
	 */
	public double[] feetToPixels(double xFeet, double yFeet){
		yFeet += 10*yConversion; //10 = height of the driver station (in feet);
		
		xFeet *= xConversion;
		yFeet *= yConversion;

		double [] res = {xFeet, yFeet};
		return res;
	}	


/*
 * x/y conversions: 
 * Takes the size of the image (in pixels) and 
 * divides it by the size of the field (in feet)
 */

	//default - (400, 30*) *27?
	//Pixels per foot
	public void setXConversion(double pixelWidth, double fieldWidth){
		this.xConversion = pixelWidth/fieldWidth;
	}

	//default - (800, 64*) *74?
	//Pixels per foot
	public void setYConversion(double pixelHeight, double fieldHeight){
		this.yConversion = pixelHeight/fieldHeight;
	}

	public double getX(){
		return xCoord;
	}
	public double getY(){
		return yCoord;
	}

	public double getXFeet(){
		return xFeet;
	}

	public double getYFeet(){
		return yFeet;
	}
	
	public double getXConversion(){
		return xConversion;
	}

	public double getYConversion(){
		return yConversion;
	}
}
