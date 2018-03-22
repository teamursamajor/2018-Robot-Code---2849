package org.usfirst.frc.team2849.autonomous;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.autonomous.LiftTask.LiftType;
import org.usfirst.frc.team2849.autonomous.TurnTask.Turntype;
import org.usfirst.frc.team2849.controls.ControlLayout;
import org.usfirst.frc.team2849.diagnostics.Logger;
import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;
import org.usfirst.frc.team2849.path.Path;
import org.usfirst.frc.team2849.path.PathReader;
import org.usfirst.frc.team2849.robot.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//TODO add comments
/**
 * Check the AutoModes folder for a file named Auto Builder Syntax.txt It
 * contains all the syntax
 * 
 * @author Evan + Sheldon wrote this on 1/16/18
 */
public class AutoBuilder {
	interface Token {
	}

	ControlLayout cont;
	private Drive drive;

	public AutoBuilder(ControlLayout cont, Drive drive) {
		this.cont = cont;
		this.drive = drive;
	}

	class ExecuteToken implements Token {
		private String scriptName;

		public ExecuteToken(String scriptName) {
			this.scriptName = "/home/lvuser/automodes/" + scriptName.trim();
		}
	}

	/**
	 * PrintToken: A token that prints all arguments passed to it
	 * 
	 * @param str String that you want to print
	 */
	class PrintToken implements Token {
		private String str; // String to be printed

		// Instantiate PrintToken class
		public PrintToken(String str) {
			this.str = str; // Set variable str to argument string
		}

		// Creates a new instance of PrintTask class
		public PrintTask makeTask(ControlLayout cont) {
			Logger.log("[TASK] Print Task", LogLevel.INFO);
			return new PrintTask(cont, str);
		}
	}

	class PathToken implements Token {
		private Path[] paths;

		public PathToken(String filename) {
			filename = filename.replace(" ", "");
			//put all paths into /automodes/paths
			paths = new PathReader("/home/lvuser/automodes/paths/" + filename + ".path", false).getPaths();
		}

		public PathTask makeTask(ControlLayout cont) {
			Logger.log("[TASK] Path Task", LogLevel.INFO);
			return new PathTask(cont, paths, drive);
		}
	}

	class IntakeToken implements Token {
		private IntakeType intake;

		public IntakeToken(String intakeType) {
			intakeType = intakeType.replace(" ", "");
			if (intakeType.equalsIgnoreCase("IN")) {
				intake = IntakeType.IN;
			} else if (intakeType.equalsIgnoreCase("OUT")) {
				intake = IntakeType.OUT;
			} else if (intakeType.equalsIgnoreCase("RUN")) {
				intake = IntakeType.RUN;
			} else if (intakeType.equalsIgnoreCase("STOP")) {
				intake = IntakeType.STOP;
			} else if (intakeType.equalsIgnoreCase("DEPLOY")) {
				intake = IntakeType.DEPLOY;
			} else if (intakeType.equalsIgnoreCase("HOLD")) {
				intake = IntakeType.HOLD;
			} else {
				intake = IntakeType.STOP;
			}
		}

		public IntakeTask makeTask(ControlLayout cont) {
			Logger.log("[TASK] Intake Task", LogLevel.INFO);
			return new IntakeTask(cont, intake);
		}
	}

	class LiftToken implements Token {
		private LiftType lift;

		public LiftToken(String liftType) {
			liftType = liftType.replace(" ", "");

			if (liftType.equalsIgnoreCase("BOTTOM")) {
				lift = LiftType.BOTTOM;
			} else if (liftType.equalsIgnoreCase("VAULT")) {
				lift = LiftType.VAULT;
			} else if (liftType.equalsIgnoreCase("SWITCH")) {
				lift = LiftType.SWITCH;
			} else if (liftType.equalsIgnoreCase("SCALE")) {
				lift = LiftType.SCALE;
			} else {
				lift = LiftType.BOTTOM;
			}
		}

		public LiftTask makeTask(ControlLayout cont) {
			Logger.log("[TASK] Lift Task", LogLevel.INFO);
			return new LiftTask(cont, LiftTask.presetToHeight(lift));
		}
	}

	class WaitToken implements Token {
		private double wait;

		public WaitToken(String waitTime) {
			waitTime = waitTime.replace(" ", "");
			if (Double.parseDouble(waitTime) >= 0) {
				wait = Double.parseDouble(waitTime);
			}
		}

