package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsDead extends UnaryExpression<Boolean, LivingCreatures> {

	protected IsDead(Expression<LivingCreatures> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return getOperand().evaluate().isDead();
	}

}
