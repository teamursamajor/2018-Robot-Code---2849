/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2849.robot;

import org.usfirst.frc.team2849.autonomous.AutoTask;
import org.usfirst.frc.team2849.autonomous.DriveTask;
import org.usfirst.frc.team2849.autonomous.UrsaScript_AutoBuilder;
import org.usfirst.frc.team2849.robot.Logger.Level;

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
//		Logger log = new Logger("testFile.txt");
//		log.log("Wow, maybe this works, good job ayo", Level.INFO);
//		//log.log("FIrst test file", Level.INFO);
//		log.write("Test");
//		log.trial();
//		Logger log0 = new Logger();
		//System.out.println(log.isNull());
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
		Drive.resetNavx();
		// temporary, set only for testing driveDistance
		AutoTask task = new UrsaScript_AutoBuilder().buildAutoMode("/AutoModes/RR_R0_switch.auto");
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
		
		Drive.drive(xbox.getAxis(XboxController.AXIS_LEFTSTICK_Y), xbox.getAxis(XboxController.AXIS_RIGHTSTICK_Y), true);
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

	/**
	 * This function is called periodically during test mode.
	 */
	int count = 0;
	@Override
	public void testPeriodic() {

		
//		Pathfinder.findposition();//this should generally always be running whenever
		//the robot is moving and therefore changing position.
		int flip = 1;
		if (xbox.getButton(1)) flip = -1;
		else flip = 1;
		Drive.drive(flip * xbox.getAxis(XboxController.AXIS_LEFTTRIGGER), flip * xbox.getAxis(XboxController.AXIS_RIGHTTRIGGER), false);
	}
}
