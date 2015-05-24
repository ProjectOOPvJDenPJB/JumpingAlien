package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class SquareRoot extends UnaryExpression<Double,Double> {

	public SquareRoot(Expression<Double> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Double evaluate(Program program) {
		return Math.sqrt(getOperand().evaluate(program));
	}

}
