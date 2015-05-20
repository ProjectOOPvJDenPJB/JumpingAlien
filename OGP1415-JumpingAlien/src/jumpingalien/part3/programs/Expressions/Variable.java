package jumpingalien.part3.programs.Expressions;

public abstract class Variable extends BasicExpression<Object>{
	
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
	
	public void setValue(double value){
		this.value = value;
	}
	
	public double value;
	
	public enum Type{
		Boolean,
		Double,
		Expression,
		variable
	}
	
	public void setVariable(String name, Double value,Type type){
		this.setName(name);
		this.setValue(value);
		this.setType(type);		
	}
	
	public double evaluate(){
		return this.value;
	}
	// Wa moet men precies terug krijgen hier bij evaluate?

}
