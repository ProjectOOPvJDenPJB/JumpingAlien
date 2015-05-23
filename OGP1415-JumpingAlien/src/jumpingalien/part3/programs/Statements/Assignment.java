package jumpingalien.part3.programs.Statements;

import jumpingalien.part3.programs.Expressions.Expression;
import jumpingalien.part3.programs.Type;

public class Assignment extends BasicStatement {
	
	public Assignment(String varName, Type varType, Expression<?> expression) {
		
	}
	
	public String getVarName() {
		return varName;
	}
	
	private String varName;
	
	public Type getVarType() {
		return varType;
	}
	
	private Type varType;
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	private Expression<?> expression;

	@Override
	public void execute(jumpingalien.part3.programs.Program program) {
		program.setVariableValue(getVarName(), getExpression().evaluate());
	}
	
	@Override
	public String toString() {
		return getVarName() + ":" + getExpression().evaluate() + "(Type:" + getVarType() + ")";
	}

}
