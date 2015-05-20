package jumpingalien.part3.programs.Expressions;

public class DoubleLiteral extends BasicExpression<Double>{

	public void setValue(double value){
		this.value = value;
	}
	
	public double value;

	@Override
	public Double evaluate() {
		return this.value;
	}
	
}
