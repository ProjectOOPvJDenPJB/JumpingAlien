package jumpingalien.part3.programs.Expressions;

public class Direction extends BasicExpression<jumpingalien.model.Direction> {
	
	public void setDirection(jumpingalien.model.Direction direction){
		this.direction = direction;
	}
	
	public jumpingalien.model.Direction direction;
	
	@Override
	public jumpingalien.model.Direction evaluate() {
		return this.direction;
	}

	public jumpingalien.model.Direction getOpposite() {
		return this.direction.oppositeDirection();
	}

}
