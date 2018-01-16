package org.usfirst.frc.team2849.autonomous;

import java.util.List;

public class UrsaScript_AutoBuilder {
	interface Token {
	}

	enum IntakeType {
		IN, OUT, RUN, STOP, UNTIL
	};

	enum LiftType {
		IN, OUT, RUN, STOP, UNTIL
	};

	class ExecuteToken implements Token {
		private String scriptName;

		public ExecuteToken(String scriptName) {
			this.scriptName = scriptName;
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
	}

	class LiftToken implements Token {
		private LiftType lift;
		public LiftToken(String liftType){
			liftType = liftType.replace(" ","");
			if(liftType.equalsIgnoreCase("IN")){
				lift = LiftType.IN;
			} else if(liftType.equalsIgnoreCase("OUT")){
				lift = LiftType.OUT;
			} else if(liftType.equalsIgnoreCase("RUN")){
				lift = LiftType.RUN;
			} else if(liftType.equalsIgnoreCase("STOP")){
				lift = LiftType.STOP;
			} else{
				lift = LiftType.UNTIL;
			}
		}
	}
	
	
	public static AutoTask buildAutoMode(String filename){
		return null;
	}
	
	private static List<Token> tokenize(String filename){
		return null;
	}
}
