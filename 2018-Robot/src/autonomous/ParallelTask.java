package autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team2849.autonomous.AutoTask;

public class ParallelTask extends GroupTask {
	public ParallelTask() {}
	
	@Override
	public void runTask() {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(AutoTask t:tasks) {
			Thread taskThread = new Thread(t);
			taskThread.start();
			threads.add(taskThread);
		}
		for(Thread t:threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return "--ParallelTask:\n" + super.toString();
	}
}
