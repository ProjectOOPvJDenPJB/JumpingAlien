package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GreaterThan extends Comparison<Boolean, Double> {

	protected GreaterThan(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException {
		super(left, right);
	}

	@Override
	public Boolean evaluate(Program program) {
		return getLeftOperand().evaluate(program) > getRightOperand().evaluate(program);
	}

}
