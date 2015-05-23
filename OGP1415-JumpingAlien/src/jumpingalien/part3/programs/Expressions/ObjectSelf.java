package jumpingalien.part3.programs.Expressions;

public class ObjectSelf extends BasicExpression<Object>{
	
		@Override
		public Object evaluate(){
			return this;
		}
}
