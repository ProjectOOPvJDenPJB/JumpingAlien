package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class Addition extends BinaryExpression<Double,Double>{

	public Addition(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException<?,?> {
		super(left, right);
	}

	@Override
	public Double evaluate() {
		return getLeftOperand().evaluate() + getRightOperand().evaluate();
	}

}
