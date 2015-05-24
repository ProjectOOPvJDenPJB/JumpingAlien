package jumpingalien.part3.programs.Statements;

import jumpingalien.model.LivingCreatures;

public class StartRun extends BasicStatement{

	@Override
	public void execute(jumpingalien.model.Program program) {
		LivingCreatures object = program.getPossessedObject();
		jumpingalien.model.Direction direction = object.getDirection();
		object.startMove(direction);
	}

}