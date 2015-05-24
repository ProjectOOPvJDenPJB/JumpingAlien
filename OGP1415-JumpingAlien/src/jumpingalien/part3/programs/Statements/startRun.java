package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Direction;
import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.Expressions.Expression;

public class StartRun extends BasicStatement{
	
	public StartRun(Expression<Direction> direction) {
		this.direction = direction;
	}
	
	public Expression<Direction> getDirection() {
		return this.direction;
	}
	
	private final Expression<Direction> direction;

	@Override
	public void execute(jumpingalien.model.Program program) {
		LivingCreatures object = program.getPossessedObject();
		object.startMove(getDirection().evaluate(program));
	}

}