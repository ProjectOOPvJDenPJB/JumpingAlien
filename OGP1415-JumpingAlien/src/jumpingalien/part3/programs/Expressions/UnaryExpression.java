package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

public abstract class UnaryExpression<T,P> extends ComposedExpression<T,P> {
	
	/**
	 * Initialize this new unary expression with given operand.
	 *
	 * @param  operand
	 *         The operand for this new unary expression.
	 * @post   The operand for this new unary expression is the
	 *         same as the given operand.
	 *       | new.getOperand() == operand
	 * @throws IllegalOperandException
	 *         This new unary expression cannot have the given
	 *         operand as its operand.
	 *       | ! canHaveAsOperand(operand)
	 */
	@Model
	protected UnaryExpression(Expression<P> operand)
			throws IllegalOperandException<?,?> {
		if (!canHaveAsOperand(operand))
			throw new IllegalOperandException<T,P>(this, operand);
		setOperandAt(1, operand);
	}

	/**
	 * Return the number of operands involved in this unary expression.
	 *
	 * @return A unary expression always involves a single operand.
	 *       | result == 1
	 */
	@Override
	@Basic
	public int getNbOperands() {
		return 1;
	}

	/**
	 * Return the operand of this unary expression at the given index.
	 * 
	 * @return The one and only operand of this unary expression.
	 *       | result == getOperand()
	 */
	@Override
	public final Expression<P> getOperandAt(int index)
			throws IndexOutOfBoundsException {
		if (index != 1)
			throw new IndexOutOfBoundsException();
		return getOperand();
	}

	/**
	 * Return the operand of this unary expression.
	 */
	@Basic
	public Expression<P> getOperand() {
		return operand;
	}

	/**
	 * Set the operand for this unary expression at the given
	 * index to the given operand.
	 */
	@Override
	protected void setOperandAt(int index, Expression<P> operand) {
		this.operand = operand;
	}

	/**
	 * Variable referencing the operand of this unary expression.
	 * 
	 * @note    This variable is not qualified final, such that operands
	 *          can be changed in cloning unary expressions.
	 */
	private Expression<P> operand;

}
