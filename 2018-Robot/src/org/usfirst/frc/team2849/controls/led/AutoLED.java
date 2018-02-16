package org.usfirst.frc.team2849.controls.led;

public class AutoLED implements LEDControl {
	
	int rCounter = 0;
	int gCounter = 0;
	int bCounter = 0;
	
	private int rPercent = 50;
	private int gPercent = 0;
	private int bPercent = 0;
	
	private double levelCount = 1.0;
	private int aimCount=1;
	private int timesCount =1;
	private boolean output;

	@Override
	public boolean getR() {
		// TODO Auto-generated method stub
		
		double percentCount = rPercent / 100.0;
		output =false;
		
		levelCount += percentCount;
		int hit = (int)levelCount;
		
		if(hit == aimCount) {
			aimCount ++;
			output = true;
		}
		if(timesCount >100) {
			timesCount = 1;
			levelCount = 0.0;
			aimCount = 1;
		}
		timesCount ++;
		return output;
		
		
	}

	@Override
	public boolean getG() {
		// TODO Auto-generated method stub
		//return false;
		
		gCounter ++;
		
		if(gCounter > 100) {
			gCounter = 1;
		}
		
		if(gPercent >= gCounter) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean getB() {
		// TODO Auto-generated method stub
		//return false;
		
		bCounter ++;
		
		if(bCounter > 100) {
			bCounter = 1;
		}
		
		if(bPercent >= bCounter) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void setRPercent(int r) {
		rPercent = r;
	}
	public void setGPercent(int g) {
		gPercent = g;
	}
	public void setBPercent(int b) {
		bPercent = b;
	}
	
	
	public int getRPercent() {
		return rPercent;
	}
	public int getGPercent() {
		return gPercent;
	}
	public int getBPercent() {
		return bPercent;
	}
	
	

}
