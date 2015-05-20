package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class Multiplication extends BinaryExpression<Double, Double>{

	protected Multiplication(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException<?> {
		super(left, right);
	}
	
	public double evaluate(){
		return evaluate(this.getLeftOperand()) * evaluate(this.getRightOperand());
	}

}
