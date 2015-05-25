package jumpingalien.part2.facade;

import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Statements.BasicStatement;

public class StartDuck extends BasicStatement{

	@Override
	public void execute(Program program) {
		LivingCreatures object = program.getPossessedObject();
		if (object instanceof Mazub){
			((Mazub) object).startDuck();
		}
	}
}
