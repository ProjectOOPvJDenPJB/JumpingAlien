package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Expression;


public abstract class ComposedStatement extends Statement{

	public abstract boolean hasSubStatement(Statement statement);
}
