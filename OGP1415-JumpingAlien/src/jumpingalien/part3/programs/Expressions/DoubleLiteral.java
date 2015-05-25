package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;

public class DoubleLiteral extends BasicExpression<Double>{
	
	/**
	 * Initialize this new double literal with given value
	 * 
	 * @param 	value
	 * 			The value of this new double literal.
	 * @post	The evaluation of this new double literal is equal to
	 * 			the given value
	 * 			| new.evaluate() == value
	 */
	public DoubleLiteral(double value){
		this.value = value;
	}
	
	/**
	 * Initialize this new double literal with value 0.
	 * 
	 * @effect	This new double literal is initialized with 0
	 * 			as its value.
	 * 			| this(0)
	 */
	public DoubleLiteral() {
		// We must explicitly initialize the final instance variable value in
		// this constructor, either in a direct way or in an indirect way.
		this(0);
	}
	
	/**
	 * Return the value of this double literal.
	 */
	private double getValue() {
		return this.value;
	}
	
	/**
	 * Variable registering the value of this double literal.
	 */
	private final double value;

	/**
	 * Return the evaluation of this double literal.
	 * @return	The value of this double literal
	 * 			| result == this.getValue()
	 */
	@Override
	public Double evaluate(Program program) {
		return getValue();
	}
	
	/**
	 * Check whether this double literal is equal to the given
	 * object.
	 *
	 * @return	True if and only if the other object is an effective
	 *         	double literal, whose value is equal to the value
	 *         	of this double literal.
	 *       	| result ==
	 *       	|   (other instanceof DoubleLiteral) &&
	 *       	|   (this.getValue() == ((DoubleLiterl)other).getValue())
	 */
	@Override
	public boolean equals(Object other) {
		return (other instanceof DoubleLiteral)
				&& (getValue() == ((DoubleLiteral) other).getValue());
	}
		
}
