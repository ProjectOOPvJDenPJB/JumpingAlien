package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GetY extends UnaryExpression<Double,GameObject> {

	protected GetY(Expression<GameObject> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Double evaluate() {
		return getOperand().evaluate().getYPosition();
	}

}
