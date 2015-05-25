package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsPassable extends UnaryExpression<Boolean, Tile> {

	public IsPassable(Expression<Tile> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Boolean evaluate(Program program) {
		return getOperand().evaluate(program).getType().getPassable();
	}

}
