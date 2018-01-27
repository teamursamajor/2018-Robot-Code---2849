package org.usfirst.frc.team2849.autonomous;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;
import org.usfirst.frc.team2849.autonomous.TurnTask.Turntype;
import org.usfirst.frc.team2849.controls.AutoControl;
import org.usfirst.frc.team2849.controls.ControlLayout;

/**
 * TODO Add comments
 * @author Evan + Sheldon wrote this on 1/16/18
 * Parser/scripting language for auto functions
 * Syntax:
 * comments start with # --> on their own line!!!!!!!!!!!!!!!!!!!!!
 * indentation is three spaces
 * wait <amount in seconds, type double>
 * execute <file directory>
 * lift <amount in inches>||<BOTTOM||VAULT||SWITCH||SCALE)>
 * drive <amount in inches>
 * turn <TO||BY> <amount in degrees> 
 * intake <IN||OUT||RUN||STOP||DEPLOY>
 * print <STRING>
 * bundle {...} - runs parallel tasks
 * serial {...} - runs tasks sequentially
 */
public class AutoBuilder {
	interface Token{}
	enum LiftType{BOTTOM, VAULT, SWITCH, SCALE };
	AutoControl cont;
	
	public AutoBuilder(AutoControl cont) {
		this.cont = cont;
	}
	
	class ExecuteToken implements Token {
		private String scriptName;
		public ExecuteToken(String scriptName){
			this.scriptName = scriptName.trim();
		}
	}
	
	/* PrintToken:
	 *     A token that prints all arguments passed to it
	 * 
	 * @param str  String that you want to print
	 */
	class PrintToken implements Token {
		private String str; // String to be printed
		
		//Instantiate PrintToken class
		public PrintToken(String str){
			this.str = str; // Set variable str to argument string
		}
		
