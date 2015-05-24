package jumpingalien.part3.programs.Statements;

public class Break extends BasicStatement {

	@Override
	public void execute(jumpingalien.model.Program program) {
		this.setBreakLoop(true);
	}

}
