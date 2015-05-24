package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Type;

public class Variable extends BasicExpression<Object>{
	
	public Variable(String name,Type type, Object value){
		this.setName(name);
		this.setValue(value);
		this.setType(type);		
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String name;
	
	public String getName(){
		return this.name;
	}
	
	public void setType(Type type){
		this.type = type;
	}
	
	public Type type;
	
	public Type getType(){
		return this.type;
	}
	
	public void setValue(Object value){
		this.value = value;
	}
	
	public Object value;
		
	@Override
	public Object evaluate(Program program) {
		return this.value;
	}

}
