package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;

public class ObjectSelf extends BasicExpression<Object>{
	
		@Override
		public Object evaluate(Program program){
			return program.getPossessedObject();
		}
}
