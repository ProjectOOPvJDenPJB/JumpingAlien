package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsDucking extends UnaryExpression<Boolean, Mazub> {

	protected IsDucking(Expression<Mazub> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return getOperand().evaluate().getDucking();
	}

}