		public WaitTask makeTask(ControlLayout cont) {
			Logger.log("[TASK] Wait Task", LogLevel.INFO);
			return new WaitTask(cont, (long) (wait * 1000.0d));
		}
	}

	class BundleToken implements Token {
		public BundleToken() {
		}
	}

	class SerialToken implements Token {
		public SerialToken() {
		}
	}

	class RightBraceToken implements Token {
		public RightBraceToken() {
		}
	}

	class TurnToken implements Token {
		private double turnAmount;
		private Turntype turnType;

		public TurnToken(String turn) {
			turn = turn.toLowerCase();
			if (turn.contains("to")) {
				turnType = Turntype.TURN_TO;
				turnAmount = Double.valueOf(turn.substring(turn.indexOf("to") + "TO".length()));
			} else {
				turnType = Turntype.TURN_BY;
				turnAmount = Double.valueOf(turn.substring(turn.indexOf("by") + "BY".length()));
			}
		}

		public TurnTask makeTask(ControlLayout cont) {
			Logger.log("[TASK] Turn Task", LogLevel.INFO);
			return new TurnTask(cont, turnType, turnAmount, drive);
		}
	}

	class DriveToken implements Token {
		private double dist;

		public DriveToken(String distance) {
			distance = distance.replace(" ", "");
			if (Math.abs(Double.parseDouble(distance)) >= 0) {
				dist = Double.parseDouble(distance);
			}
		}

		public DriveTask makeTask(ControlLayout cont) {
			Logger.log("[TASK] Drive Task", LogLevel.INFO);
			//TODO should dist be an int in the first place? it's parsed as a double but drivetask only takes ints
			return new DriveTask(cont, (int) dist, drive);
		}
	}
	
	/**
	 * Interprets specified file to identify keywords as tokens to add to a collective ArrayList
	 * @param filename
	 * 		Name of file to tokenize
	 * @return ArrayList of all tokens in ranking order
	 * @throws IOException
	 */
	private ArrayList<Token> tokenize(String filename) throws IOException {
		ArrayList<Token> ret = new ArrayList<Token>();
		BufferedReader buff;
		buff = new BufferedReader(new FileReader(filename));
		String line = null;
		while ((line = buff.readLine()) != null) {
			if (line.contains("#")) { // If the line is a comment
				continue;
			} else if (line.contains("follow")) { // If the line is a path token
				String current = line.substring(line.indexOf("follow") + "follow".length());
				// The path to follow (name of path file) is what comes after the token "follow"
				ret.add(new PathToken(current)); // Adds new path token to the ArrayList of all tokens
			} else if (line.contains("execute")) { // If the line is an execute token
				String current = line.substring(line.indexOf("execute") + "execute".length());
				// The automode to execute (name of auto file) is what comes after the token "execute"
				ret.add(new ExecuteToken(current)); // Adds new execute token to the ArrayList of all tokens
			} else if (line.contains("wait")) { // If the line is a wait token
				String current = line.substring(line.indexOf("wait") + "wait".length());
				// The length (in seconds) to wait is what comes after the token "wait"
				ret.add(new WaitToken(current)); // Adds new wait token to the ArrayList of all tokens
			} else if (line.contains("drive")) { // If the line is a drive token
				String current = line.substring(line.indexOf("drive") + "drive".length());
				// The length (in feet) to drive is what comes after the token "drive"
				ret.add(new DriveToken(current)); // Adds new drive token to the ArrayList of all tokens
			} else if (line.contains("turn")) { // If the line is a turn token
				String current = line.substring(line.indexOf("turn") + "turn".length());
				// The type and amount in degrees to turn is what comes after the token "turn"
				ret.add(new TurnToken(current)); // Adds new turn token to the ArrayList of all tokens
			} else if (line.contains("lift")) { // If the line is a lift token
				String current = line.substring(line.indexOf("lift") + "lift".length());
				// The preset height to lift to is what comes after the token "lift"
				ret.add(new LiftToken(current)); // Adds new lift token to the ArrayList of all tokens
			} else if (line.contains("intake")) { // If the line is an intake token
				String current = line.substring(line.indexOf("intake") + "intake".length());
				// The intake mode is what comes after the token "intake"
				ret.add(new IntakeToken(current)); // Adds new intake token to the ArrayList of all tokens
			} else if (line.contains("print")) { // If the line is a print token
				String current = line.substring(line.indexOf("print") + "print".length());
				// The data to print is everything that comes after the token "print"
				ret.add(new PrintToken(current)); // Adds new print token to the ArrayList of all tokens
			} else if (line.contains("bundle")) { // If the line is a bundle token
				ret.add(new BundleToken()); // Adds new bundle token to the ArrayList of all tokens
			} else if (line.contains("serial")) { // If the line is a serial token
				ret.add(new SerialToken()); // Adds new serial token to the ArrayList of all tokens
			} else if (line.contains("}")) { // If the line is a right brace (ends a group task)
				ret.add(new RightBraceToken()); // Adds new right brace token to the ArrayList of all tokens
			}
		}
		buff.close();
		return ret; // Returns ArrayList of all tokens
	}

