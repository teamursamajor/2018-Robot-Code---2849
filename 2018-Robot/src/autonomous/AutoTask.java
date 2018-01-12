package autonomous;

public interface AutoTask extends Runnable{

	public default void run(){
		runTask();
		//TODO Log-in call
	}
	
	public void runTask();
}
