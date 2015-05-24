package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.model.TileType;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsWater extends UnaryExpression<Boolean, Tile> {

	public IsWater(Expression<Tile> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (getOperand().evaluate(program).getType() == TileType.WATER);
	}

}
