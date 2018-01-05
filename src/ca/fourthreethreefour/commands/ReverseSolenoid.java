package ca.fourthreethreefour.commands;

import edu.first.command.Command;
import edu.first.module.actuators.DualActionSolenoid;

public class ReverseSolenoid implements Command {
	
	private final DualActionSolenoid solenoid;
	
	public ReverseSolenoid(DualActionSolenoid solenoid) {
		this.solenoid = solenoid;
	}
	
	@Override
	public void run() {
		solenoid.reverse();
	}

}
