package jumpingalien.part3.programs.Statements;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Expressions.Expression;

public class ForEach extends ComposedStatement{

	@Override
	public boolean hasSubStatement(Statement statement) {
		return true;
	}
	
	public Object[] getObjectArray(){
		return this.objects;
	}
	
	private Object[] objects;
	
	public void setObjectArray(Object[] objects){
		this.objects = objects;
	}

	@Override
	public void execute(Program program) {
		Object[] array = objects;
		for (int i = 1; i < objects.length;i +=1){
				objects[i].execute(program);
				//find a way 
			}
	}

}
