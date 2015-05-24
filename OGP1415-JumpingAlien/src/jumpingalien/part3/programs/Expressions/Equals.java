package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public class Equals extends Comparison<Boolean, Double> {
	
	/**
	 * Initialize this new equals comparison with the given operands.
	 * 
	 * @param 	left
	 * 			The left operand for this new equals comparison.
	 * @param 	right
	 * 			The right operand for this new equals comparison.
	 * @effect	This new addition is initialized as a comparison
	 * 			with the given operands.
	 * 			| super(left,right)
	 */
	public Equals(Expression<Double> left, Expression<Double> right)
			throws IllegalOperandException {
		super(left, right);
	}
	
	/**
	 * Evaluates this equals comparison
	 * 
	 * @return	True if the evaluation of the left operand is 
	 * 			equal to the evaluation of the right operand.
	 * 			| result ==
	 * 			|	getLeftOperand().evaluate() ==
	 * 			|	getRightOperand().evaluate()
	 */
	@Override
	public Boolean evaluate(Program program) {
		return getLeftOperand().evaluate(program) == getRightOperand().evaluate(program);
	}

}
