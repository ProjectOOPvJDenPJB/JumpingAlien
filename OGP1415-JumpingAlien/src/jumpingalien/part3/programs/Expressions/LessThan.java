package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class LessThan extends Comparison<Boolean,Double> {
	
	public LessThan(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException<?,?> {
		super(left, right);
	}

	@Override
	public Boolean evaluate() {
		return getLeftOperand().evaluate() < getRightOperand().evaluate();
	}
		
}
