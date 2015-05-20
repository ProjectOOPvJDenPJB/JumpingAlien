package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class LessThan extends Comparison<Double,Double> {
	
	protected LessThan(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException<?> {
		super(left, right);
	}
	
	public boolean evaluate(){
		return evaluate(this.getLeftOperand()) < evaluate(this.getRightOperand());
	}
	
}
