package jumpingalien.part3.programs.Statements;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.DoubleLiteral;

public class Skip extends BasicStatement{

	@Override
	public void execute(Program program) {
		Wait skip = new Wait(new DoubleLiteral(getExecutionTime()));
		skip.execute(program);
	}

}
