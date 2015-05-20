package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class Conjunction extends BinaryExpression<Boolean, Boolean> {

	protected Conjunction(Expression<Boolean> left, Expression<Boolean> right)
			throws IllegalOperandException<?> {
		super(left, right);
	}
	
	public boolean evaluate(){
		return (evaluate(this.getLeftOperand()) && evaluate(this.getRightOperand()));
	}
}
