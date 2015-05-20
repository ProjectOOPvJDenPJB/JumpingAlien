package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class Equals<T, P> extends Comparison<T, P> {

	protected Equals(Expression<T> left, Expression<T> right)
			throws IllegalOperandException<?> {
		super(left, right);
	}
	
	public boolean evaluate(){
		return evaluate(this.getLeftOperand()) == evaluate(this.getRightOperand());

	}

}
