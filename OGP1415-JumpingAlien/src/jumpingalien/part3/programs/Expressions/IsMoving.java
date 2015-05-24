package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsMoving extends UnaryExpression<Boolean, LivingCreatures> {

	public IsMoving(Expression<LivingCreatures> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		return (getOperand().evaluate().getMoving()) || (getOperand().evaluate().getMovingVertical());
	}

}
