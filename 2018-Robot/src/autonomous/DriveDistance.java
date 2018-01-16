package autonomous;

import org.usfirst.frc.team2849.robot.Drive;

public class DriveDistance implements AutoTask{
private int distance;
	public DriveDistance(int distance){
		this.distance = distance;
	}

	/** overshoots 3 in */
	public void runTask() {
		double leftPowerConstant = 0.3;
		double rightPowerConstant = 0.3;
		Drive.resetEncoders();
		while (Math.abs(Drive.getLeftEncoder()) < Math.abs(distance) && Math.abs(Drive.getRightEncoder()) < Math.abs(distance)) {
			if (Math.abs(Drive.getLeftEncoder()) > Math.abs(distance)) {
				leftPowerConstant = 0;
			}
			if (Math.abs(Drive.getRightEncoder()) > Math.abs(distance)) {
				rightPowerConstant = 0;
			}
			Drive.drive(leftPowerConstant*Math.signum(distance), rightPowerConstant*Math.signum(distance), false);
		}
		Drive.drive(0, 0, false);
	}	

}
