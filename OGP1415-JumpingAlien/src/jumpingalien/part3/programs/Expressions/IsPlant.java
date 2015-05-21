package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Plant;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsPlant extends UnaryExpression<Boolean, GameObject> {

	protected IsPlant(Expression<GameObject> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return (getOperand().evaluate() instanceof Plant);
	}

}
