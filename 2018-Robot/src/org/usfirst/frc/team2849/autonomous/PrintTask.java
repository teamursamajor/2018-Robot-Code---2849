package org.usfirst.frc.team2849.autonomous;

/* PrintTask
 *     Prints the arguments sent to it
 *     @param str  The string that you want to print
 *     @toString  The string that will be printed, prefixed with "Print: "
 */

public class PrintTask implements AutoTask {
	private String str; //The string that you want to print
	
	//Instantiate the class by setting variable str to the argument passed to the class
	public PrintTask(String str) {
		this.str = str;
	}
	
	//Prints the string to System.out
	public void runTask() {
		System.out.println(str);
	}
	
	//Returns the string that would be printed
	public String toString() {
		return "Print: " + str + "\n";
	}
}
