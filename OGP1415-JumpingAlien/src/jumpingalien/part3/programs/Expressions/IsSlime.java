package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Slime;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class IsSlime extends UnaryExpression<Boolean, GameObject> {

	public IsSlime(Expression<GameObject> operand)
			throws IllegalOperandException {
		super(operand);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (getOperand().evaluate(program) instanceof Slime);
	}

}
