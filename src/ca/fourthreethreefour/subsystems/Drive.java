package ca.fourthreethreefour.subsystems;

import edu.first.identifiers.Function;
import edu.first.identifiers.InversedSpeedController;
import edu.first.module.Module;
import edu.first.module.actuators.Drivetrain;
import edu.first.module.actuators.VictorModule;
import edu.first.module.actuators.VictorModuleGroup;
import edu.first.module.subsystems.Subsystem;

public interface Drive {
	
	VictorModuleGroup left = new VictorModuleGroup(new VictorModule[] { new VictorModule(0),
			new VictorModule(1), new VictorModule(2) });
	
	VictorModuleGroup right = new VictorModuleGroup(new VictorModule[] { new VictorModule(4),
			new VictorModule(5), new VictorModule(6) });
	
	Drivetrain drivetrain = new Drivetrain( new InversedSpeedController (left), right);
	
	Function speedFunction = new Function() {
		@Override
		public double F(double in) {
			return in > 0 ? in * in : -(in * in);
		}
	};

	public Subsystem drive = new Subsystem(new Module[] { drivetrain, left, right });
}
