package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Program;

public class Sequence extends ComposedStatement{

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	public Statement[] getStatementArray(){
		return this.array;
	}
	
	private Statement[] array;
	
	public void setStatementArray(Statement[] array){
		this.array = array;
	}

	@Override
	public void execute(Program program) {
		Statement[] statementArray = getStatementArray();
		for (int i = 1; i < statementArray.length;i +=1){
			statementArray[i].execute(program);
			}	
	}
}
