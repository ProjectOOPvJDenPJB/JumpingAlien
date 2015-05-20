package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class Addition extends BinaryExpression<Double,Double>{

	protected Addition(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException<?> {
		super(left, right);
	}
	
	public double evaluate(){
		return evaluate(this.getLeftOperand()) + evaluate(this.getRightOperand());
	}

}
