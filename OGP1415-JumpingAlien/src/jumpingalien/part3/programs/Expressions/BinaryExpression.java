package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;
import be.kuleuven.cs.som.annotate.*;


public abstract class BinaryExpression<T,P> extends ComposedExpression<T,P> {

	/**
	 * Initialize this new binary expression with given operands.
	 *
	 * @param  left
	 *         The left operand for this new binary expression.
	 * @param  right
	 *         The right operand for this new binary expression.
	 * @post   The left operand of this new binary expression is the
	 *         same as the given left operand.
	 *       | new.getLeftOperand() == left
	 * @post   The right operand of this new binary expression is the
	 *         same as the given right operand.
	 *       | new.getRightOperand() == right
	 * @throws IllegalOperandException
	 *         This new binary expression cannot have the given left
	 *         operand or the given right operand as its operand.
	 *      |     (! canHaveAsOperand(left))
	 *      |  || (! canHaveAsOperand(right))
	 */
	@Model
	protected BinaryExpression(Expression<P> left, Expression<P> right)
			throws IllegalOperandException<?,?> {
		if (!canHaveAsOperand(left))
			throw new IllegalOperandException<T,P>(this, left);
		if (!canHaveAsOperand(right))
			throw new IllegalOperandException<T,P>(this, right);
		setOperandAt(1, left);
		setOperandAt(2, right);
	}
	
	/**
	 * Set the operand for this binary expression at the given
	 * index to the given operand.
	 */
	@Override
	@Raw
	protected void setOperandAt(int index, Expression<P> operand) {
		if (index == 1)
			this.leftOperand = operand;
		else
			this.rightOperand = operand;
	}

	/**
	 * Return the number of operands involved in this binary expression.
	 *
	 * @return A binary expression always involves two operands.
	 *       | result == 2
	 */
	@Override
	@Basic
	public final int getNbOperands() {
		return 2;
	}
	
	/**
	 * Return the operand of this binary expression at the given index.
	 * 
	 * @return If the given index is 1, the left operand of this
	 *         binary expression; otherwise the right operand of
	 *         this binary expression.
	 *       | if (index == 1)
	 *       |   then result == getLeftOperand()
	 *       |   else result == getRightOperand()
	 */
	@Override
	@Raw
	public final Expression<P> getOperandAt(int index)
			throws IndexOutOfBoundsException {
		if ((index != 1) && (index != 2))
			throw new IndexOutOfBoundsException();
		if (index == 1)
			return getLeftOperand();
		else
			return getRightOperand();
	}
	

	/**
	 * Return the left operand of this binary expression.
	 */
	@Basic
	public Expression<P> getLeftOperand() {
		return leftOperand;
	}

	/**
	 * Variable referencing the left operand of this
	 * binary expression.
	 *
	 * @note   This variable is not qualified final, such that operands
	 *         can be changed in cloning unary expressions.
	 */
	private Expression<P> leftOperand;

	/**
	 * Return the right operand of this binary expression.
	 */
	@Basic
	public Expression<P> getRightOperand() {
		return rightOperand;
	}

	/**
	 * Variable referencing the right operand of this
	 * binary expression.
	 *
	 * @note   This variable is not qualified final, such that operands
	 *         can be changed in cloning unary expressions.
	 */
	private Expression<P> rightOperand;
}
