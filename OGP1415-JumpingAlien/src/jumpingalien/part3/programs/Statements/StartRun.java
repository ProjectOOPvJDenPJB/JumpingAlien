package jumpingalien.part3.programs.Statements;

import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.Expressions.DirectionExpression;
import jumpingalien.part3.programs.Expressions.Expression;

public class StartRun extends BasicStatement{
	
	public StartRun(Expression<Direction> direction) {
		this.direction = new DirectionExpression(direction.evaluate(new Program(null,null)));
	}
	
	public DirectionExpression getDirection() {
		return this.direction;
	}
	
	private final DirectionExpression direction;

	@Override
	public void execute(Program program) {
		LivingCreatures object = program.getPossessedObject();
		object.startMove(getDirection().evaluateForCreature());
	}
}
