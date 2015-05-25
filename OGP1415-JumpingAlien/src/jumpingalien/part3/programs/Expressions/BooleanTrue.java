package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;

public class BooleanTrue extends BasicExpression<Boolean> {
	
	/**
	 * Evaluates this true boolean.
	 * @return	Returns true
	 * 			| result == true
	 */
	@Override
	public Boolean evaluate(Program program) {
		return true;
	}

}
