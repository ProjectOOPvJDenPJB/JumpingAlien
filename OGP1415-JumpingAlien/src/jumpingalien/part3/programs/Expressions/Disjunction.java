package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class Disjunction extends BinaryExpression<Boolean, Boolean> {

	protected Disjunction(Expression<Boolean> left, Expression<Boolean> right)
			throws IllegalOperandException<?> {
		super(left, right);
	}
	
	public boolean evaluate(){
		return (evaluate(this.getLeftOperand()) || evaluate(this.getRightOperand()));
	}
}