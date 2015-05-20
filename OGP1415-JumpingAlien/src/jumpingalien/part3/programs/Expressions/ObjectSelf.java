package jumpingalien.part3.programs.Expressions;

public abstract class ObjectSelf extends BasicExpression<Object>{
	
		public Object evaluate(){
			return this;
		}
}
