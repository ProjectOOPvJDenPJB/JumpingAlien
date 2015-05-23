package jumpingalien.part3.programs.Expressions;

import jumpingalien.part3.programs.IProgramFactory.Direction;

public class DirectionExpression extends BasicExpression<jumpingalien.part3.programs.IProgramFactory.Direction> {
	
	public DirectionExpression(jumpingalien.part3.programs.IProgramFactory.Direction direction){
		this.setDirection(direction);
	}
	
	public void setDirection(jumpingalien.part3.programs.IProgramFactory.Direction direction){
		this.direction = direction;
	}
	
	private jumpingalien.part3.programs.IProgramFactory.Direction direction;
	
	public jumpingalien.part3.programs.IProgramFactory.Direction getDirection(){
		return this.direction;
	}
	@Override
	public jumpingalien.part3.programs.IProgramFactory.Direction evaluate() {
		return this.direction;
	}

	public jumpingalien.part3.programs.IProgramFactory.Direction getOpposite() {
		return this.oppositeDirection();
	}

	public jumpingalien.part3.programs.IProgramFactory.Direction oppositeDirection(){
		jumpingalien.part3.programs.IProgramFactory.Direction direction = this.getDirection();
		if (direction == Direction.UP){
			return Direction.DOWN;
		}else if (direction == Direction.DOWN){
			return Direction.UP;
		}else if (direction == Direction.RIGHT){
			return Direction.LEFT;
		}else{
			return Direction.RIGHT;
		}
	}
}
