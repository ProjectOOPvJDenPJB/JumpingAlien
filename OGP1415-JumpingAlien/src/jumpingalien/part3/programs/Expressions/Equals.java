package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public  class Equals<T, P> extends Comparison<Boolean, Double> {

	protected Equals(Expression<T> left, Expression<T> right)
			throws IllegalOperandException<?,?> {
		super(left, right);
	}

	@Override
	public Boolean evaluate() {
		return getLeftOperand().evaluate() == getRightOperand().evaluate();
	}

}
