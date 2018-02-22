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
import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.controls.XboxController;
import org.usfirst.frc.team2849.controls.drive.ArcadeDrive;
import org.usfirst.frc.team2849.controls.drive.AutoDrive;
import org.usfirst.frc.team2849.controls.drive.NullDrive;
//import org.usfirst.frc.team2849.controls.drive.TankDrive;
import org.usfirst.frc.team2849.controls.intake.AutoIntake;
import org.usfirst.frc.team2849.controls.intake.BumperTriggerIntake;
import org.usfirst.frc.team2849.controls.intake.NullIntake;
import org.usfirst.frc.team2849.controls.led.AutoLED;
import org.usfirst.frc.team2849.controls.led.ColorsCheck;
import org.usfirst.frc.team2849.controls.led.NullLED;
import org.usfirst.frc.team2849.controls.led.TeleopLED;
import org.usfirst.frc.team2849.controls.lift.AutoLift;
import org.usfirst.frc.team2849.controls.lift.NullLift;
import org.usfirst.frc.team2849.controls.lift.XYLift;
import org.usfirst.frc.team2849.diagnostics.DebugSelector;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.diagnostics.PDP;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot implements UrsaRobot {
	
	ControlLayout cont;
	Drive drive;
	LED led;
	XboxController xbox;
	AutoSelector autoSelect;
	AutoBuilder autoBuilder;
	PDP pdp;
	DebugSelector debugSelect;
	DigitalInput limSwitch;
	String robotMode;
	ColorsCheck colorsCheck;
	
	private Intake intake;
	private Lift lift;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Logger.setLevel(LogLevel.DEBUG);
		Logger.log("********ROBOT PROGRAM STARTING********", LogLevel.INFO);
		xbox = new XboxController(0);
		cont = new ControlLayout(new NullDrive(), new NullIntake(), new NullLift(), new NullLED());
		autoSelect = new AutoSelector();
		drive = new Drive(DRIVE_FRONT_LEFT, DRIVE_FRONT_RIGHT, DRIVE_REAR_LEFT, DRIVE_REAR_RIGHT, cont);
		autoBuilder = new AutoBuilder(cont, drive);
		intake = new Intake(INTAKE_LEFT, INTAKE_RIGHT, cont);
		lift = new Lift(cont);
		led = new LED(cont);
		pdp = new PDP();
		colorsCheck = new ColorsCheck(cont);
		drive.resetNavx();
		Vision.visionInit();
		debugSelect = new DebugSelector();
		Logger.setLevel(debugSelect.getLevel());
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 * 
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		Logger.log("Started Autonomous mode", LogLevel.INFO);
		robotMode = "Autonomous";
		drive.resetNavx();
		cont.updateControlLayout(new AutoDrive(), new AutoIntake(), new AutoLift(), new AutoLED());
//		For setting autoMode manually (for testing)
//	 	String autoMode = "/home/lvuser/automodes/L_L0_path_switch.auto";
		String autoMode = autoBuilder.pickAutoMode(autoSelect.getStartingPosition(), 
			autoSelect.getAutoPrefs(), autoSelect.findAutoFiles());
		System.out.println("Now running " + autoMode);
		AutoTask task = autoBuilder.buildAutoMode(autoMode);
		task.start();
		System.out.println(task.toString());
		Logger.log("Current Auto Mode: " + autoMode, LogLevel.INFO);
		Logger.setLevel(debugSelect.getLevel());
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is run when teleop mode is first started up and should be
	 * used for any teleop initialization code.
	 */
	public void teleopInit() {
		Logger.log("Started Teleop mode", LogLevel.INFO);
		robotMode = "Teleop";
		System.out.println(cont);
		cont.updateControlLayout(new ArcadeDrive(xbox, true), new BumperTriggerIntake(xbox), new XYLift(xbox), new TeleopLED());
		Logger.setLevel(debugSelect.getLevel());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
	}
	
	/**
	 * This function is run when test mode is first started up and should be
	 * used for any test initialization code.
	 */
	public void testInit() {
		Logger.log("Started Test mode", LogLevel.INFO);
		robotMode = "Test";
//		SmartDashboard.updateValues(); <-- TODO what is this for? is it still needed?
//		For anyone who wants Tank Drive: change "new ArcadeDrive" to "new TankDrive" and uncomment the TankDrive import
		cont.updateControlLayout(new ArcadeDrive(xbox, true), new BumperTriggerIntake(xbox), new XYLift(xbox), new TeleopLED());
		Logger.setLevel(debugSelect.getLevel());
		limSwitch = new DigitalInput(4);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
//		System.out.println("Left Encoder: " + drive.getLeftEncoder());
//		System.out.println("Right Encoder: " + drive.getRightEncoder());
//		System.out.println("Heading: " + drive.getRawHeading());
//		System.out.println("");
		System.out.println(limSwitch.get());
	}

	/**
	 * This function is run when a mode is initially disabled and should be
	 * used for any disabling code.
	 */
	public void disabledInit() {
		Logger.log("Disabled " + robotMode + " mode", LogLevel.INFO);
		Logger.closeWriters();
		cont.updateControlLayout(new NullDrive(), new NullIntake(), new NullLift(), new NullLED());
	}
	
	/**
	 * This function is called periodically when a mode is disabled.
	 */
	public void disabledPeriodic() {
		drive.stop();
	}
}
