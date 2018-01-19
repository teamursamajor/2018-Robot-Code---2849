/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.autonomous.AutoTask;
import org.usfirst.frc.team2849.autonomous.DriveDistance;
import org.usfirst.frc.team2849.autonomous.UrsaScript_AutoBuilder;

import autonomous.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	private Drive drive;
	private XboxController xbox;
	private Encoder enc;
	
	private Thread autoThread;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drive = new Drive(2, 3, 0, 1);
		xbox = new XboxController(0);
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
//		new Pathfinder().init();
		Logger log = new Logger();
		log.write("test.txt", "charlie sux");
		//log.trial();
				
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
		// temporary, set only for testing driveDistance
		AutoTask task = new UrsaScript_AutoBuilder().buildAutoMode("/autotest.auto");
		Thread t = new Thread(task);
		t.start();
/* m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected); */
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
//		Pathfinder.findposition();//this should generally always be running whenever
		//the robot is moving and therefore changing position.
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
//		Pathfinder.findposition();//this should generally always be running whenever
		
		Drive.drive(xbox.getAxis(XboxController.AXIS_LEFTSTICK_Y), xbox.getAxis(XboxController.AXIS_RIGHTSTICK_Y), true);
		//the robot is moving and therefore changing position.
	}

	/**
	 * This function is called periodically during test mode.
	 */
	int count = 0;
	@Override
	public void testPeriodic() {
//		Pathfinder.findposition();//this should generally always be running whenever
		//the robot is moving and therefore changing position.
		int flip = -1;
		if (xbox.getButton(1)) flip = 1;
		else flip = -1;
		Drive.drive(flip * xbox.getAxis(XboxController.AXIS_LEFTTRIGGER), flip * xbox.getAxis(XboxController.AXIS_RIGHTTRIGGER), false);
		Drive.drive(xbox.getAxis(XboxController.AXIS_LEFTSTICK_Y), xbox.getAxis(XboxController.AXIS_RIGHTSTICK_Y), true);
		if (count++%10 == 0) {
		System.out.println("Left Encoder: " + Drive.getLeftEncoder());
		System.out.println("Right Encoder: " + Drive.getRightEncoder());
		}
	}
}
