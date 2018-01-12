package org.usfirst.frc.team2849.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;

public class Pathfinder{
	static double[] position;
	//position[0] = distance left right from starting position
	//position[1] = distance down field away from game station
	PIDController PIDcont;
	static AHRS ahrs;
	final static int encoderchannelA = 4;// change this to the encoder channel
	final static int encoderchannelB = 5;// change this to the encoder channel
	static Encoder encoder = new Encoder(encoderchannelA,encoderchannelB);
	double currentangle = 0;
	final static int wheelradius = 4;//inches
	void init(){
		position=new double[]{0,0};
		prevdist = 0;
		try {
	          ahrs = new AHRS(SPI.Port.kMXP); 
	      } catch (RuntimeException ex ) {
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	      }
		encoder.setDistancePerPulse(Math.PI*wheelradius*wheelradius);
		ahrs.reset();
	}
	static double prevdist;
	static void findposition(){
		double now = encoder.getDistance();
		double mov = now-prevdist;
		position= new double[]{position[0]+mov*Math.sin(ahrs.getAngle()),position[1]+mov*Math.cos(ahrs.getAngle())};
		prevdist=now;
	}

}
