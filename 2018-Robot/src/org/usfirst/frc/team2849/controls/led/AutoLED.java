package org.usfirst.frc.team2849.controls.led;

public class AutoLED implements LEDControl {
	


	@Override
	public boolean getR() {
		// TODO Auto-generated method stub
		return ColorsLED.getRed();	
	}

	@Override
	public boolean getG() {
		// TODO Auto-generated method stub
		return ColorsLED.getGreen();
	}

	@Override
	public boolean getB() {
		// TODO Auto-generated method stub
		return ColorsLED.getBlue();
	}
}
