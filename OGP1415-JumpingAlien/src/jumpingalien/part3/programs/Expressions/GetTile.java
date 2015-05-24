package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.model.World;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GetTile extends BinaryExpression<Tile, Double> {

	protected GetTile(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException<?, ?> {
		super(left, right);
	}

	@Override
	public Tile evaluate(Program program) {
		int pixelX = getLeftOperand().evaluate(program).intValue();
		int pixelY = getRightOperand().evaluate(program).intValue();
		World world = program.getPossessedObject().getWorld();
		int[] tilePos = world.getTilePosition(pixelX, pixelY);
		return new Tile(tilePos[0],tilePos[1],world);
	}
	
}
