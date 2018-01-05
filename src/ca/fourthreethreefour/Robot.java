package ca.fourthreethreefour;

import ca.fourthreethreefour.commands.ReverseSolenoid;
import edu.first.module.Module;
import edu.first.module.actuators.DualActionSolenoid;
import edu.first.module.actuators.DualActionSolenoid.Direction;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.joysticks.XboxController;
import edu.first.module.joysticks.BindingJoystick.DualAxisBind;
import edu.first.module.subsystems.Subsystem;
import edu.first.robot.IterativeRobotAdapter;

public class Robot extends IterativeRobotAdapter {

	DualActionSolenoidModule armSolenoid = new DualActionSolenoidModule(0, 1);
	
	XboxController controller = new XboxController(0);
	
	private final Subsystem ALL_MODULES = new Subsystem(new Module[] { drive, armSolenoid, controller });
	
	public Robot() {
		super("Steve");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		ALL_MODULES.init();
		
		controller.addAxisBind(new DualAxisBind(controller.getLeftDistanceFromMiddle(), controller.getRightX()) {
            @Override
			public void doBind(double speed, double turn) {
				if (turn == 0 && speed == 0) {
					drivetrain.stopMotor();
				} else {
					drivetrain.arcadeDrive(speed, turn);
				}				
			}
		});
		
		controller.addWhenPressed(XboxController.RIGHT_BUMPER, new ReverseSolenoid(armSolenoid));
	}
	
	@Override
	public void initTeleoperated() {
		ALL_MODULES.enable();
		if (armSolenoid.get() == Direction.OFF) {
			armSolenoid.set(DualActionSolenoid.Direction.LEFT);
		}
	}
	
	@Override
	public void periodicTeleoperated() {
		controller.doBinds();
	}
	
	@Override
	public void endTeleoperated() {
		ALL_MODULES.disable();
	}
	
	

}

	