package jumpingalien.part3.programs.Statements;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Expression;

public class IfElse extends ComposedStatement{

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	public Expression<Boolean> getIfExpression(){
		return this.ifExpression;
	}
	
	private Expression<Boolean> ifExpression;
	
	public void setIfExpression(Expression<Boolean> expression){
		this.ifExpression = expression;
	}
	
	public Statement getElseStatement(){
		return this.elseStatement;
	}
	
	private Statement elseStatement;
	
	public void setElseStatement(Statement statement){
		this.elseStatement = statement;
	}
	
	public Statement getIfStatement(){
		return this.ifStatement;
	}

	private Statement ifStatement;
	
	public void setIfStatement(Statement statement){
		this.ifStatement = statement;
	}
	
	@Override
	public void execute(Program program) {
		if (getIfExpression().evaluate()){
			getIfStatement().execute(program);
		}else{
			getElseStatement().execute(program);
		}
		
	}

}
