package jumpingalien.part3.programs.Statements;

public abstract class Statement {

	/**
	 * Check whether this expression has the given expression as one
	 * of its subexpressions.
	 *
	 * @param  expression
	 *         The expression to be checked.
	 * @return True if the given expression is the same expression as this
	 *         expression.
	 *       | if (expression == this)
	 *       |   then result == true
	 * @return False if the given expression is not effective.
	 *       | if (expression == null)
	 *       |   then result == false
	 * @note   This method illustrates partial specifications of methods. At this
	 *         level, the effect of the method is only defined in 2 cases. All
	 *         other cases must be worked out at the lower levels of the hierarchy.
	 */
	public abstract boolean hasSubStatement(Statement statement);
	
	private final double executionTime = 0.001;
	
	public double getExecutionTime(){
		return this.executionTime;
	}
	
	protected boolean getBreakLoop(){
		return breakLoop;
	}
	
	private boolean breakLoop = false;
	
	protected void setBreakLoop(Boolean flag){
		this.breakLoop = flag;
	}
	
	/**
	 *Evaluate this program
	 */
	public abstract void execute(jumpingalien.model.Program program);
}
