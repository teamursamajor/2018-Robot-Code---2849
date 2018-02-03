/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.autonomous.AutoBuilder;
import org.usfirst.frc.team2849.autonomous.AutoSelector;
import org.usfirst.frc.team2849.autonomous.AutoTask;
import org.usfirst.frc.team2849.controls.AutoControl;
import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.controls.NullControl;
import org.usfirst.frc.team2849.controls.TankDriveControl;
import org.usfirst.frc.team2849.controls.TestControl;
import org.usfirst.frc.team2849.controls.XboxController;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot implements UrsaRobot {
	ControlLayout tankDriveCont;
	ControlLayout testCont;
	AutoControl autoCont;
	Drive drive;
	XboxController xbox;
	AutoSelector autoSelect;
	AutoBuilder autoBuilder;

	private Intake intake;

	private Encoder enc;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		autoSelect = new AutoSelector(5);
		tankDriveCont = new TankDriveControl(CONTROLLER_PORT);
		testCont = new TestControl(CONTROLLER_PORT);
		autoCont = new AutoControl();
		autoBuilder = new AutoBuilder(autoCont);
		intake = new Intake(INTAKE_LEFT, INTAKE_RIGHT, tankDriveCont);
		drive = new Drive(DRIVE_FRONT_LEFT, DRIVE_FRONT_RIGHT, DRIVE_REAR_LEFT, DRIVE_REAR_RIGHT, tankDriveCont);
		xbox = new XboxController(CONTROLLER_PORT);
		Logger.initLogger();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		Logger.log("Started auto", LogLevel.INFO);
		Drive.resetNavx();
		setControlScheme(autoCont);
		String autoMode = "/AutoModes/0_00_drive.auto";
//		String autoMode = autoBuilder.pickAutoMode(autoSelect.getStartingPosition(), 
//			autoSelect.getAutoPrefs(), AutoSelector.findAutoFiles())
		AutoTask task = autoBuilder.buildAutoMode(autoMode);
		task.start();
		Logger.log("Current Auto Mode: " + autoMode, LogLevel.INFO);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	}

	public void teleopInit() {
		Logger.log("Started teleop", LogLevel.INFO);
		setControlScheme(tankDriveCont);
	}

	/**
	 * This function is called periodically during operator control.
	 */

	// Solenoid green = new Solenoid(5);
	// Solenoid red = new Solenoid(6);
	// Solenoid blue = new Solenoid(7);
	// boolean R = false;
	// boolean G = false;
	// boolean B = false;
	// boolean W = false;
	// long lighttime = 0;
	// @Override
	public void teleopPeriodic() {
		//// Pathfinder.findposition();
		//this should generally always be running whenever the robot is moving and therefore changing position.
		// if(System.currentTimeMillis()-100 > lighttime){
		// if(xbox.getButton(XboxController.BUTTON_B) == true){
		// R = !R;
		// red.set(R);
		// lighttime = System.currentTimeMillis();
		// }
		// if(xbox.getButton(XboxController.BUTTON_A) == true){
		// G = !G;
		// green.set(G);
		// lighttime = System.currentTimeMillis();
		// }
		// if(xbox.getButton(XboxController.BUTTON_X) == true){
		// B = !B;
		// blue.set(B);
		// lighttime = System.currentTimeMillis();
		// }
		// if(xbox.getButton(XboxController.BUTTON_Y) == true){
		// W= !W;
		// red.set(true);
		// green.set(false);
		// blue.set(false);
		// if(W == true){
		// try {
		// Thread.sleep(1000);
		// green.set(true);
		// Thread.sleep(1000);
		// red.set(false);
		// Thread.sleep(1000);
		// blue.set(true);
		// Thread.sleep(1000);
		// green.set(false);
		// Thread.sleep(1000);
		// red.set(true);
		// Thread.sleep(1000);
		// blue.set(false);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// else{
		// red.set(true);
		// green.set(true);
		// blue.set(true);
		// }
		// lighttime = System.currentTimeMillis();
		// }
		// }
	}

	public void testInit() {
		Logger.log("Started test", LogLevel.INFO);
		SmartDashboard.updateValues();
		setControlScheme(testCont);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public void disabledInit() {
		Logger.log("disabled", LogLevel.INFO);
		Drive.setControlScheme(new NullControl());
	}
	/**
	 * Sets the control scheme for all subsystems to the scheme parameter
	 * @param scheme Desired control scheme
	 */
	public void setControlScheme(ControlLayout scheme){
		Drive.setControlScheme(scheme);
		intake.setControlScheme(scheme);
		Lift.setControlScheme(scheme);
		
		
	}
}
