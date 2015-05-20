package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class NegateDoubleLiteral extends UnaryExpression<Double> {

	public NegateDoubleLiteral(Expression<Double> operand)
			throws IllegalOperandException<?,?> {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double evaluate() {
		return -getOperand().evaluate();
	}

}
