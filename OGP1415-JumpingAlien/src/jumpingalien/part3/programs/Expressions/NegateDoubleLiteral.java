package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class NegateDoubleLiteral extends UnaryExpression<Number,Number> {

	public NegateDoubleLiteral(Expression<Number> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Number evaluate(Program program) {
		return -getOperand().evaluate(program).doubleValue();
	}

}
