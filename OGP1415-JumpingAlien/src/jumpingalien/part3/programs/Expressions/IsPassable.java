package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsPassable extends UnaryExpression<Boolean, Tile> {

	protected IsPassable(Expression<Tile> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return getOperand().evaluate().getType().getPassable();
	}

}
