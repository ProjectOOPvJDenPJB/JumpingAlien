package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsDucking extends UnaryExpression<Boolean, Mazub> {

	public IsDucking(Expression<Mazub> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Boolean evaluate(Program program) {
		return getOperand().evaluate(program).getDucking();
	}

}
