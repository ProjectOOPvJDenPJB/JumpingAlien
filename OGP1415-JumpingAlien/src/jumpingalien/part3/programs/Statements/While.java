package jumpingalien.part3.programs.Statements;
import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Expression;


public class While extends ComposedStatement{
	
	public While(Expression<Boolean> condition,Statement actions){
		this.loopCondition = condition;
		this.actions = actions;
	}
	
	
	public Expression<Boolean> getLoopCondition(){
		return this.loopCondition;
	}
	
	private final Expression<Boolean> loopCondition;
	
	public Statement getActions(){
		return this.actions;
	}
	
	private final Statement actions;
	
	

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	@Override
	public void execute(Program program) {
		if (this.getLoopCondition().evaluate(program) && !getBreakLoop()){
			getActions().execute(program);
		}	
	}
}
