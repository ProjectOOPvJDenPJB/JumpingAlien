package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;
import be.kuleuven.cs.som.annotate.*;


public abstract class BinaryExpression<T> extends ComposedExpression<T> {

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
	protected BinaryExpression(Expression<T> left, Expression<T> right)
			throws IllegalOperandException<?> {
		if (!canHaveAsOperand(left))
			throw new IllegalOperandException<T>(this, left);
		if (!canHaveAsOperand(right))
			throw new IllegalOperandException<T>(this, right);
		setOperandAt(1, left);
		setOperandAt(2, right);
	}
	
	/**
	 * Set the operand for this binary expression at the given
	 * index to the given operand.
	 */
	@Override
	@Raw
	protected void setOperandAt(int index, Expression<T> operand) {
		if (index == 1)
			this.leftOperand = operand;
		else
			this.rightOperand = operand;
	}
	
	/**
	 * Return the left operand of this binary expression.
	 */
	@Basic
	public Expression<T> getLeftOperand() {
		return leftOperand;
	}

	/**
	 * Variable referencing the left operand of this
	 * binary expression.
	 *
	 * @note   This variable is not qualified final, such that operands
	 *         can be changed in cloning unary expressions.
	 */
	private Expression<T> leftOperand;

	/**
	 * Return the right operand of this binary expression.
	 */
	@Basic
	public Expression<T> getRightOperand() {
		return rightOperand;
	}

	/**
	 * Variable referencing the right operand of this
	 * binary expression.
	 *
	 * @note   This variable is not qualified final, such that operands
	 *         can be changed in cloning unary expressions.
	 */
	private Expression<T> rightOperand;
}
