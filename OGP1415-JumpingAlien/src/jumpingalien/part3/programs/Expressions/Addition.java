package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class Addition extends BinaryExpression<Double,Double>{
	
	/**
	 * Initialize this new Addition with the given operands.
	 * 
	 * @param 	left
	 * 			The left operand for this new addition.
	 * @param 	right
	 * 			The right operand for this new addition.
	 * @effect	This new addition is initialized as a binary expression
	 * 			with the given operands.
	 * 			| super(left,right)
	 */
	public Addition(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException {
		super(left, right);
	}
	
	
	/**
	 * Return the evaluation of this addition.
	 * 
	 * @return	The sum of the evaluation of the operands of this addition.
	 * 			| result ==
	 * 			|	getLeftOperand().evaluate() +
	 * 			|	getRightOperand().evaluate()
	 */
	@Override
	public Double evaluate(Program program) {
		return getLeftOperand().evaluate(program) + getRightOperand().evaluate(program);
	}

}
