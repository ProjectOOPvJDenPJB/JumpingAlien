package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Direction;
import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class SearchObject extends UnaryExpression<GameObject, Direction> {

	public SearchObject(Expression<Direction> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public GameObject evaluate(Program program) {
		
	}

}
