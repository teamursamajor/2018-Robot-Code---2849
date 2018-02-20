package org.usfirst.frc.team2849.diagnostics;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.robot.UrsaRobot;

import edu.wpi.first.wpilibj.Solenoid;

public class LEDDiagnosticPanel extends Thread {
	Solenoid[] solenoids = new Solenoid[6];
	Boolean[] conditions = new Boolean[solenoids.length];
	String returnvalue = "the LED Diagnostic Panel is not currently functioning, also Greetings from the past";
	int positive = 10;

	LEDDiagnosticPanel() {
		solenoids[0] = new Solenoid(UrsaRobot.LED_Solenoid_port_1);
		solenoids[1] = new Solenoid(UrsaRobot.LED_Solenoid_port_2);
		solenoids[2] = new Solenoid(UrsaRobot.LED_Solenoid_port_3);
		solenoids[3] = new Solenoid(UrsaRobot.LED_Solenoid_port_4);
		solenoids[4] = new Solenoid(UrsaRobot.LED_Solenoid_port_5);
		solenoids[5] = new Solenoid(UrsaRobot.LED_Solenoid_port_6);
		start();
	}

	public void run() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Logger.log("LED Diagnostic Thread.sleep call, printStackTrace", LogLevel.ERROR);
		}
		for (int i = 0; i < conditions.length; i++) {
			solenoids[i].set(conditions[i]);
		}
		if (positive > 0) {
			positive--;
		} else {
			returnvalue = "the LED Diagnostic Panel is currently functioning";
		}
	}
}
