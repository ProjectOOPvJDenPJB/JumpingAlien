package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsShark extends UnaryExpression<Boolean, GameObject> {

	public IsShark(Expression<GameObject> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return (getOperand().evaluate() instanceof Shark);
	}

}
