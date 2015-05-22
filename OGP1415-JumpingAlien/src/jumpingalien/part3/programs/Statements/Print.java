package jumpingalien.part3.programs.Statements;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Expression;

public class Print extends ComposedStatement{

	@Override
	public boolean hasSubStatement(Statement statement) {
		return false;
	}
	
	public Expression<?> getExpression(){
		return  this.expression;
	}
	
	private Expression<?> expression;
	
	public void setExpression(Expression<?> expression) {
		this.expression = expression;
	}

	@Override
	public void execute(Program program) {
		System.out.println(getExpression().evaluate());
	}

}
