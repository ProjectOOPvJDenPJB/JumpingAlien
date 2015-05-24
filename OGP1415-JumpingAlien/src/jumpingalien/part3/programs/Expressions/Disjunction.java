package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public  class Disjunction extends BinaryExpression<Boolean, Boolean> {

	/**
	 * Initializes this Disjunction with given operands.
	 * 
	 * @param 	left
	 * 			The left operand for this disjunction.
	 * @param 	right
	 * 			The right operand for this disjunction.
	 * @effect	This new disjunction is initialized as a binary expression
	 * 			with the given operands.
	 * 			| super(left,right)
	 */
	public Disjunction(Expression<Boolean> left, Expression<Boolean> right)
			throws IllegalOperandException<?,?> {
		super(left, right);
	}

	
	/**
	 * Evaluates this disjunction.
	 * 
	 * @return	The disjunction of the evaluation of the operands.
	 * 			| result ==
	 * 			|	getLeftOperand().evaluate() ||
	 * 			|	getRightOperand().evaluate()
	 */
	@Override
	public Boolean evaluate(Program program) {
		return  getLeftOperand().evaluate(program) || getRightOperand().evaluate(program);
	}
	
}