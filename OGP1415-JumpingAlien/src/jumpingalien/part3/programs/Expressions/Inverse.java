package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class Inverse extends UnaryExpression<Double, Double> {

	protected Inverse(Expression<Double> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Double evaluate() {
		return 1/ (getOperand().evaluate());
	}

}
