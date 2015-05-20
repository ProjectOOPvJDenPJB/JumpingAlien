package jumpingalien.part3.programs.Expressions;


import be.kuleuven.cs.som.annotate.*;


public abstract class ComposedExpression<T> extends Expression<T>{
	

	/**
	 * Return the number of operands involved in this composed
	 * expression.
	 */
	@Basic
	public abstract int getNbOperands();
	
	/**
	 * Return the operand of this composed expression at the given index.
	 *
	 * @param  index
	 *         The index of the requested operand.
	 * @throws IndexOutOfBoundsException
	 *         The given index is not positive or exceeds the
	 *         number of operands for this composed expression.
	 *       | (index < 1) || (index > getNbOperands())
	 */
	@Basic
	public abstract Expression<T> getOperandAt(int index)
			throws IndexOutOfBoundsException;
	
	/**
	 * Check whether this composed expression can have the given
	 * expression as one of its operands.
	 *
	 * @param  expression
	 *         The expression to check.
	 * @return True if and only if the given expression is effective,
	 *         and if that expression does not have this composed
	 *         expression as a subexpression.
	 *       | result ==
	 *       |   ( (expression != null)
	 *       |  && (! expression.hasAsSubExpression(this)) )
	 */
	public boolean canHaveAsOperand(Expression<T> expression) {
		return (expression != null) && (!expression.hasAsSubExpression(this));
	}
	
	/**
	 * Set the operand for this composed expression at the given
	 * index to the given operand.
	 * 
	 * @param  index
	 *         The index at which the operand must be registered.
	 * @param  operand
	 *         The operand to be registered.
	 * @pre    The given index is positive and does not exceed the
	 *         number of operands for this composed expression.
	 *       | (index >= 1) && (index <= getNbOperands())
	 * @pre    This expression can have the given operand as one
	 *         of its operands.
	 *       | canHaveAsOperand(operand)
	 * @post   The operand at the given index of this composed
	 *         expression is the same as the given operand.
	 *       | new.getOperandAt(index) == operand
	 */
	protected abstract void setOperandAt(int index, Expression<T> operand);
	
	/**
	 * Check whether this composed expression has the given expression
	 * as one of its subexpressions.
	 *
	 * @return True if and only if the given expression is the same
	 *         expression as this composed expression, or if the given
	 *         expression is a subexpression of one of the operands
	 *         of this composed expression.
	 *       | result ==
	 *       |     (expression == this)
	 *       |  || ( for some I in 1..getNbOperands():
	 *       |         getOperandAt(I).hasAsSubExpression(expression) )
	 */
	@Override
	public boolean hasAsSubExpression(Expression<T> expression) {
		if (expression == this)
			return true;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (getOperandAt(pos).hasAsSubExpression(expression))
				return true;
		return false;
	}
}
