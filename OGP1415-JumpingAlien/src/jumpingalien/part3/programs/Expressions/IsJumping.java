package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsJumping extends UnaryExpression<Boolean, LivingCreatures> {

	public IsJumping(Expression<LivingCreatures> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Boolean evaluate() {
		LivingCreatures evaluatedOperand = getOperand().evaluate();
		if (!((evaluatedOperand instanceof Mazub)|| (evaluatedOperand instanceof Shark)))
			throw new IllegalArgumentException("Cannot retrieve jumping state of Livingcreatures who are not Mazub or Sharks.");
		// We define jumping as upwards movement, thus the vertical velocity must be higher than 0.
		// If the creature is moving vertical but has a velocity of lower than 0, the creature is falling, not jumping.
		return evaluatedOperand.getHorizontalVelocity() > 0;
	}

}
