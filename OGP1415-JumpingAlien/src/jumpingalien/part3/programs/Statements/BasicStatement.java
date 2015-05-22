package jumpingalien.part3.programs.Statements;

public abstract class BasicStatement extends Statement {
	
	@Override
	public boolean hasSubStatement(Statement statement) {
		return statement == this;
	}

}
