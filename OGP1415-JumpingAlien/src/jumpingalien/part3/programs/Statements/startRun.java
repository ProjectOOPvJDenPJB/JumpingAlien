package jumpingalien.part3.programs.Statements;

import jumpingalien.model.LivingCreatures;

public class startRun extends BasicStatement{

	@Override
	public void execute(jumpingalien.part3.programs.Program program) {
		LivingCreatures object = program.getPossessedObject();
		jumpingalien.model.Direction direction = object.getDirection();
		object.startMove(direction);
		
	}

}
