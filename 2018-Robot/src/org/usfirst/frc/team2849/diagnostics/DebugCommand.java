package org.usfirst.frc.team2849.diagnostics;

import org.usfirst.frc.team2849.diagnostics.Logger.LogLevel;

import edu.wpi.first.wpilibj.command.Command;

public class DebugCommand extends Command {

	public DebugCommand() {
		super("Debug Command");
	}
	
	@Override
	protected boolean isFinished() {
		System.out.println("is finished");
		return false;
	}
	
	@Override
	protected void initialize() {
		System.out.println("!!!!!!!");
		Logger.setLevel(LogLevel.DEBUG);
		super.initialize();
	}
	
	@Override
	public void cancel() {
		System.out.println("cancel");
		super.cancel();
	}
	
	@Override
	protected void execute() {
		System.out.println("execute");
	}
	
	@Override
	protected void end() {
		System.out.println("end");
		super.end();
	}
	
	@Override
	public void start() {
		System.out.println("start");
		super.start();
	}
	
	@Override
	public boolean isInterruptible() {
		System.out.println("is interruptible");
		return false;
	}
}
