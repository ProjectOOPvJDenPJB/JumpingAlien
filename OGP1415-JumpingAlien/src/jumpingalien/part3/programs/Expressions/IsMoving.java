package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsMoving extends UnaryExpression<Boolean, LivingCreatures> {

	public IsMoving(Expression<LivingCreatures> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (getOperand().evaluate(program).getMoving()) || (getOperand().evaluate(program).getMovingVertical());
	}

}
