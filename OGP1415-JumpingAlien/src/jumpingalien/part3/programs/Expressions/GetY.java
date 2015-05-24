package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class GetY extends UnaryExpression<Double,GameObject> {

	public GetY(Expression<GameObject> operand)
			throws IllegalOperandException<?, ?> {
		super(operand);
	}

	@Override
	public Double evaluate(Program program) {
		return getOperand().evaluate(program).getYPosition();
	}

}
