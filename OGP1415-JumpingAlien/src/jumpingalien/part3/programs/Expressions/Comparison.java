package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class Comparison<T,P> extends BinaryExpression<T,P>{

	protected Comparison(Expression<P> left, Expression<P> right)
			throws IllegalOperandException<?,?> {
		super(left, right);
	}

}
