package autonomous;

public class IntakeTask implements AutoTask {
	
	enum IntakeType{IN, OUT, RUN, STOP, UNTIL };
	
	private IntakeType intake;
	public IntakeTask(IntakeType intakeVal) {
		intake = intakeVal;
	}

	@Override
	public void runTask() {
		//TODO write this code
	}
	
	public String toString() {
		return "IntakeTask: " + intake.name() + "\n";
	}
}