		//Creates a new instance of PrintTask class
		public PrintTask makeTask(AutoControl cont) {
			return new PrintTask(cont, str);
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
			} else {
				intake = IntakeType.UNTIL;
			}
		}
		public IntakeTask makeTask(AutoControl cont) {
			return new IntakeTask(cont, intake);
		}
	}
	
	//TODO check lift heights
	class LiftToken implements Token {
		private double lift;
		public LiftToken(String liftType){
			liftType = liftType.replace(" ","");
			if(liftType.equalsIgnoreCase("BOTTOM")){
				lift = 0;
			} else if(liftType.equalsIgnoreCase("VAULT")){
				lift = 5;
			} else if(liftType.equalsIgnoreCase("SWITCH")){
				lift = 20;
			} else if(liftType.equalsIgnoreCase("SCALE")){
				lift = 80;
			} else if(Double.parseDouble(liftType) >= 0) {
				lift = Double.parseDouble(liftType);
			}
		}
		public LiftTask makeTask(AutoControl cont) {
			return new LiftTask(cont, lift);
		}
	}
	
	class WaitToken implements Token {
		private double wait;
		public WaitToken(String waitTime){
			waitTime = waitTime.replace(" ","");
			if(Double.parseDouble(waitTime) >= 0) {
				wait = Double.parseDouble(waitTime);
			}
		}
		public WaitTask makeTask(AutoControl cont) {
			return new WaitTask(cont, (long) (wait*1000.0d));
		}
	}
	
	class BundleToken implements Token {
		public BundleToken() {}
	}
	
	class SerialToken implements Token {
		public SerialToken() {}
	}
	
	class RightBraceToken implements Token {
		public RightBraceToken() {}
	}
	
	class TurnToken implements Token {
		private double turnAmount;
		private Turntype turnType;
		public TurnToken(String turn) {
			if(turn.contains("TO")) {
				turnType = Turntype.TURN_TO;
				turnAmount = Double.valueOf(turn.substring(turn.indexOf("TO") + "TO".length()));
			}
			else {
				turnType = Turntype.TURN_BY;
				turnAmount = Double.valueOf(turn.substring(turn.indexOf("BY") + "BY".length()));
			}
		}
		public TurnTask makeTask(AutoControl cont) {
			return new TurnTask(cont, turnType, turnAmount);
		}
	}
	
	class DriveToken implements Token {
		private double dist;
		public DriveToken(String distance){
			distance = distance.replace(" ","");
			if(Double.parseDouble(distance) >= 0) {
				dist = Double.parseDouble(distance);
			}
		}
		public DriveTask makeTask(AutoControl cont) {
			return new DriveTask(cont, (int) dist);
		}
	}
	
	public AutoTask buildAutoMode(String filename){
		try {
			return parseAuto(tokenize(filename), new SerialTask(cont));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<Token> tokenize(String filename) throws IOException{
		ArrayList<Token> ret = new ArrayList<Token>();
		BufferedReader buff;
		buff = new BufferedReader(new FileReader(filename));
		String line = null;
		while((line = buff.readLine()) != null) {
			if(line.contains("#")) {
				continue;
			}
			else if(line.contains("execute")) {
				String current = line.substring(line.indexOf("execute") + "execute".length());
				ret.add(new ExecuteToken(current));
			}
			else if(line.contains("wait")) {
				String current = line.substring(line.indexOf("wait") + "wait".length());
				ret.add(new WaitToken(current));
			}
			else if(line.contains("drive")) {
				String current = line.substring(line.indexOf("drive") + "drive".length());
				ret.add(new DriveToken(current));
			}
			else if(line.contains("turn")) {
				String current = line.substring(line.indexOf("turn") + "turn".length());
				ret.add(new TurnToken(current));
			}
			else if(line.contains("lift")) {
				String current = line.substring(line.indexOf("lift") + "lift".length());
				ret.add(new LiftToken(current));
			}
			else if(line.contains("intake")) {
				String current = line.substring(line.indexOf("intake") + "intake".length());
				ret.add(new IntakeToken(current));
			}
			
			else if(line.contains("print")) { //If the line is a print token
				String current = line.substring(line.indexOf("print") + "print".length()); //The data that should be printed is everything that comes after the token "print"
				ret.add(new PrintToken(current)); //Adds new Print Token to the ArrayList of all tokens
			}
			else if(line.contains("bundle")) {
				ret.add(new BundleToken());
			}
			else if(line.contains("serial")) {
				ret.add(new SerialToken());
			}
			else if(line.contains("}")) {
				ret.add(new RightBraceToken());
			}
		}
		buff.close();
		return ret;
	}
	
	private AutoTask parseAuto(ArrayList<Token> toks, GroupTask ret) {
		if(toks.size() == 0) {
			return new WaitTask(cont, 0);
		}
		
		while(toks.size() > 0) {
			Token t = toks.remove(0);
			if (t instanceof ExecuteToken) {
				AutoTask otherMode = buildAutoMode(((ExecuteToken) t).scriptName);
				ret.addTask(otherMode);
			}
			else if(t instanceof WaitToken) {
				ret.addTask(((WaitToken) t).makeTask(cont));
			}
			else if(t instanceof DriveToken) {
				ret.addTask(((DriveToken) t).makeTask(cont));
			}
			else if(t instanceof TurnToken) {
				ret.addTask(((TurnToken) t).makeTask(cont));
			}
			else if(t instanceof IntakeToken) {
				ret.addTask(((IntakeToken) t).makeTask(cont));
			}
			else if(t instanceof LiftToken) {
				ret.addTask(((LiftToken) t).makeTask(cont));
			}
			else if(t instanceof BundleToken) {
				ParallelTask p = new ParallelTask(cont);
				parseAuto(toks, p);
				ret.addTask(p);
			}
			else if(t instanceof SerialToken) {
				SerialTask p = new SerialTask(cont);
				parseAuto(toks, p);
				ret.addTask(p);
			}
			else if(t instanceof RightBraceToken) {
				return ret;
			}
			else if(t instanceof PrintToken){
				ret.addTask(((PrintToken) t).makeTask(cont));
			}
		}
		
		return ret;
	}
}