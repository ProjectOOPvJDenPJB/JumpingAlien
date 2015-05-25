package jumpingalien.part3.programs.Statements;

import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.Program;

public class StopDuck extends BasicStatement{

	@Override
	public void execute(Program program) {
		LivingCreatures object = program.getPossessedObject();
		if (object instanceof Mazub){
			((Mazub) object).endDuck();
		}
	}
}
