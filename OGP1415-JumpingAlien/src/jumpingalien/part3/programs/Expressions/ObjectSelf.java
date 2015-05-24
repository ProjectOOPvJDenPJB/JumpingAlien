package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;

public class ObjectSelf extends BasicExpression<Object>{
	
		@Override
		public Object evaluate(Program program){
			return this;
		}
}
