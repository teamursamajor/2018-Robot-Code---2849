package com.teamursamajor.auto;

public class AnchorPoint {
	private double xCoord, yCoord;
	private double defaultInflectionPoint;// for flipping over x axis
	private int halfImageHeight = 400; //Half of the image's height
	
	//Width of the image frame/2:
	int width = 800/2;
	
	public AnchorPoint (double xCoord, double yCoord, double inflectionPoint){
		this.xCoord=xCoord;
		this.yCoord=yCoord;
		this.defaultInflectionPoint = inflectionPoint;
	}
	
	public AnchorPoint (double xCoord, double yCoord){
		this.xCoord=xCoord;
		this.yCoord=yCoord;
		defaultInflectionPoint = xCoord;
	}

	//TODO - no static
	//TODO - test
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

	//TODO - feet to pixel method
	//Field - 27*54 feet
	//Driver Station - 30*10 feet x 2
	/*
	 *Start measuring from the end of the driver station 
	 */
	public double[] feetToPixels(double xFeet, double yFeet){
		//Driver Station Measurements
		double xConv = getXConversion();
		double yConv = getYConversion();

		//xFeet += 30*xConv;
		yFeet += 10*yConv; //10 = height of the driver station (in feet);
		
		xFeet *= xConv;
		yFeet *= yConv;

		double [] res = {xConv, yConv};
		return res;
	}	


/*
 * x/y conversions: 
 * Takes the size of the image (in pixels) and 
 * divides it by the size of the field (in feet)
 */

	public double getXConversion(){
		return getXConversion(400, 30);
	}

	//default - (400, 30*) *27?
	public double getXConversion(int pixelWidth, int fieldWidth){
		return pixelWidth/fieldWidth;
	}


	public double getYConversion(){
		return getYConversion(64, 800);
	}
	
	//default - (800, 64*) *74?
	public double getYConversion(int pixelHeight, int fieldHeight){
		return pixelHeight/fieldHeight;
	}
	/*
	 * Rocket:
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	public double getX (){
		return xCoord*getXConversion();
	}
	public double getY (){
		return yCoord*getYConversion();
	}
}
