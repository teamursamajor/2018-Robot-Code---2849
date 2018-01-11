package org.usfirst.frc.team2849.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;

public class Pathfinder implements PIDOutput {
	double[] position;
	PIDController PIDcont;
	AHRS ahrs;
	
	double currentangle = 0;
	void init(){
		position=new double[]{0,0};
		try {
	          /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
	          /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
	          /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
	          ahrs = new AHRS(SPI.Port.kMXP); 
	      } catch (RuntimeException ex ) {
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	      }
		PIDcont = new PIDController(0.3d, 0d, 0d, 0d, ahrs, this);
	    PIDcont.setInputRange(-180.0f,  180.0f);
	    PIDcont.setOutputRange(-180.0f,  180.0f);
	    PIDcont.setAbsoluteTolerance(2.0f);
	    PIDcont.setContinuous(true);
	}
	public void pidWrite(double output) {
	}
}
