package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class Multiplication extends BinaryExpression<Double, Double>{

	public Multiplication(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException<?,?> {
		super(left, right);
	}

	@Override
	public Double evaluate() {
		return getLeftOperand().evaluate() * getRightOperand().evaluate();
	}
	


}
