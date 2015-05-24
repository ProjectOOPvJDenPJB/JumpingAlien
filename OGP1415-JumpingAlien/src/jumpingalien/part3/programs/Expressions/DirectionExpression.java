package jumpingalien.part3.programs.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class DirectionExpression extends BasicExpression<jumpingalien.part3.programs.IProgramFactory.Direction> {
	
	/**
	 * Initializes this direction expression.
	 * 
	 * @param 	direction
	 * 			The direction to set this direction expression to.
	 */
	public DirectionExpression(jumpingalien.part3.programs.IProgramFactory.Direction direction){
		this.setDirection(direction);
	}
	
	/**
	 * Sets the direction of this direction expression.
	 * 
	 * @param	direction
	 * 			The direction to be set.
	 */
	public void setDirection(jumpingalien.part3.programs.IProgramFactory.Direction direction){
		this.direction = direction;
	}
	
	/**
	 * Variable registering the direction of this direction expression.
	 */
	private jumpingalien.part3.programs.IProgramFactory.Direction direction;
	
	/**
	 * Returns the direction of this direction expression.
	 */
	public jumpingalien.part3.programs.IProgramFactory.Direction getDirection(){
		return this.direction;
	}
	
	/**
	 * Evaluates this direction expression
	 * @return	The direction of this direction expression.
	 * 			| result ==
	 * 			|	this.getDirection()
	 */
	@Override
	public jumpingalien.part3.programs.IProgramFactory.Direction evaluate(Program program) {
		return this.direction;
	}
	
	/**
	 * Returns the opposite direction of this direction expression.
	 * @return	If the direction is UP, DOWN is returned
	 * 			| if (this.getDirection() == UP)
	 * 			|	then result == DOWN
	 * 			If the direction is DOWN, UP is returned
	 * 			| if (this.getDirection() == DOWN)
	 * 			|	then result == UP
	 * 			If the direction is RIGHT, LEFT is returned
	 * 			| if (this.getDirection() == RIGHT)
	 * 			|	then result == LEFT
	 * 			If the direction is LEFT, RIGHT is returned
	 * 			| if (this.getDirection() == LEFT)
	 * 			|	then result == RIGHT
	 */
	public jumpingalien.part3.programs.IProgramFactory.Direction getOpposite() {
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