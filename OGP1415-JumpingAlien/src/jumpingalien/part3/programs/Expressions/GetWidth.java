package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GetWidth extends UnaryExpression<Integer, GameObject> {

	public GetWidth(Expression<GameObject> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Integer evaluate(Program program) {
		return getOperand().evaluate(program).getWidth();
	}

}
