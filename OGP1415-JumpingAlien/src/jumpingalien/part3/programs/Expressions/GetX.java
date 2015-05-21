package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GetX extends UnaryExpression<Double, Object> {

	protected GetX(Expression<Object> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Double evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
