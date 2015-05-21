package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Tile;
import jumpingalien.model.TileType;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsTerrain extends UnaryExpression<Boolean, Tile> {

	protected IsTerrain(Expression<Tile> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return (getOperand().evaluate().getType() == TileType.GROUND);
	}

}
