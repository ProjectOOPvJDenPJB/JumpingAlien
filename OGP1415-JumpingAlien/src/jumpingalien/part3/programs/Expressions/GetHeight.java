package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GetHeight extends UnaryExpression<Integer, GameObject> {

	public GetHeight(Expression<GameObject> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Integer evaluate(Program program) {
		return getOperand().evaluate(program).getHeight();
	}

}
