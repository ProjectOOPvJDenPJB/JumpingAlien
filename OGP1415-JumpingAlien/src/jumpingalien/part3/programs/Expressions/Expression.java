package jumpingalien.part3.programs.Expressions;

public abstract class Expression<T> {
	//TODO implements ?
	
	/**
	 * Check whether this expression has the given expression as one
	 * of its subexpressions.
	 *
	 * @param  expression
	 *         The expression to be checked.
	 * @return True if the given expression is the same expression as this
	 *         expression.
	 *       | if (expression == this)
	 *       |   then result == true
	 * @return False if the given expression is not effective.
	 *       | if (expression == null)
	 *       |   then result == false
	 * @note   This method illustrates partial specifications of methods. At this
	 *         level, the effect of the method is only defined in 2 cases. All
	 *         other cases must be worked out at the lower levels of the hierarchy.
	 */
	public abstract boolean hasAsSubExpression(Expression<?> expression);
	
	/**
	 *Evaluate this Expression.
	 */
	public abstract T evaluate(jumpingalien.model.Program program);
}
