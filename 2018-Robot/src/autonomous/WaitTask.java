package autonomous;

public class WaitTask implements AutoTask {
	
	private long waitTime = 0;
	public WaitTask(long time) {
		waitTime = time;
	}

	@Override
	public void runTask() {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return "WaitTask: " + waitTime + "\n";
	}
}
