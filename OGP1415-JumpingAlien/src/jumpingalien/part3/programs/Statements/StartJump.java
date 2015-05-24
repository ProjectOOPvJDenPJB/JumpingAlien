package jumpingalien.part3.programs.Statements;

import jumpingalien.model.LivingCreatures;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;

public class StartJump extends BasicStatement{
	
	@Override
	public void execute(Program program) {
		LivingCreatures object = program.getPossessedObject();
		if (object instanceof Mazub){
			((Mazub) object).startJump();
		}else if(object instanceof Shark){
			((Shark) object).startJump();
		}		
	}

}
