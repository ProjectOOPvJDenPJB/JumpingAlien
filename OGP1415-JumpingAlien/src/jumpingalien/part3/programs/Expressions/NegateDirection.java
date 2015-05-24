package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Direction;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class NegateDirection extends UnaryExpression<Direction,Direction> {

	protected NegateDirection(Expression<Direction> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Direction evaluate(Program program) {
		return getOperand().evaluate(program).oppositeDirection();
	}
}
