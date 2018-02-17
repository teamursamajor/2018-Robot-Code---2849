package org.usfirst.frc.team2849.autonomous;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {
	private ArrayList<SendableChooser<String>> autoList = new ArrayList<SendableChooser<String>>();
	private SendableChooser<Character> startingPosition = new SendableChooser<Character>();

	public AutoSelector() {
		startingPosition.addDefault("Select starting position...", ' ');
		startingPosition.addObject("Left", 'L');
		startingPosition.addObject("Middle", 'M');
		startingPosition.addObject("Right", 'R');
		for (int i = 0; i < 4; i++)
			autoList.add(new SendableChooser<String>());
		sendAutoModes(findAutoModes());
	}

	public File[] findAutoFiles() {
		File[] autoFiles;
		File autoDirectory = new File("/home/lvuser/automodes/");
		if (autoDirectory.isDirectory()) {
			autoFiles = autoDirectory.listFiles((File dir, String name) -> {
				// Regex expression which only checks for files matching our
				// naming syntax
				return name.matches("[LMR0]_[LR0]{2}_.*\\.auto");
			});
		} else {
			autoFiles = null;
		}
		return autoFiles;
	}

	public String[] findAutoModes() {
		String[] names;
		File[] autoFiles = findAutoFiles();
		if (autoFiles != null) {
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
		autoList.get(0).addDefault("Select LL auto", "LL");
		autoList.get(1).addDefault("Select LR auto", "LR");
		autoList.get(2).addDefault("Select RL auto", "RL");
		autoList.get(3).addDefault("Select RR auto", "RR");

		for(SendableChooser<String> chooser: autoList){
			for (String name : names) {
				chooser.addObject(name, name);
			}
			SmartDashboard.putData("Auto Mode for " + chooser.getSelected(), chooser);
		}
		SmartDashboard.putData("Robot Start Position", startingPosition);
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
}
