package jumpingalien.part3.programs.Expressions;


public abstract class BasicExpression<T> extends Expression<T>{
	/**
	 * Check whether this basic expression has the given expression as
	 * one of its subexpressions.
	 *
	 * @return  True if and only if the given expression is the same
	 *          expression as this basic expression.
	 *        | result == (expression == this)
	 */
	@Override
	public final boolean hasAsSubExpression(Expression<T> expression) {
		return expression == this;
	}
}
