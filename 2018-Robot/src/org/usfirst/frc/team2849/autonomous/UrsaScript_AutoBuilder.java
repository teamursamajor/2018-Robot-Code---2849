package org.usfirst.frc.team2849.autonomous;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.usfirst.frc.team2849.autonomous.TurnTask.Turntype;

import org.usfirst.frc.team2849.autonomous.IntakeTask.IntakeType;

/**
 * TODO Add comments
 * @author Evan + Sheldon wrote this on 1/16/18
 * Parser/scripting language for auto functions
 * Syntax:
 * wait <amount in seconds, type double>
 * execute <file directory>
 * lift <amount in inches>||<BOTTOM||VAULT||SWITCH||SCALE)>
 * drive <amount in inches>
 * turn <TO||BY> <amount in degrees> 
 * intake <IN||OUT||RUN||STOP>
 * bundle {...} - runs parallel tasks
 * serial {...} - runs tasks sequentially
 */
public class UrsaScript_AutoBuilder {
	interface Token{}
	enum LiftType{BOTTOM, VAULT, SWITCH, SCALE };

	public static void main(String[] args) {
		System.out.println(new UrsaScript_AutoBuilder().buildAutoMode("C:/Users/Ursa Major/tmp/autotest1.auto").toString());
	}
	
	
	
	class ExecuteToken implements Token {
		private String scriptName;
		public ExecuteToken(String scriptName){
			this.scriptName = scriptName.trim();
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
			} else {
				intake = IntakeType.UNTIL;
			}
		}
		public IntakeTask makeTask() {
			return new IntakeTask(intake);
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
		public LiftTask makeTask() {
			return new LiftTask(lift);
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
		public WaitTask makeTask() {
			return new WaitTask((long) (wait*1000.0d));
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
		public TurnTask makeTask() {
			return new TurnTask(turnType, turnAmount);
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
		public DriveDistance makeTask() {
			return new DriveDistance((int) dist);
		}
	}
	
	public AutoTask buildAutoMode(String filename){
		try {
			return parseAuto(tokenize(filename), new SerialTask());
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
			if(line.contains("execute")) {
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
			return new WaitTask(0);
		}
		
		while(toks.size() > 0) {
			Token t = toks.remove(0);
			if (t instanceof ExecuteToken) {
				AutoTask otherMode = buildAutoMode(((ExecuteToken) t).scriptName);
				ret.addTask(otherMode);
			}
			else if(t instanceof WaitToken) {
				ret.addTask(((WaitToken) t).makeTask());
			}
			else if(t instanceof DriveToken) {
				ret.addTask(((DriveToken) t).makeTask());
			}
			else if(t instanceof TurnToken) {
				ret.addTask(((TurnToken) t).makeTask());
			}
			else if(t instanceof IntakeToken) {
				ret.addTask(((IntakeToken) t).makeTask());
			}
			else if(t instanceof LiftToken) {
				ret.addTask(((LiftToken) t).makeTask());
			}
			else if(t instanceof BundleToken) {
				ParallelTask p = new ParallelTask();
				parseAuto(toks, p);
				ret.addTask(p);
			}
			else if(t instanceof SerialToken) {
				SerialTask p = new SerialTask();
				parseAuto(toks, p);
				ret.addTask(p);
			}
			else if(t instanceof RightBraceToken) {
				return ret;
			}
		}
		
		return ret;
	}
}
