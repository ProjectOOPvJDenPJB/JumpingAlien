package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Direction;
import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Expression;

public class StopRun extends BasicStatement{
	
	public StopRun(Expression<Direction> direction) {
		this.direction = direction;
	}
	
	public Expression<Direction> getDirection() {
		return this.direction;
	}
	
	private final Expression<Direction> direction;

	@Override
	public void execute(Program program) {
		LivingCreatures object = program.getPossessedObject();
		if (object.getDirection() == getDirection().evaluate(program))
			object.endMove();
	}
	
}
