package autonomous;

import org.usfirst.frc.team2849.robot.Drive;

public class DriveDistance implements AutoTask{
private int distance;
	public DriveDistance(int distance){
		this.distance = distance;
	}

	public void runTask() {
		double leftPowerConstant = 0.3;
		double rightPowerConstant = 0.3;
		Drive.resetEncoders();
		while ((Drive.getLeftEncoder() < distance) && (Drive.getRightEncoder() < distance)) {
			//TODO fix condition for negative distances
			if (Drive.getLeftEncoder() > distance) {
				leftPowerConstant = 0;
			}
			if (Drive.getRightEncoder() > distance) {
				rightPowerConstant = 0;
			}
			Drive.drive(leftPowerConstant*Math.signum(distance), rightPowerConstant*Math.signum(distance), false);
		}
		Drive.drive(0, 0, false);
	}	

}
