package jumpingalien.part3.programs.Statements;

import java.util.List;

import jumpingalien.part3.programs.Program;

public class Sequence extends ComposedStatement{
	
	public Sequence(List<Statement> statements){
		this.array = statements;
	}

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	public List<Statement> getStatementArray(){
		return this.array;
	}
	
	private final List<Statement> array;
	
	@Override
	public void execute(Program program) {
		List<Statement> statementArray = getStatementArray();
		for (int i = 1; i < statementArray.size();i +=1){
			statementArray.get(i).execute(program);
			if (this.getBreakLoop()){
				this.setBreakLoop(false);
				break;
			}
		}	
	}
}