	private AutoTask parseAuto(ArrayList<Token> toks, GroupTask ret) {
		if (toks.size() == 0) {
			Logger.log("[TASK] Wait Task", LogLevel.INFO);
			return new WaitTask(cont, 0);
		}

		while (toks.size() > 0) {
			Token t = toks.remove(0);
			if (t instanceof ExecuteToken) {
				AutoTask otherMode = buildAutoMode(((ExecuteToken) t).scriptName);
				ret.addTask(otherMode);
			} else if (t instanceof WaitToken) {
				ret.addTask(((WaitToken) t).makeTask(cont));
			} else if (t instanceof DriveToken) {
				ret.addTask(((DriveToken) t).makeTask(cont));
			} else if (t instanceof TurnToken) {
				ret.addTask(((TurnToken) t).makeTask(cont));
			} else if (t instanceof PathToken) {
				ret.addTask(((PathToken) t).makeTask(cont));
			} else if (t instanceof IntakeToken) {
				ret.addTask(((IntakeToken) t).makeTask(cont));
			} else if (t instanceof LiftToken) {
				ret.addTask(((LiftToken) t).makeTask(cont));
			} else if (t instanceof BundleToken) {
				ParallelTask p = new ParallelTask(cont);
				parseAuto(toks, p);
				ret.addTask(p);
			} else if (t instanceof SerialToken) {
				SerialTask p = new SerialTask(cont);
				parseAuto(toks, p);
				ret.addTask(p);
			} else if (t instanceof RightBraceToken) {
				return ret;
			} else if (t instanceof PrintToken) {
				ret.addTask(((PrintToken) t).makeTask(cont));
			}
		}

		return ret;
	}

	public AutoTask buildAutoMode(String filename) {
		try {
			return parseAuto(tokenize(filename), new SerialTask(cont));
		} catch (IOException e) {
			e.printStackTrace();
			Logger.log("AutoBuilder buildAutoMode method parseAuto printStackTrace", LogLevel.ERROR);
			return null;
		}
	}

	/**
	 * Gets the switch/scale side from the FMS, and finds an Auto Mode file
	 * which finds robot's position and switch/scale ownership and performs the
	 * highest task in the ranked list of tasks from the SmartDashboard that
	 * matches the current setup.
	 * 
	 * @param robotPosition
	 *            The side our robot starts on. L, M, or R.
	 * @param autoPrefs
	 *            String array of ranked Auto Modes
	 * @param autoFiles
	 *            File array of all files in the AutoModes folder
	 * @return String name of the auto file to run
	 */
	public String pickAutoMode(char robotPosition, String[] autoPrefs, File[] autoFiles) {
		// Gets the ownership information from the FMS
		String switchPos = DriverStation.getInstance().getGameSpecificMessage().substring(0,1);
		String scalePos = DriverStation.getInstance().getGameSpecificMessage().substring(1,2);
		String mode;
		System.out.println(switchPos + scalePos);
		
		SmartDashboard.putString("Switch side: ", switchPos);
		SmartDashboard.putString("Scale side: ", scalePos);
		
		switch (switchPos + scalePos) {
		case "LL":
			mode = autoPrefs[0];
			break;
		case "LR":
			mode = autoPrefs[1];
			break;
		case "RL":
			mode = autoPrefs[2];
			break;
		case "RR":
			mode = autoPrefs[3];
			break;
		default:
			mode = "path_drive";
			break;
		}
		
		//for potential future use
		String oppSide = DriverStation.getInstance().getGameSpecificMessage().substring(2);
		
		String regex = "^[" + robotPosition + "0]_[" + switchPos + "0][" + scalePos + "0]_" + mode + "\\.auto$";
		
		for (File f: autoFiles) {
			if (f.getName().matches(regex)) {
				return "/home/lvuser/automodes/" + f.getName();
			}
		}
		return "/home/lvuser/automodes/0_00_path_drive.auto";
	}
}
