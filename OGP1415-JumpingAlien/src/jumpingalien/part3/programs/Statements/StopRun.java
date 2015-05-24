package jumpingalien.part3.programs.Statements;

import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.Expressions.DirectionExpression;
import jumpingalien.part3.programs.Expressions.Expression;

public class StopRun extends BasicStatement{
	
	public StopRun(Expression<Direction> direction) {
		this.direction = new DirectionExpression(direction.evaluate(new Program(null,null)));
	}
	
	public DirectionExpression getDirection() {
		return this.direction;
	}
	
	private final DirectionExpression direction;

	@Override
	public void execute(Program program) {
		LivingCreatures object = program.getPossessedObject();
		if (object.getDirection() == getDirection().evaluateForCreature())
			object.endMove();
	}
	
}
