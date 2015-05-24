package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Program;

public class Skip extends BasicStatement{

	public double getSkipTime(){
		return this.skipTime;
	}
	
	private double skipTime;
	
	public void setSkipTime(double time){
		this.skipTime = time;
	}
	
	@Override
	public void execute(Program program) {
		//TODO Da klopt ier langs gene kut
		try {
			wait((long) this.getSkipTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
