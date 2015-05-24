package jumpingalien.part3.programs.Statements;

import jumpingalien.model.Program;

public class Skip extends BasicStatement{

	public long getSkipTime(){
		return this.skipTime;
	}
	
	private long skipTime;
	
	public void setSkipTime(long time){
		this.skipTime = time;
	}
	
	@Override
	public void execute(Program program) {
		try {
			wait(this.getSkipTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
