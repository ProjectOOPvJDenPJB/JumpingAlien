package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class Inverse extends UnaryExpression<Double, Double> {

	public Inverse(Expression<Double> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Double evaluate(Program program) {
		return 1/ (getOperand().evaluate(program));
	}

}
