package jumpingalien.part3.programs.Expressions;

public abstract class DoubleLiteral extends BasicExpression<Double>{

	public void setValue(double value){
		this.value = value;
	}
	
	public double value;
	
	public double evaluate(){
		return this.value;
	}
}
