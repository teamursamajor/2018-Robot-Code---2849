package org.usfirst.frc.team2849.autonomous;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team2849.autonomous.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {

	private ArrayList<SendableChooser<String>> autoList = new ArrayList<SendableChooser<String>>();
	private SendableChooser<Character> startingPosition = new SendableChooser<Character>();
	
	public AutoSelector(int numChoosers) {
		startingPosition.addDefault("Select starting position...", ' ');
		startingPosition.addObject("Left", 'L');
		startingPosition.addObject("Middle", 'M');
		startingPosition.addObject("Right", 'R');
		for (int i = 0; i < numChoosers; i++) autoList.add(new SendableChooser<String>());
		sendAutoModes(findAutoModes());
	}

	public String[] findAutoModes() {
		File autoDirectory = new File("/home/lvuser/AutoModes/");
		String[] names;
		if (autoDirectory.isDirectory()) {
			File[] autoFiles = autoDirectory.listFiles((File dir, String name) -> {
				return name.matches(".*auto");
			});
			names = new String[autoFiles.length];
			for (int i = 0; i < autoFiles.length; i++) {
				names[i] = autoFiles[i].getName().substring(5, autoFiles[i].getName().length() - 5);
			}
			Arrays.sort(names);
		} else {
			names = new String[] { "None" };
		}
		return names;
	}

	public void sendAutoModes(String[] names) {
		for (int i = 0; i < autoList.size(); i++) {
			autoList.get(i).addDefault("Select auto #" + (i + 1), "");
			for (String name : names) {
				autoList.get(i).addObject(name, name);
			}
			SmartDashboard.putData("Auto preference #" + (i + 1), autoList.get(i));
		}
	}
	
	public void updateModes() {
		for (int i = 0; i < autoList.size(); i++) {
			SmartDashboard.putData("Auto preference #" + (i + 1), autoList.get(i));
		}
	}
	
	public String[] getAutoPrefs() {
		String[] prefs = new String[autoList.size()];
		int i = 0;
		for (SendableChooser<String> chooser : autoList) {
			prefs[i++] = chooser.getSelected();
		}
		return prefs;
	}
	
	public String getAutoPref(int num) {
		return autoList.get(num).getSelected();
	}
	
	public char getStartingPosition() {
		return startingPosition.getSelected();
	}

	public String pickAutoMode(char robotPosition, String[] autoPrefs){
		String sides = DriverStation.getInstance().getGameSpecificMessage();
		char switchSide = sides.charAt(0);
		char scaleSide = sides.charAt(1);
		String compatibleAuto = "0_00_drive.auto";
	    
//		for(String autoPreference : autoPrefs){
//			TODO fix this
//			if(buildAutoMode(robotPosition + "_" + switchSide + scaleSide + "_" + autoPreference + ".auto") != null){
//				compatibleAuto = robotPosition + "_" + switchSide + scaleSide + "_" + autoPreference + ".auto";
//				break;
//			}
//		}
		return compatibleAuto;
	}
}
