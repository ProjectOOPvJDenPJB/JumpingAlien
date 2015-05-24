package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;

public class BooleanFalse extends BasicExpression<Boolean>{

	/**
	 * Evaluates this false boolean.
	 * @return	Returns false
	 * 			| result == false
	 */
	@Override
	public Boolean evaluate(Program program) {
		return false;
	}

}
