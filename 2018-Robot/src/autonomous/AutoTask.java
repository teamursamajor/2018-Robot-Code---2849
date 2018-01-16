package autonomous;

public interface AutoTask extends Runnable{
	
	public default void run(){
		runTask();
	}
	
	public void runTask();
}
