package jumpingalien.part3.programs.Expressions;

public abstract class Direction extends BasicExpression<jumpingalien.model.Direction> {
	
	public void setDirection(jumpingalien.model.Direction direction){
		this.direction = direction;
	}
	
	public jumpingalien.model.Direction direction;
	
	public jumpingalien.model.Direction evaluate(){
		return this.direction;
	}
}
