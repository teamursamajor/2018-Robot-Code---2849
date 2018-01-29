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
import org.usfirst.frc.team2849.controls.TankDrive;
import org.usfirst.frc.team2849.controls.TestControl;
import org.usfirst.frc.team2849.controls.XboxController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot implements UrsaRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String autoSelected;
	private SendableChooser<String> autoChooser = new SendableChooser<>();
	
	ControlLayout tankDriveCont;
	ControlLayout testCont;
	AutoControl autoCont;
	Drive drive;
	XboxController xbox;
	AutoSelector autoSelect;
	
	private Intake intake;
	
	private Encoder enc;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		autoSelect = new AutoSelector(5);
		tankDriveCont = new TankDrive(CONTROLLER_PORT);
		testCont = new TestControl(CONTROLLER_PORT);
		autoCont = new AutoControl();
		intake = new Intake(INTAKE_LEFT, INTAKE_RIGHT, tankDriveCont);
		drive = new Drive(DRIVE_FRONT_LEFT, DRIVE_FRONT_RIGHT, DRIVE_REAR_LEFT, DRIVE_REAR_RIGHT, tankDriveCont);
//		autoChooser.addDefault("Default Auto", kDefaultAuto);
//		autoChooser.addObject("My Auto", kCustomAuto);
//		SmartDashboard.putData("Auto choices", autoChooser);
		xbox = new XboxController(CONTROLLER_PORT);
	}
	

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		Drive.resetNavx();
		Drive.setControlScheme(autoCont);
		AutoTask task = new AutoBuilder(autoCont).buildAutoMode("/AutoModes/RR_R0_switch.auto");
		task.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	}
	
	public void teleopInit() {
		Drive.setControlScheme(tankDriveCont);
	}


	/**
	 * This function is called periodically during operator control.
	 */

	Solenoid green = new Solenoid(5);
	Solenoid red = new Solenoid(6);
	Solenoid blue = new Solenoid(7);
	boolean R = false;
	boolean G = false;
	boolean B = false;
	boolean W = false;
	long lighttime = 0;
	@Override
	public void teleopPeriodic() {
//		Pathfinder.findposition();//this should generally always be running whenever
		//the robot is moving and therefore changing position.
		if(System.currentTimeMillis()-100 > lighttime){
			if(xbox.getButton(XboxController.BUTTON_B) == true){
				R = !R;
				red.set(R);
				lighttime = System.currentTimeMillis();
			}
			if(xbox.getButton(XboxController.BUTTON_A) == true){
				G = !G;
				green.set(G);
				lighttime = System.currentTimeMillis();
			}
			if(xbox.getButton(XboxController.BUTTON_X) == true){
				B = !B;
				blue.set(B);
				lighttime = System.currentTimeMillis();
			}
			if(xbox.getButton(XboxController.BUTTON_Y) == true){
				W= !W;
				red.set(true);
				green.set(false);
				blue.set(false);
				if(W == true){
					try {
						Thread.sleep(1000);
						green.set(true);
						Thread.sleep(1000);
						red.set(false);
						Thread.sleep(1000);
						blue.set(true);
						Thread.sleep(1000);
						green.set(false);
						Thread.sleep(1000);
						red.set(true);
						Thread.sleep(1000);
						blue.set(false);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					red.set(true);
					green.set(true);
					blue.set(true);
				}
				lighttime = System.currentTimeMillis();
			}
		}
	}
	
	public void testInit() {
		SmartDashboard.updateValues();
		Drive.setControlScheme(tankDriveCont);
		Intake.setControlScheme(testCont);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		testCont.runIntake();
	}
	
	public void disabledInit() {
		Drive.setControlScheme(new NullControl());
	}
}
