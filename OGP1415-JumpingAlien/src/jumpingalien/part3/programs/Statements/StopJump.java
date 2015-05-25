
package jumpingalien.part3.programs.Statements;

import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.Program;

public class StopJump extends BasicStatement{

	@Override
	public void execute(Program program) {
		LivingCreatures object = program.getPossessedObject();
		object.endJump();
	}

}
