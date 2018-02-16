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
import org.usfirst.frc.team2849.controls.intake.AutoIntake;
import org.usfirst.frc.team2849.controls.intake.BumperTriggerIntake;
import org.usfirst.frc.team2849.controls.intake.NullIntake;
import org.usfirst.frc.team2849.controls.led.AutoLED;
import org.usfirst.frc.team2849.controls.led.NullLED;
import org.usfirst.frc.team2849.controls.led.TeleopLED;
import org.usfirst.frc.team2849.controls.lift.AutoLift;
import org.usfirst.frc.team2849.controls.lift.NullLift;
import org.usfirst.frc.team2849.controls.lift.XYLift;
import org.usfirst.frc.team2849.diagnostics.DebugSelector;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.diagnostics.PDP;

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
	
	ControlLayout cont;
	Drive drive;
	LED led;
	XboxController xbox;
	AutoSelector autoSelect;
	AutoBuilder autoBuilder;
	PDP pdp;
	DebugSelector debugSelect;
	
	private Intake intake;
	private Lift lift;

	private Encoder enc;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Logger.setLevel(LogLevel.DEBUG);
		Logger.log("********ROBOT PROGRAM STARTING********", LogLevel.INFO);
		cont = new ControlLayout(new NullDrive(), new NullIntake(), new NullLift(), new NullLED());
		autoSelect = new AutoSelector(5);
		drive = new Drive(DRIVE_FRONT_LEFT, DRIVE_FRONT_RIGHT, DRIVE_REAR_LEFT, DRIVE_REAR_RIGHT, cont);
		autoBuilder = new AutoBuilder(cont, drive);
//		intake = new Intake(INTAKE_LEFT, INTAKE_RIGHT, cont);
		lift = new Lift(cont);
		xbox = new XboxController(CONTROLLER_PORT);
		led = new LED(cont);
		pdp = new PDP();
		drive.resetNavx();
//		Vision.visionInit();
		debugSelect = new DebugSelector();
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
		drive.resetNavx();
		cont.updateControlLayout(new AutoDrive(), new AutoIntake(), new AutoLift(), new AutoLED());
		String autoMode = "/AutoModes/0_00_path.auto";
//		String autoMode = autoBuilder.pickAutoMode(autoSelect.getStartingPosition(), 
//			autoSelect.getAutoPrefs(), AutoSelector.findAutoFiles())
		AutoTask task = autoBuilder.buildAutoMode(autoMode);
		task.start();
		Logger.setLevel(debugSelect.getLevel());
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
		cont.updateControlLayout(new ArcadeDrive(xbox, true), new BumperTriggerIntake(xbox), new XYLift(xbox), new TeleopLED());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {

	}

	public void testInit() {
		Logger.log("Started test", LogLevel.INFO);
		SmartDashboard.updateValues();
		cont.updateControlLayout(new ArcadeDrive(xbox, true), new BumperTriggerIntake(xbox), new XYLift(xbox), new TeleopLED());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		System.out.println("Left Encoder: " + drive.getLeftEncoder());
		System.out.println("Right Encoder: " + drive.getRightEncoder());
		System.out.println("Heading: " + drive.getRawHeading());
		System.out.println("");
	}

	public void disabledInit() {
		Logger.log("disabled", LogLevel.INFO);
		Logger.closeWriters();
		cont.updateControlLayout(new NullDrive(), new NullIntake(), new NullLift(), new NullLED());
	}
	
	public void disabledPeriodic() {
		drive.stop();
	}
}
