package jumpingalien.part3.programs.Statements;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Expression;

public class IfElse extends ComposedStatement{
	
	public IfElse(Expression<Boolean> condition,Statement actionsIfTrue, Statement actionsIfFalse){
		this.ifExpression = condition;
		this.ifStatement = actionsIfTrue;
		this.elseStatement = actionsIfFalse;
	}

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	public Expression<Boolean> getIfExpression(){
		return this.ifExpression;
	}
	
	private final Expression<Boolean> ifExpression;
	
	public Statement getElseStatement(){
		return this.elseStatement;
	}
	
	private final Statement elseStatement;
	
	public Statement getIfStatement(){
		return this.ifStatement;
	}

	private final Statement ifStatement;
	
	@Override
	public void execute(Program program) {
		if (getIfExpression().evaluate(program)){
			getIfStatement().execute(program);
		}else{
			try {
			getElseStatement().execute(program);
			} catch (NullPointerException e) {
				
			}
		}
		
	}

}
