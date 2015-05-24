package jumpingalien.part3.programs.Statements;

import java.util.List;

import jumpingalien.model.Program;

public class Sequence extends ComposedStatement{
	
	public Sequence(List<Statement> statements){
		this.array = (Statement[]) statements.toArray();
	}

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	public Statement[] getStatementArray(){
		return this.array;
	}
	
	private final Statement[] array;
	
	@Override
	public void execute(Program program) {
		Statement[] statementArray = getStatementArray();
		for (int i = 1; i < statementArray.length;i +=1){
			statementArray[i].execute(program);
			if (this.getBreakLoop()){
				this.setBreakLoop(false);
				break;
			}
		}	
	}
}
