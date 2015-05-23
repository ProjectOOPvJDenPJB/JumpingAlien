package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class Conjunction extends BinaryExpression<Boolean, Boolean> {

	public Conjunction(Expression<Boolean> left, Expression<Boolean> right)
			throws IllegalOperandException<?,?> {
		super(left, right);
	}

	@Override
	public Boolean evaluate() {
		return getLeftOperand().evaluate() && getRightOperand().evaluate();
	}
	
}
