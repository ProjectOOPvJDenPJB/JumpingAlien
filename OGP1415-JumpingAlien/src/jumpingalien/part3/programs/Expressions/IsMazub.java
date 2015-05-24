package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsMazub extends UnaryExpression<Boolean, GameObject> {

	public IsMazub(Expression<GameObject> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return (getOperand().evaluate() instanceof Mazub);
	}

}
