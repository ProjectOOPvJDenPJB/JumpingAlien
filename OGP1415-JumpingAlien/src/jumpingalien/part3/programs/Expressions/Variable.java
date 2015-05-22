package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.Type;

public class Variable extends BasicExpression<Object>{
	
	public Variable(String name, Object value,Type type){
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
		
	public Object evaluate(){
		return this.value;
	}
	// Wa moet men precies terug krijgen hier bij evaluate?
	// Een variabele is geen double, maar kan ook gwn een object zijn ;), in orde gemaakt

}
