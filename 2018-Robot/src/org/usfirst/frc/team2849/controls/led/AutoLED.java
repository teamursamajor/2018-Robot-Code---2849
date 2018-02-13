package org.usfirst.frc.team2849.controls.led;

public class AutoLED implements LEDControl {
	
	int rCounter = 0;
	int gCounter = 0;
	int bCounter = 0;
	
	private int rPercent = 54;
	private int gPercent = 9;
	private int bPercent = 99;

	@Override
	public boolean getR() {
		// TODO Auto-generated method stub
		
		//old code:
		//return false;
		
		rCounter ++;
		
		if(rCounter > 100) {
			rCounter = 1;
		}
		
		if(rPercent >= rCounter) {
			return true;
		}
		else {
			return false;
		}
		
		
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