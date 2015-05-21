package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Tile;
import jumpingalien.model.TileType;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsWater extends UnaryExpression<Boolean, Tile> {

	protected IsWater(Expression<Tile> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return (getOperand().evaluate().getType() == TileType.WATER);
	}

}
