package org.usfirst.frc.team2849.controls.led;

public class TeleopLED implements LEDControl {
	
	@Override
	public boolean getR() {
		return ColorsLED.getRed();
	}

	@Override
	public boolean getG() {
		return ColorsLED.getGreen();
	}

	@Override
	public boolean getB() {
		return ColorsLED.getBlue();
	}

}
