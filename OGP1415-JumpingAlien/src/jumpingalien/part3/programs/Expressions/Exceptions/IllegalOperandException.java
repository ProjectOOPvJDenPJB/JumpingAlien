package jumpingalien.part3.programs.Expressions.Exceptions;

import be.kuleuven.cs.som.annotate.*;
import jumpingalien.part3.programs.Expressions.Expression;
 /**
 * A class for signaling illegal operands.
 * 
 */
public class IllegalOperandException<T> extends RuntimeException {

	/**
	 * Initialize this new illegal operand exception with given target expression
	 * and given operand.
	 *
	 * @param   targetExpression
	 *          The target expression for this new illegal operand exception.
	 * @param   operand
	 *          The operand for this new illegal operand exception.
	 * @post    The target expression for this new illegal operand exception
	 *          is the same as the given target expression.
	 *          | new.getTargetExpression() == targetExpression
	 * @post    The operand for this new illegal operand exception
	 *          is the same as the given operand.
	 *          | new.getOperand() == operands
	 */
	public IllegalOperandException(Expression<T> targetExpression, Expression<T> operand) {
		this.targetExpression = targetExpression;
		this.operand = operand;
	}

	/**
	 * Return the target expression of this illegal operand exception.
	 */
	@Basic @Immutable @Raw
	public Expression<T> getTargetExpression() {
		return this.targetExpression;
	}

	/**
	 * Variable registering the target expression of this illegal operand exception.
	 */
	private final Expression<T> targetExpression;

	/**
	 * Return the operand of this illegal operand exception.
	 */
	@Basic @Immutable @Raw
	public Expression<T> getOperand() {
		return this.operand;
	}

	/**
	 * Variable registering the operand of this illegal operand exception.
	 */
	private final Expression<T> operand;

	/**
	 * The Java API strongly recommends to explicitly define a version
	 * number for classes that implement the interface Serializable.
	 * At this stage, that aspect is of no concern to us. 
	 */
	private static final long serialVersionUID = 2003001L;

}