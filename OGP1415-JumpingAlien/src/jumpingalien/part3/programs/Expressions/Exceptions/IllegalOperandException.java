package jumpingalien.part3.programs.Expressions.Exceptions;

import be.kuleuven.cs.som.annotate.*;
import jumpingalien.part3.programs.Expressions.BinaryExpression;
import jumpingalien.part3.programs.Expressions.Expression;
 /**
 * A class for signaling illegal operands.
 * 
 */
public class IllegalOperandException<T,P> extends RuntimeException {
	
	/**
	 * Initialize this new illegal operand exception with given target expression
	 * and given operand.
	 *
	 * @param   targetExpression
	 *          The target expression for this new illegal operand exception.
	 * @param   left
	 *          The operand for this new illegal operand exception.
	 * @post    The target expression for this new illegal operand exception
	 *          is the same as the given target expression.
	 *          | new.getTargetExpression() == targetExpression
	 * @post    The operand for this new illegal operand exception
	 *          is the same as the given operand.
	 *          | new.getOperand() == operands
	 */
	public IllegalOperandException(Expression<T> targetExpression,
			Expression<T> operand) {
		this.targetExpression = targetExpression;
		this.operand = operand;
	}

	/**
	 * Initialize this new illegal operand exception with given target expression
	 * and given operand.
	 *
	 * @param   targetExpression
	 *          The target expression for this new illegal operand exception.
	 * @param   left
	 *          The operand for this new illegal operand exception.
	 * @post    The target expression for this new illegal operand exception
	 *          is the same as the given target expression.
	 *          | new.getTargetExpression() == targetExpression
	 * @post    The operand for this new illegal operand exception
	 *          is the same as the given operand.
	 *          | new.getOperand() == operands
	 */
	public IllegalOperandException(BinaryExpression<T,P> targetExpression,
			Expression<P> operand) {
		this.targetExpression = targetExpression;
		this.operand = operand;
	}
	
	public IllegalOperandException(Expression<T> targetExpression,
			Expression<T> operand, String message) {
		this(targetExpression, operand);
		System.out.println(message);
	}

	/**
	 * Return the target expression of this illegal operand exception.
	 */
	@Basic @Immutable @Raw
	public Expression<?> getTargetExpression() {
		return this.targetExpression;
	}

	/**
	 * Variable registering the target expression of this illegal operand exception.
	 */
	private final Expression<?> targetExpression;

	/**
	 * Return the operand of this illegal operand exception.
	 */
	@Basic @Immutable @Raw
	public Expression<?> getOperand() {
		return this.operand;
	}

	/**
	 * Variable registering the operand of this illegal operand exception.
	 */
	private final Expression<?> operand;

	/**
	 * The Java API strongly recommends to explicitly define a version
	 * number for classes that implement the interface Serializable.
	 * At this stage, that aspect is of no concern to us. 
	 */
	private static final long serialVersionUID = 2003001L;

}