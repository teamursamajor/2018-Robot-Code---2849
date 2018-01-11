package org.usfirst.frc.team2849.robot;

import edu.wpi.first.wpilibj.PIDController;

import java.awt.Color;

import com.kauailabs.navx.frc.AHRS;

public class Pathfinder {
	double[] position;
	PIDController turnController;
	AHRS ahrs;
	void init() {
		position = new double[] {0,0};
	}
}
