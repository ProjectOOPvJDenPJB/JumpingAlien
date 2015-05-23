package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.LivingCreatures;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GetHP extends UnaryExpression<Integer, LivingCreatures> {

	public GetHP(Expression<LivingCreatures> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Integer evaluate() {
		return getOperand().evaluate().getHP();
	}

}
