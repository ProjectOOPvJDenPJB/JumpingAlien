package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class Conjunction extends BinaryExpression<Boolean, Boolean> {
	
	/**
	 * Initializes this Conjunction with given operands.
	 * 
	 * @param 	left
	 * 			The left operand for this conjunction.
	 * @param 	right
	 * 			The right operand for this conjunction.
	 * @effect	This new conjunction is initialized as a binary expression
	 * 			with the given operands.
	 * 			| super(left,right)
	 */
	public Conjunction(Expression<Boolean> left, Expression<Boolean> right)
			throws IllegalOperandException {
		super(left, right);
	}
	
	
	/**
	 * Evaluates this conjunction.
	 * 
	 * @return	The conjunction of the evaluation of the operands.
	 * 			| result ==
	 * 			|	getLeftOperand().evaluate() &&
	 * 			|	getRightOperand().evaluate()
	 */
	@Override
	public Boolean evaluate(Program program) {
		return getLeftOperand().evaluate(program) && getRightOperand().evaluate(program);
	}
	
}
