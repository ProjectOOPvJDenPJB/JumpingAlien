package jumpingalien.part3.programs.Statements;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Expression;


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
		if (this.getExpression().evaluate(program)){
			execute(program);
		}	
	}
}
