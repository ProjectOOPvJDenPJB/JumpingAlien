package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.model.TileType;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsTerrain extends UnaryExpression<Boolean, Tile> {

	public IsTerrain(Expression<Tile> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (getOperand().evaluate(program).getType() == TileType.GROUND);
	}

}
