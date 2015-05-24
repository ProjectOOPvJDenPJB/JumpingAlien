package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class Comparison<T,P> extends BinaryExpression<T,P>{

	/**
	 * Initializes this Comparison with given operands.
	 * 
	 * @param 	left
	 * 			The left operand for this comparison.
	 * @param 	right
	 * 			The right operand for this comparison.
	 * @effect	This new comparison is initialized as a binary expression
	 * 			with the given operands.
	 * 			| super(left,right)
	 */
	protected Comparison(Expression<P> left, Expression<P> right)
			throws IllegalOperandException {
		super(left, right);
	}

}
