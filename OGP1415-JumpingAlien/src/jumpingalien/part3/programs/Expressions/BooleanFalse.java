package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;

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
