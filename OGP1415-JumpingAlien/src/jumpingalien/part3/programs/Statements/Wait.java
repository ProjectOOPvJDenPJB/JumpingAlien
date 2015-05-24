package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expressions.DoubleLiteral;
import jumpingalien.part3.programs.Expressions.Expression;
import jumpingalien.util.Util;

public class Wait extends BasicStatement {
	
	public Wait(Expression<Double> duration) {
		setDuration(duration, new Program(null, null));
	}
	
	public double getDuration(Program program) {
		return this.duration.evaluate(program);
	}
	
	private Expression<Double> duration;
	
	public void setDuration(Expression<Double> duration, Program program) {
		if (! isValidDuration(duration.evaluate(program)))
			throw new IllegalArgumentException("The given duration is not a valid duration for this wait statement.");
		this.duration = duration; 
	}
	
	public void decreaseDuration(Expression<Double> duration, Program program) {
		if (! isValidDuration(getDuration(program) - duration.evaluate(program)))
			duration = new DoubleLiteral(0);
		else
			duration = new DoubleLiteral(getDuration(program) - duration.evaluate(program));
		this.duration = duration;
	}
	
	public static boolean isValidDuration(double duration) {
		if (Util.fuzzyLessThanOrEqualTo(duration, 0))
			return false;
		return true;
	}

	@Override
	public void execute(Program program) {
		//Does nothing
	}

}
