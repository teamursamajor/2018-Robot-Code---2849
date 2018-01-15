package autonomous;
import org.usfirst.frc.team2849.robot.*;
public interface AutoTask extends Runnable{
	
	public default void run(){
		runTask();
	}
	
	public void runTask();
}
