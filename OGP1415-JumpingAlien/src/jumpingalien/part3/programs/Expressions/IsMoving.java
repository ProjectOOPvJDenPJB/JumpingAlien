package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsMoving extends UnaryExpression<Boolean, LivingCreatures> {

	public IsMoving(Expression<LivingCreatures> operand, Expression<Direction> direction)
			throws IllegalOperandException {
		super(operand);
		this.direction = direction;
	}
	
	public Expression<Direction> getDirection() {
		return this.direction;
	}
	
	private final Expression<Direction> direction;

	@Override
	public Boolean evaluate(Program program) {
		if (((getDirection().evaluate(program) == Direction.RIGHT) &&
				(getOperand().evaluate(program).getDirection() == jumpingalien.model.Direction.RIGHT))
		|| ((getDirection().evaluate(program) == Direction.LEFT) &&
				(getOperand().evaluate(program).getDirection() == jumpingalien.model.Direction.LEFT))) {
			if (getOperand().evaluate(program).getHorizontalVelocity() > 0)
				return true;
		}
		if ((getDirection().evaluate(program) == Direction.UP) &&
				(getOperand().evaluate(program).getVerticalVelocity() > 0))
			return true;
		if ((getDirection().evaluate(program) == Direction.DOWN) &&
				(getOperand().evaluate(program).getVerticalVelocity() < 0))
			return true;
		return false;
	}

}
