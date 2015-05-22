package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class NegateBoolean extends UnaryExpression<Boolean,Boolean> {

	protected NegateBoolean(Expression<Boolean> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return !getOperand().evaluate();
	}
	
	

}