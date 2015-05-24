package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Expression;

public class Print extends ComposedStatement{
	
	public Print(Expression<?> expression) {
		this.expression = expression;
	}

	@Override
	public boolean hasSubStatement(Statement statement) {
		return false;
	}
	
	public Expression<?> getExpression(){
		return  this.expression;
	}
	
	private final Expression<?> expression;

	@Override
	public void execute(Program program) {
		System.out.println(getExpression().evaluate(program));
	}

}
