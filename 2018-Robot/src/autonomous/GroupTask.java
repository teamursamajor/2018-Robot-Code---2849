package autonomous;

import java.util.ArrayList;

public abstract class GroupTask implements AutoTask {
	protected ArrayList<AutoTask> tasks = new ArrayList<AutoTask>();
	public GroupTask() {}
	
	public void addTask(AutoTask t) {
		tasks.add(t);
	}
	
	public ArrayList<AutoTask> getTaskGroup() {
		return tasks;
	}
	
	public String toString() {
		String ret = new String();
		for(AutoTask t:tasks) {
			ret += t.toString();
		}
		return ret + "--\n";
	}
}
