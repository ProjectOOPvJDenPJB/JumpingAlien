import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Expression;
import jumpingalien.part3.programs.Statements.ComposedStatement;
import jumpingalien.part3.programs.Statements.Statement;


public class While extends ComposedStatement{

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	public Expression<Boolean> getExpression(){
		return this.expression;
	}

	private Expression<Boolean> expression;
	
	public void setExpression(Expression<Boolean> expression){
		this.expression = expression;
	}
	
	
	@Override
	public void execute(Program program) {
		if (this.getExpression().evaluate()){
			execute(program);
		}	
	}
}
