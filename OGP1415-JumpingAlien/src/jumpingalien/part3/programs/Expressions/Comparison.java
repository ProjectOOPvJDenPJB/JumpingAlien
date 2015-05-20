package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Expressions.Exceptions.IllegalOperandException;

public abstract class Comparison<T,P> extends BinaryExpression<T, P>{

	protected Comparison(Expression<T> left, Expression<T> right)
			throws IllegalOperandException<?> {
		super(left, right);
	}

}
