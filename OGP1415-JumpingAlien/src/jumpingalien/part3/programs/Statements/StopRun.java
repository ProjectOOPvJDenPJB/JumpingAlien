package jumpingalien.part3.programs.Statements;

import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.Expressions.DirectionExpression;
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
		DirectionExpression direction = new DirectionExpression(getDirection().evaluate(program));
		if (object.getDirection() == direction.evaluateForCreature())
			object.endMove();
	}
	
}
