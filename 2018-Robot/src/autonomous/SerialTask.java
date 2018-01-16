package autonomous;

import org.usfirst.frc.team2849.autonomous.AutoTask;

public class SerialTask extends GroupTask {
	public SerialTask() {}
	
	@Override
	public void runTask() {
		for(AutoTask t:tasks) {
			Thread taskThread = new Thread(t);
			taskThread.start();
			try {
				taskThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return "--SerialTask:\n" + super.toString();
	}
}
