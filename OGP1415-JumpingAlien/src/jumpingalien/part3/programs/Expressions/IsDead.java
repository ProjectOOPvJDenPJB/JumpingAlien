package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsDead extends UnaryExpression<Boolean, LivingCreatures> {

	public IsDead(Expression<LivingCreatures> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Boolean evaluate(Program program) {
		return getOperand().evaluate(program).isDead();
	}

}
