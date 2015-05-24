package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.Expression;
import jumpingalien.part3.programs.Type;

public class Assignment extends BasicStatement {
	
	public Assignment(String varName, Type varType, Expression<?> expression) {
		this.varName = varName;
		this.varType = varType;
		this.expression = expression;
	}
	
	public String getVarName() {
		return varName;
	}
	
	private final String varName;
	
	public Type getVarType() {
		return varType;
	}
	
	private final Type varType;
	
	public Expression<?> getExpression() {
		return expression;
	}
	
	private final Expression<?> expression;

	@Override
	public void execute(Program program) {
		program.setVariableValue(getVarName(), getExpression().evaluate(program));
	}
	
	@Override
	public String toString() {
		return getVarName() + ":" + getExpression().evaluate(new Program(null, null)) + "(Type:" + getVarType() + ")";
	}

}
