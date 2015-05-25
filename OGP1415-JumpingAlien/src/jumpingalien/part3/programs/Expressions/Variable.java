package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Program;
import jumpingalien.part3.programs.Type;

public class Variable extends BasicExpression<Object>{
	
	public Variable(String name,Type type){
		this.setName(name);
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
		
	@Override
	public Object evaluate(Program program) {
		return program.getVariableValues().get(getName());
	}

}
