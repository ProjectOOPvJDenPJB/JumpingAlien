package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GetHeight extends UnaryExpression<Integer, GameObject> {

	protected GetHeight(Expression<GameObject> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Integer evaluate() {
		return getOperand().evaluate().getHeight();
	}

}
