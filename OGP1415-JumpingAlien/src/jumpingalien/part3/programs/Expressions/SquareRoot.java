package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class SquareRoot extends UnaryExpression<Double,Double> {

	protected SquareRoot(Expression<Double> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Double evaluate() {
		return Math.sqrt(getOperand().evaluate());
	}

}
