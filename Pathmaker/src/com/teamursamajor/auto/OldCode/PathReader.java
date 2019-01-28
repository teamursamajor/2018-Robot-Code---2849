package com.teamursamajor.auto;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PathReader {
	
//TODO - GET RID OF GOSH DARN CHEEZE

	private RobotPath leftPath;
	private RobotPath rightPath;
	
	//TODO - change CHEEZY - WHAT THE F* IS IT
	public PathReader(String filename, boolean iHateCheese) {
		leftPath = new RobotPath();
		rightPath = new RobotPath();
		if (iHateCheese) {
			parseCheezeAndEndMySuffering(filename);
		} else  {
			parse(filename);
		}
	}
	
	private void parse(String filename) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "\\" + filename), Charset.defaultCharset());
			String state = "name";
			for (String line : lines) {
				if (line.equals("") || line.charAt(0) == '#') continue;
				switch (state) {
				case "name":
					leftPath.setName(line + "Left");
					rightPath.setName(line + "Right");
					state = "search";
					break;
				case "search":
					if (line.toLowerCase().equals("left")) state = "left";
					else if (line.toLowerCase().equals("right")) state = "right";
					break;
				case "left":
					if (line.toLowerCase().equals("end")) {
						state = "search";
						break;
					}
					leftPath.add(parsePoint(line));
					break;
				case "right":
					if (line.toLowerCase().equals("end")) {
						state = "search";
						break;
					}
					rightPath.add(parsePoint(line));
				case "default":
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//TODO I NO LONGER LIKE CHARLIE AS MUCH BUT HE STILL HAS NICE HAIR
	private void parseCheezeAndEndMySuffering(String filename) {
		int numPoints = 0;
		try {
			List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "\\" + filename), Charset.defaultCharset());
			String state = "name";
			for (String line : lines) {
				if (line.equals("") || line.charAt(0) == '#') continue;
				switch (state) {
				case "name":
					leftPath.setName(line + "Left");
					rightPath.setName(line + "Right");
					state = "num";
					break;
				case "num":
					numPoints = Integer.parseInt(line);
					state = "left";
					break;
				case "left":
					leftPath.add(parsePointCheeze(line));
					if (leftPath.numPoints() == numPoints) state = "right";
					break;
				case "right":
					rightPath.add(parsePoint(line));
					if (rightPath.numPoints() == numPoints) state = "";
				case "default":
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private PointonPath parsePoint(String line) {
		String[] splitPoint = line.trim().split("\\s+");
		return new PointonPath(Double.parseDouble(splitPoint[1].trim()), Double.parseDouble(splitPoint[2].trim()), Double.parseDouble(splitPoint[5].trim()), Double.parseDouble(splitPoint[6].trim()), Double.parseDouble(splitPoint[0].trim()), Double.parseDouble(splitPoint[3].trim()), Double.parseDouble(splitPoint[4].trim()));
	}
	//TODO END MY LIFE NOW
	private PointonPath parsePointCheeze(String line) {
		String[] splitPoint = line.trim().split("\\s+");
		return new PointonPath(Double.parseDouble(splitPoint[0]), Double.parseDouble(splitPoint[4]), -1, -1);
	}
	
	public RobotPath getLeftPath() {
		return leftPath;
	}
	
	public RobotPath getRightPath() {
		return rightPath;
	}
	
	public RobotPath[] getPaths() {
		return new RobotPath[] {leftPath, rightPath};
	}

}
